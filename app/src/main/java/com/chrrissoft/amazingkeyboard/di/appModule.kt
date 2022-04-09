package com.chrrissoft.amazingkeyboard.di

import android.content.Context
import com.chrrissoft.amazingkeyboard.AmazingKeyboard
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
    fun provideMyApplication(@ApplicationContext app: Context) : AmazingKeyboard {
        return app as AmazingKeyboard
    }
}