package com.arysapp.digikala.di

import com.arysapp.digikala.util.Constants.BASE_URL
import com.arysapp.digikala.util.Constants.TIME_OUT_SECONDS
import com.arysapp.digikala.util.Constants.USER_LANGUAGE
import com.arysapp.digikala.util.Constants.X_API_KEY
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    internal  fun interceptor(): HttpLoggingInterceptor {
     val logging = HttpLoggingInterceptor()
     logging.setLevel(HttpLoggingInterceptor.Level.BODY)
     return logging
    }


    @Provides
    @Singleton
    fun provideOkhttpClient(): OkHttpClient = OkHttpClient.Builder()
        .connectTimeout(TIME_OUT_SECONDS, TimeUnit.SECONDS)
        .writeTimeout(TIME_OUT_SECONDS, TimeUnit.SECONDS)
        .readTimeout(TIME_OUT_SECONDS, TimeUnit.SECONDS)
        .addInterceptor { chain ->
            val request = chain.request().newBuilder()
                .addHeader("x-api-key", X_API_KEY)
                .addHeader("lang", USER_LANGUAGE)
                .build()
            chain.proceed(request)
        }
        .addInterceptor(interceptor())
        .build()

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttpClient)
        .build()


}