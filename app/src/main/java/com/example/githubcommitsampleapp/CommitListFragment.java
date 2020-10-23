package com.example.githubcommitsampleapp;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.githubcommitsampleapp.daggerbase.BaseFragment;
import com.example.githubcommitsampleapp.di.viewModels.ViewModelProviderFactory;
import com.example.githubcommitsampleapp.model.GitHubCommit;

import java.util.List;

import javax.inject.Inject;

import dagger.android.support.DaggerFragment;


public class CommitListFragment extends BaseFragment {

    @Inject
    ViewModelProviderFactory providerFactory;

    public MainViewModel viewModel;

    public CommitListFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_commit_list, container, false);
        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        viewModel = new ViewModelProvider(requireActivity(), providerFactory).get(MainViewModel.class);

        MutableLiveData<ApiResponse> apiResponse = viewModel.getCommitsList();
        apiResponse.observe(getViewLifecycleOwner(), observer);
    }

    Observer<ApiResponse> observer = apiResponse -> {
        List<GitHubCommit> commitsList = apiResponse.getCommits();
        if(commitsList != null) {
            Log.d("Observer", "gitHubCommitsSize() "+apiResponse.getCommits().size());
        } else {
            Log.d("Observer", "gError "+apiResponse.getError());
        }
    };


}