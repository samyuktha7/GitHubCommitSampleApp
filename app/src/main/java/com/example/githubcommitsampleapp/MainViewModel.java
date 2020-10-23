package com.example.githubcommitsampleapp;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.githubcommitsampleapp.model.GitHubCommit;
import com.example.githubcommitsampleapp.network.retrofitApiService;

import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainViewModel extends ViewModel {

    private final retrofitApiService apiService;
    MutableLiveData<ApiResponse> apiResponse;
    private List<GitHubCommit> commitsList;
    private String userName;
    private String repoName;
    private static final String TAG = "MainViewModel";
    private int page = 0;

    @Inject
    public MainViewModel(retrofitApiService apiService) {
        this.apiService = apiService;
        commitsList = new ArrayList<>();
    }

    MutableLiveData<ApiResponse> getResponse(String userName, String repoName) {
        if(this.userName == null || !this.userName.equals(userName) || this.repoName == null  || !this.repoName.equals(repoName) || apiResponse == null) {
            this.userName = userName;
            this.repoName = repoName;
            Log.d("TAG", "getResponse - parameters");
            this.page = 1;
            initNetworkCall(page);
        }
        return apiResponse;
    }

    MutableLiveData<ApiResponse> getResponse() {
        Log.d("TAG", "getResponse");
        if(apiResponse == null) initNetworkCall(page);
        return apiResponse;
    }

    private void initNetworkCall(int page) {
        Log.d(TAG, "initNetworkCall()");

        apiResponse = new MutableLiveData<>();
        Call<List<GitHubCommit>> list = apiService.getUserGithubCommits(userName, repoName, page);
        list.enqueue(new Callback<List<GitHubCommit>>() {
            @Override
            public void onResponse(Call<List<GitHubCommit>> call, Response<List<GitHubCommit>> response) {
                if(response.body() == null) {
                    apiResponse.setValue(new ApiResponse("Not found"));
                } else {
                    commitsList.addAll(response.body());
                    apiResponse.setValue(new ApiResponse(commitsList));
                }
            }

            @Override
            public void onFailure(Call<List<GitHubCommit>> call, Throwable t) {
                Log.d(TAG, "onFailure");
                apiResponse.setValue(new ApiResponse(t.getMessage()));            }
        });
    }

    public void fetchMoreData() {
        Log.d(TAG, "fetchMoreData");
        page++;
        initNetworkCall(page);
    }

}
