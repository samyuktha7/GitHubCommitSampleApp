package com.example.githubcommitsampleapp.model;

public class GitHubCommit {

    private Commit commit;
    private String sha;

    public GitHubCommit(String sha, Commit commit) {
        this.sha = sha;
        this.commit = commit;
    }

    public String getSha() {
        return sha;
    }

    public void setSha(String sha) {
        this.sha = sha;
    }

    public Commit getCommit() {
        return commit;
    }

    public void setCommit(Commit commit) {
        this.commit = commit;
    }


}
