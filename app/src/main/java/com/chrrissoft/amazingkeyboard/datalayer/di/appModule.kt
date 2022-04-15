package com.chrrissoft.amazingkeyboard.datalayer.di

import android.content.Context
import com.chrrissoft.amazingkeyboard.AmazingKeyboardApp
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {
    @Singleton
    @Provides
    fun provideMyApplication(@ApplicationContext app: Context) : AmazingKeyboardApp {
        return app as AmazingKeyboardApp
    }
}