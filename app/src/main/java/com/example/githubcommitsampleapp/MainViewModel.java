package com.example.githubcommitsampleapp;

import android.util.Log;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;

import com.example.githubcommitsampleapp.model.GitHubCommit;
import com.example.githubcommitsampleapp.network.RetrofitApiService;
import com.example.githubcommitsampleapp.utils.Constants;

import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainViewModel extends ViewModel {

    private HandleNetworkCalls handleNetworkCalls;
    MutableLiveData<ApiResponse> apiResponse;
    private String userName;
    private String repoName;
    private static final String TAG = "MainViewModel";
    private int page = 0;


    @Inject
    public MainViewModel(HandleNetworkCalls handleNetworkCalls) {
        this.handleNetworkCalls = handleNetworkCalls;
    }

    MutableLiveData<ApiResponse> getResponse(String userName, String repoName) {
        if(this.userName == null || !this.userName.equals(userName) || this.repoName == null  || !this.repoName.equals(repoName) || apiResponse == null) {
            this.userName = userName;
            this.repoName = repoName;
            this.page = 1;
            apiResponse = new MutableLiveData<>();
            apiResponse = handleNetworkCalls.getApiResponse(userName, repoName, page);
        }
        return apiResponse;
    }

    MutableLiveData<ApiResponse> getResponse() {
        Log.d("TAG", "getResponse");
        if(apiResponse == null) apiResponse = handleNetworkCalls.getApiResponse(userName, repoName, page);
        return apiResponse;
    }

    /*
    Fetches more data from next page when user scrolls end of page
     */
    public void fetchMoreData() {
        Log.d(TAG, "fetchMoreData");
        page++;
        apiResponse = handleNetworkCalls.getApiResponse(userName, repoName, page);
    }
}
