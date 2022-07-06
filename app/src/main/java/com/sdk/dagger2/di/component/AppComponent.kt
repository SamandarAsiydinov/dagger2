package com.sdk.dagger2.di.component

import com.sdk.dagger2.MainActivity
import com.sdk.dagger2.di.module.DatabaseModule
import com.sdk.dagger2.di.module.NetworkModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [NetworkModule::class, DatabaseModule::class])
interface AppComponent {
    fun inject(activity: MainActivity)
}