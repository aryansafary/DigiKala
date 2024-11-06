package com.arysapp.digikala.di

import com.arysapp.digikala.data.remote.HomeApiInterface
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object HomeApiInterfaceModule {
    @Provides
    @Singleton
    fun provideHomeApiServices(retrofit: Retrofit): HomeApiInterface =
        retrofit.create(HomeApiInterface::class.java)

}