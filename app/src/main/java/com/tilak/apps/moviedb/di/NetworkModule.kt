package com.tilak.apps.moviedb.di

import android.content.Context
import com.google.gson.Gson
import com.tilak.apps.moviedb.common.AppConstants
import com.tilak.apps.moviedb.data.network.ApiHelper
import com.tilak.apps.moviedb.data.network.ApiService
import com.tilak.apps.moviedb.data.network.MovieApiHelperImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class NetworkModule {

    @Provides
    @Singleton
    fun providesRetrofit(
        gsonConverterFactory: GsonConverterFactory,
        okHttpClient: OkHttpClient
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(AppConstants.BASE_URL)
            .addConverterFactory(gsonConverterFactory)
            .client(okHttpClient)
            .build()
    }

    @Provides
    @Singleton
    fun providesOkHttpClient(
        @ApplicationContext context: Context
    ): OkHttpClient {
        val cacheSize = (5 * 1024 * 1024).toLong()
        val mCache = Cache(context.cacheDir, cacheSize)
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        val client = OkHttpClient.Builder()
            .cache(mCache) // make your app offline-friendly without a database!
            .connectTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .addNetworkInterceptor(interceptor)
            .addInterceptor { chain ->
                var request = chain.request()
                val urlWithApiHeader = request.url.newBuilder()
                    .addQueryParameter("api_key", AppConstants.API_KEY).build()
                /* If there is Internet, get the cache that was stored 5 seconds ago.
                 * If the cache is older than 5 seconds, then discard it,
                 * and indicate an error in fetching the response.
                 * The 'max-age' attribute is responsible for this behavior.
                 */
                request =
                    if (true) request.newBuilder() //make default to true till i figure out how to inject network status
                        .header("Cache-Control", "public, max-age=" + 5).url(urlWithApiHeader)
                        .build()
                    /*If there is no Internet, get the cache that was stored 7 days ago.
                     * If the cache is older than 7 days, then discard it,
                     * and indicate an error in fetching the response.
                     * The 'max-stale' attribute is responsible for this behavior.
                     * The 'only-if-cached' attribute indicates to not retrieve new data; fetch the cache only instead.
                     */
                    else request.newBuilder().header(
                        "Cache-Control",
                        "public, only-if-cached, max-stale=" + (60 * 60 * 24 * 7)
                    ).url(urlWithApiHeader).build()
                chain.proceed(request)
            }
        return client.build()
    }

    @Provides
    @Singleton
    fun providesGson(): Gson {
        return Gson()
    }

    @Provides
    @Singleton
    fun providesGsonConverterFactory(): GsonConverterFactory {
        return GsonConverterFactory.create()
    }

    @Singleton
    @Provides
    fun provideService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideApiHelper(apiHelperImpl: MovieApiHelperImpl): ApiHelper = apiHelperImpl
}