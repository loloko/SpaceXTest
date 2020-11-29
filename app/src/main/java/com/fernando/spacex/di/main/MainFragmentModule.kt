package com.fernando.spacex.di.main


import com.fernando.spacex.ui.IntroFragment
import com.fernando.spacex.ui.RocketDetailFragment
import com.fernando.spacex.ui.RocketListFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class MainFragmentModule {

    @ContributesAndroidInjector
    abstract fun contributeIntroFragment(): IntroFragment

    @ContributesAndroidInjector
    abstract fun contributeRocketListFragment(): RocketListFragment

    @ContributesAndroidInjector
    abstract fun contributeRocketDetailsFragment(): RocketDetailFragment

}