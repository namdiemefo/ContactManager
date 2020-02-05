package com.naemo.contactmanager.di.module

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule {
    var firstName: String? = null
    var lastName: String? = null
    var phoneNumber: String? = null
    var birthday: String? = null
    var address: String? = null

    @Singleton
    @Provides
    fun provideContext(application: Application): Context {
        return application.applicationContext
    }

    @Singleton
    @Provides
    fun provideFirstName() : String? {
        return firstName
    }

    @Singleton
    @Provides
    fun provideLastName() : String? {
        return lastName
    }

    @Singleton
    @Provides
    fun providePhoneNumber(): String? {
        return phoneNumber
    }

    @Singleton
    @Provides
    fun provideBirthday(): String? {
        return birthday
    }

    @Singleton
    @Provides
    fun provideAddress(): String? {
        return address
    }
}