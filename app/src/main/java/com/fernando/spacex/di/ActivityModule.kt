package com.fernando.spacex.di

import com.fernando.spacex.di.main.MainFragmentModule
import com.fernando.spacex.di.main.MainModule
import com.fernando.spacex.di.main.MainScope
import com.fernando.spacex.di.main.MainViewModelModule
import com.fernando.spacex.ui.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityModule {

    @MainScope
    @ContributesAndroidInjector(modules = [MainViewModelModule::class, MainModule::class, MainFragmentModule::class])
    abstract fun contributeMainActivity(): MainActivity


}