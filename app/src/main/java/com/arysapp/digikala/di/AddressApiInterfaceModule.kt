package com.arysapp.digikala.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import com.arysapp.digikala.data.remote.AddressApiInterface
import retrofit2.Retrofit
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AddressApiInterfaceModule {

    @Singleton
    @Provides
    fun provideAddressApiService(retrofit: Retrofit) : AddressApiInterface =
        retrofit.create(AddressApiInterface::class.java)

}