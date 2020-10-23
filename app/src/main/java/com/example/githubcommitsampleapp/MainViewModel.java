package com.example.githubcommitsampleapp;

import android.util.Log;

import androidx.lifecycle.ViewModel;

import com.example.githubcommitsampleapp.network.retrofitApiService;

import javax.inject.Inject;

public class MainViewModel extends ViewModel {

    private final retrofitApiService apiService;

    @Inject
    public MainViewModel(retrofitApiService apiService) {
        this.apiService = apiService;
    }
}
