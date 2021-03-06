package com.whc.asmrMusic.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.whc.asmrMusic.ui.home.HomeViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap



@Module
abstract class ViewModelModule {


    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(HomeViewModel::class)
    internal abstract fun bindCodeDetailViewModel(viewModel: HomeViewModel): ViewModel
}