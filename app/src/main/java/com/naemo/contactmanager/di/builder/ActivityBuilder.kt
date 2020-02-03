package com.naemo.contactmanager.di.builder

import com.naemo.contactmanager.ui.home.HomeActivity
import com.naemo.contactmanager.ui.home.HomeModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilder {

    @ContributesAndroidInjector(modules = [HomeModule::class])
    abstract fun bindHomeActivity(): HomeActivity
}