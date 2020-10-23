package com.example.githubcommitsampleapp;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.githubcommitsampleapp.daggerbase.BaseFragment;
import com.example.githubcommitsampleapp.di.viewModels.ViewModelProviderFactory;

import java.util.Objects;

import javax.inject.Inject;

import dagger.android.support.DaggerFragment;


public class RepoInputFragment extends BaseFragment {

    private EditText userName;
    private EditText repositoryName;
    private MainViewModel viewModel;
    private MutableLiveData<ApiResponse> apiResponse;

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
        Button getCommitsButton = (Button) v.findViewById(R.id.button);

        getCommitsButton.setOnClickListener(view -> {
            String gitHubUserName = userName.getText().toString();
            String repoName = repositoryName.getText().toString();
            if(validate(gitHubUserName, repoName)) {
                apiResponse = viewModel.getResponse(gitHubUserName, repoName);
            }

            apiResponse.observe(getViewLifecycleOwner(), apiResponse -> {
                if(apiResponse.getError() == null) {
                    FragmentTransaction fragmentTransaction = Objects.requireNonNull(getActivity()).getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.FragmentContainer, new CommitListFragment()).commit();
                    fragmentTransaction.addToBackStack(null);
                } else {
                    showToast(apiResponse.getError());
                }
            });
        });
        return v;
    }

    private boolean validate(String gitHubUserName, String repository) {
        if(gitHubUserName.isEmpty() && repository.isEmpty()) {
            showToast("Enter Input");
            return false;
        }
        else if(gitHubUserName.isEmpty()) {
            showToast("Enter Github Username");
            return false;
        }
        else if(repository.isEmpty()) {
            showToast("Enter Repository name");
            return false;
        }
        else return true;
    }

    private void showToast(String s) {
        Toast.makeText(getContext(), s, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        viewModel = new ViewModelProvider(requireActivity(), providerFactory).get(MainViewModel.class);

    }
}