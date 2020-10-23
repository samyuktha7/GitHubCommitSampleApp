package com.example.githubcommitsampleapp.di.modules;

import androidx.lifecycle.ViewModelProvider;

import com.example.githubcommitsampleapp.di.viewModels.ViewModelProviderFactory;

import dagger.Binds;
import dagger.Module;

@Module
public abstract class ViewModelProviderModule {

    @Binds
    public abstract ViewModelProvider.Factory bindViewModelFactory(ViewModelProviderFactory viewModelFactory);
}
