package com.naemo.contactmanager.ui.card

import com.naemo.contactmanager.R
import dagger.Module
import dagger.Provides

@Module
class CardModule {
    @Provides
    fun providesCardViewModule(): CardViewModel {
        return CardViewModel()
    }

    @Provides
    fun provideLayoutId(): Int {
        return R.layout.activity_card
    }
}