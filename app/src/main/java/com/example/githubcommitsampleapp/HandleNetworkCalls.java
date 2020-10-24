package com.example.githubcommitsampleapp;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.example.githubcommitsampleapp.model.GitHubCommit;
import com.example.githubcommitsampleapp.network.RetrofitApiService;
import com.example.githubcommitsampleapp.utils.Constants;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HandleNetworkCalls {

    MutableLiveData<ApiResponse> apiResponse;
    private static final String TAG = "HandleNetworkCalls";
    private final RetrofitApiService apiService;
    private List<GitHubCommit> commitsList;
    private String gitHubUserName;
    private String repoName;
    private int page;

    @Inject
    public HandleNetworkCalls(RetrofitApiService apiService) {
        this.apiService = apiService;
        commitsList = new ArrayList<>();
        apiResponse = new MutableLiveData<>();
    }

    public MutableLiveData<ApiResponse> getApiResponse(String userName, String repoName, int page) {
        this.gitHubUserName = userName;
        this.repoName = repoName;
        this.page = page;
        initNetworkCall();
        return apiResponse;
    }

    private void initNetworkCall() {
        Log.d(TAG, "initNetworkCall()");

        apiResponse = new MutableLiveData<>();
        Call<List<GitHubCommit>> list = apiService.getUserGithubCommits(gitHubUserName, repoName, page);
        list.enqueue(new Callback<List<GitHubCommit>>() {
            @Override
            public void onResponse(@NonNull Call<List<GitHubCommit>> call, @NonNull Response<List<GitHubCommit>> response) {
                Log.d(TAG, "onResponse");
                List<GitHubCommit> commitList = response.body();
                if(commitList == null) {
                    apiResponse.setValue(new ApiResponse(Constants.ENTRY_NOTFOUND));
                } else {
                    commitsList.addAll(commitList);
                    apiResponse.setValue(new ApiResponse(commitsList));

                }
            }

            @Override
            public void onFailure(@NonNull Call<List<GitHubCommit>> call, @NonNull Throwable t) {
                Log.d(TAG, "onFailure");
                apiResponse.setValue(new ApiResponse(t.getMessage()));            }
        });
    }
}
