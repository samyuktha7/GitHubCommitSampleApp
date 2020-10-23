package com.example.githubcommitsampleapp.di.modules;

import com.example.githubcommitsampleapp.MainActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
/*
Module to Build dependencies for Activities/Fragments
 */
@Module
public abstract class ActivityBuilderModule {

    @ContributesAndroidInjector
    abstract MainActivity contributeMainActivity();
}
