package com.arysapp.digikala.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import com.arysapp.digikala.data.datastore.DataStoreRepository
import com.arysapp.digikala.data.datastore.DataStoreRepositoryImpl
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object DataStoreModule {

    @Singleton
    @Provides
    fun provideDataStoreRepository(
        @ApplicationContext context: Context
    ): DataStoreRepository = DataStoreRepositoryImpl(context)

}