package com.example.githubcommitsampleapp.di.modules;

import com.example.githubcommitsampleapp.MainActivity;
import com.example.githubcommitsampleapp.MainViewModelModule;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
/*
Module to Build dependencies for Activities/Fragments
 */
@Module
public abstract class ActivityBuilderModule {

    @ContributesAndroidInjector(
            modules = {MainViewModelModule.class,
                    MainActivityFragmentsBuilderModule.class,}
    )
    abstract MainActivity contributeMainActivity();
}
