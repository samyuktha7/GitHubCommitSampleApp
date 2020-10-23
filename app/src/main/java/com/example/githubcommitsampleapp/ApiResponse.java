package com.example.githubcommitsampleapp;

import com.example.githubcommitsampleapp.model.GitHubCommit;

import java.util.List;

public class ApiResponse {

    private List<GitHubCommit> commits;
    private String error;

    public ApiResponse(String error) {
        this.error = error;
        this.commits= null;
    }

    public ApiResponse(List<GitHubCommit> commits) {
        this.commits = commits;
        this.error = null;
    }

    public List<GitHubCommit> getCommits() {
        return commits;
    }

    public void setCommits(List<GitHubCommit> commits) {
        this.commits = commits;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
