package com.ml.translator.di

import com.ml.translator.data.*
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DataModule {

    @Binds
    @Singleton
    abstract fun bindTranslatorProvider(provider: TranslatorProviderImpl): TranslatorProvider

    @Binds
    @Singleton
    abstract fun bindDigitalInkProvider(provider: DigitalInkProviderImpl): DigitalInkProvider

    @Binds
    @Singleton
    abstract fun bindScreenDataProvider(provider: ScreensDataProviderImpl): ScreensDataProvider

}