package com.example.githubcommitsampleapp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import dagger.android.support.DaggerFragment;


public class RepoInputFragment extends DaggerFragment {

    private EditText userName;
    private EditText repositoryName;
    private Button getCommitsButton;

    public RepoInputFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_repo_input, container, false);
        userName = (EditText) v.findViewById(R.id.userName);
        repositoryName = (EditText) v.findViewById(R.id.repoName);
        getCommitsButton = (Button) v.findViewById(R.id.button);

        getCommitsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String gitHubUserName = userName.getText().toString();
                String repoName = repositoryName.getText().toString();

            }
        });
        return v;
    }
}