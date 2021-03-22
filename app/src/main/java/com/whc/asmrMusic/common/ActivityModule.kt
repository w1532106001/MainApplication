package com.whc.asmrMusic.common

import com.whc.asmrMusic.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector


@Module
abstract class ActivityModule {
    @ContributesAndroidInjector(modules = [(FragmentModule::class)])
    abstract fun contributeMainActivity(): MainActivity

}