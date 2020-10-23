package com.example.githubcommitsampleapp.network;

import com.example.githubcommitsampleapp.model.GitHubCommit;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface retrofitApiService {

    @GET("/repos/{user}/{repo}/commits")
    Call<List<GitHubCommit>> getUserGithubCommits(@Path("user") String user, @Path("repo") String repo);

}
