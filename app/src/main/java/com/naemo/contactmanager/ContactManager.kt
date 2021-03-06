package com.naemo.contactmanager

import android.app.Activity
import android.app.Application
import com.naemo.contactmanager.di.component.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject

class ContactManager : Application(), HasActivityInjector {

    internal var activityDispatchingAndroidInjector: DispatchingAndroidInjector<Activity>? = null
    @Inject set

    override fun onCreate() {
        super.onCreate()
        DaggerAppComponent.builder()
            .application(this)
            .build()
            .inject(this)
    }
    override fun activityInjector(): AndroidInjector<Activity> ?{
        return activityDispatchingAndroidInjector
    }
}