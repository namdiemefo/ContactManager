package com.naemo.contactmanager.ui.home

import com.naemo.contactmanager.R
import dagger.Module
import dagger.Provides

@Module
class HomeModule {

    @Provides
    fun providesHomeViewModule(): HomeViewModel {
        return HomeViewModel()
    }

    @Provides
    fun provideLayoutId(): Int {
        return R.layout.activity_home
    }
}