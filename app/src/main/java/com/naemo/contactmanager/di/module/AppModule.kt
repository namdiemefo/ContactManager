package com.naemo.contactmanager.di.module

import android.app.Application
import android.content.Context
import com.naemo.contactmanager.helpers.SnackBarManager
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule {

    @Singleton
    @Provides
    fun provideContext(application: Application): Context {
        return application.applicationContext
    }

    @Singleton
    @Provides
    fun provideSnackBar(): SnackBarManager {
        return SnackBarManager()
    }

}