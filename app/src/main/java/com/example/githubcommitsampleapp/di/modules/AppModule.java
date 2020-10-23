package com.example.githubcommitsampleapp.di.modules;

import android.content.Context;

import com.example.githubcommitsampleapp.daggerbase.BaseApplication;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/*
Module to build dependencies classes which are not Activities/Fragments,
which are intended to instantiate once and never change
 */
@Module
public class AppModule {

    @Singleton
    @Provides
    Context provideContext(BaseApplication application){
        return application;
    }
}
