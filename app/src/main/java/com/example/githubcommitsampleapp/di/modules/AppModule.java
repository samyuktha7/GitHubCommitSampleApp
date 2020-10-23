package com.example.githubcommitsampleapp.di.modules;

import android.content.Context;

import com.example.githubcommitsampleapp.daggerbase.BaseApplication;
import com.example.githubcommitsampleapp.network.RetrofitApiService;
import com.example.githubcommitsampleapp.utils.Constants;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

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

    @Singleton
    @Provides
    static Retrofit provideRetrofitInstance() {
        return new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    @Provides
    static RetrofitApiService provideRetrofitApiService(Retrofit retrofit) {
        return retrofit.create(RetrofitApiService.class);
    }
}
