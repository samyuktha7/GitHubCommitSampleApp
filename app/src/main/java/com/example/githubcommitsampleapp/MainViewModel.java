package com.example.githubcommitsampleapp;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.githubcommitsampleapp.model.GitHubCommit;
import com.example.githubcommitsampleapp.network.retrofitApiService;

import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainViewModel extends ViewModel {

    private final retrofitApiService apiService;
    MutableLiveData<ApiResponse> apiResponse = new MutableLiveData<>();
    private String userName;
    private String repoName;

    @Inject
    public MainViewModel(retrofitApiService apiService) {
        this.apiService = apiService;
    }

    MutableLiveData<ApiResponse> getCommitsList() {
        initNetworkCall();
        return apiResponse;
    }

    public void setData(String userName, String repoName) {
        this.userName = userName;
        this.repoName = repoName;
    }

    private void initNetworkCall() {
        Call<List<GitHubCommit>> list = apiService.getUserGithubCommits(userName, repoName);
        list.enqueue(new Callback<List<GitHubCommit>>() {
            @Override
            public void onResponse(Call<List<GitHubCommit>> call, Response<List<GitHubCommit>> response) {
                apiResponse.setValue(new ApiResponse(response.body()));
            }

            @Override
            public void onFailure(Call<List<GitHubCommit>> call, Throwable t) {
                apiResponse.setValue(new ApiResponse(t.getMessage()));            }
        });
    }

    private boolean validate(String userName, String repoName) {
        // TO-DO
        return true;
    }

}
