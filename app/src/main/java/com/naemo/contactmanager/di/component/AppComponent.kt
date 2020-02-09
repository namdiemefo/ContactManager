package com.naemo.contactmanager.di.component

import android.app.Application
import com.naemo.contactmanager.ContactManager
import com.naemo.contactmanager.di.builder.ActivityBuilder
import com.naemo.contactmanager.di.module.AppModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [(AndroidSupportInjectionModule::class), (AppModule::class), (ActivityBuilder::class)])
interface AppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder


        fun build(): AppComponent
    }

    fun inject(contactManager: ContactManager)
}