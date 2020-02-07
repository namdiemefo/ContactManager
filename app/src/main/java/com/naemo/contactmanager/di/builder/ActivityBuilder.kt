package com.naemo.contactmanager.di.builder

import com.naemo.contactmanager.ui.add.AddActivity
import com.naemo.contactmanager.ui.add.AddModule
import com.naemo.contactmanager.ui.base.BaseModule
import com.naemo.contactmanager.ui.card.CardActivity
import com.naemo.contactmanager.ui.card.CardModule
import com.naemo.contactmanager.ui.home.HomeActivity
import com.naemo.contactmanager.ui.home.HomeModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilder {


    @ContributesAndroidInjector(modules = [HomeModule::class, BaseModule::class])
    abstract fun bindHomeActivity(): HomeActivity

    @ContributesAndroidInjector(modules = [AddModule::class, BaseModule::class])
    abstract fun bindAddActivity(): AddActivity

    @ContributesAndroidInjector(modules = [CardModule::class, BaseModule::class])
    abstract fun bindCardActivity(): CardActivity
}