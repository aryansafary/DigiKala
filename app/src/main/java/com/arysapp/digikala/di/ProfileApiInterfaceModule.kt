package com.arysapp.digikala.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import com.arysapp.digikala.data.remote.ProfileApiInterface
import retrofit2.Retrofit
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object ProfileApiInterfaceModule {

    @Singleton
    @Provides
    fun provideProfileApiService(retrofit: Retrofit) : ProfileApiInterface =
        retrofit.create(ProfileApiInterface::class.java)

}