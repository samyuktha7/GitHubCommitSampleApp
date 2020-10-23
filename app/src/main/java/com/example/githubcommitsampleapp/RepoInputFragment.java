package com.example.githubcommitsampleapp;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModelProvider;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.githubcommitsampleapp.daggerbase.BaseFragment;
import com.example.githubcommitsampleapp.di.viewModels.ViewModelProviderFactory;

import java.util.Objects;

import javax.inject.Inject;

import dagger.android.support.DaggerFragment;


public class RepoInputFragment extends BaseFragment {

    private EditText userName;
    private EditText repositoryName;
    private Button getCommitsButton;
    private MainViewModel viewModel;

    @Inject
    ViewModelProviderFactory providerFactory;

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

        getCommitsButton.setOnClickListener(view -> {
            String gitHubUserName = userName.getText().toString();
            String repoName = repositoryName.getText().toString();
            // Validate Username and repoName - TODO
            viewModel.setData(gitHubUserName, repoName);
            FragmentTransaction fragmentTransaction = Objects.requireNonNull(getActivity()).getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.FragmentContainer, new CommitListFragment()).commit();
            fragmentTransaction.addToBackStack(null);
        });
        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        viewModel = new ViewModelProvider(requireActivity(), providerFactory).get(MainViewModel.class);

    }
}