package com.example.githubcommitsampleapp;

import androidx.lifecycle.ViewModel;

import com.example.githubcommitsampleapp.di.viewModels.ViewModelKey;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class MainViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel.class)
    public abstract ViewModel bindViewModel(MainViewModel viewModel);
}
