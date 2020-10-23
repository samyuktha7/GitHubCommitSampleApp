package com.example.githubcommitsampleapp.di.modules;

import com.example.githubcommitsampleapp.CommitListFragment;
import com.example.githubcommitsampleapp.RepoInputFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class MainActivityFragmentsBuilderModule {

    @ContributesAndroidInjector
    abstract RepoInputFragment contributeRepoInputFragment();

    @ContributesAndroidInjector
    abstract CommitListFragment contributeCommitsListFragment();
}
