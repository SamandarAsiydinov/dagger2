package com.sdk.dagger2

import android.app.Application
import com.sdk.dagger2.di.component.AppComponent
import com.sdk.dagger2.di.component.DaggerAppComponent
import com.sdk.dagger2.di.module.DatabaseModule

class MyApp: Application() {
    companion object {
        lateinit var appComponent: AppComponent
    }

    override fun onCreate() {
        super.onCreate()

        appComponent = DaggerAppComponent.builder()
            .databaseModule(DatabaseModule(this))
            .build()
    }
}