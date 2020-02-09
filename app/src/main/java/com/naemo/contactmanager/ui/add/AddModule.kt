package com.naemo.contactmanager.ui.add

import com.naemo.contactmanager.R
import com.naemo.contactmanager.helpers.SnackBarManager
import dagger.Module
import dagger.Provides

@Module
class AddModule {

    @Provides
    fun providesAddViewModel() : AddViewModel {
        return AddViewModel()
    }

    @Provides
    fun provideLayoutId(): Int {
        return R.layout.activity_add
    }

}