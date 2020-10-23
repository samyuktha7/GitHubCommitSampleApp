package com.example.githubcommitsampleapp.di.components;

import android.app.Application;

import com.example.githubcommitsampleapp.daggerbase.BaseApplication;
import com.example.githubcommitsampleapp.di.modules.ActivityBuilderModule;
import com.example.githubcommitsampleapp.di.modules.AppModule;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;

@Singleton
@Component(
        modules = {AndroidSupportInjectionModule.class,
                ActivityBuilderModule.class,
                AppModule.class,}
)
public interface AppComponent extends AndroidInjector<BaseApplication> {

    @Component.Builder
    interface Builder {

        // Optional
        @BindsInstance
        Builder application(Application application);

        AppComponent build();
    }
}
