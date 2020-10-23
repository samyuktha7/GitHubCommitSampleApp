package com.example.githubcommitsampleapp;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.githubcommitsampleapp.daggerbase.BaseFragment;
import com.example.githubcommitsampleapp.di.viewModels.ViewModelProviderFactory;
import com.example.githubcommitsampleapp.model.GitHubCommit;

import java.util.List;

import javax.inject.Inject;


public class CommitListFragment extends BaseFragment {

    @Inject
    ViewModelProviderFactory providerFactory;

    public MainViewModel viewModel;
    private RecyclerView recyclerView;
    private static final String TAG = CommitListFragment.class.getName();

    public CommitListFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_commit_list, container, false);
        recyclerView = (RecyclerView)v.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.addItemDecoration(new DividerItemDecoration(recyclerView.getContext(), DividerItemDecoration.VERTICAL));
        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        viewModel = new ViewModelProvider(requireActivity(), providerFactory).get(MainViewModel.class);

        MutableLiveData<ApiResponse> apiResponse = viewModel.getResponse();
        apiResponse.observe(getViewLifecycleOwner(), observer);
    }

    Observer<ApiResponse> observer = apiResponse -> {
        List<GitHubCommit> commitsList = apiResponse.getCommits();
        if(commitsList != null) {
            RecyclerViewAdapter adapter = new RecyclerViewAdapter(commitsList);
            recyclerView.setAdapter(adapter);
        } else {
            Log.e(TAG, "Error : "+apiResponse.getError());
        }
    };

    public static class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.Mainview> {
        private List<GitHubCommit> commitsList;

        RecyclerViewAdapter(List<GitHubCommit> commitList) {
            this.commitsList = commitList;
        }

        @Override
        public int getItemViewType(int position) {
            return super.getItemViewType(position);
        }

        @NonNull
        @Override
        public Mainview onCreateViewHolder(ViewGroup parent, int viewType) {
            View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_each, parent, false);
            return new Mainview(v);
        }

        @Override
        public void onBindViewHolder(Mainview holder, int position) {
            GitHubCommit commit = commitsList.get(position);

            holder.authorTextView.setText(commit.getCommit().getAuthor().getName());
            holder.commitTextView.setText(commit.getCommit().getMessage());
            holder.hashTextView.setText(commit.getSha());
        }

        @Override
        public int getItemCount() {
            return commitsList.size();
        }

        public static class Mainview extends RecyclerView.ViewHolder {
            TextView authorTextView;
            TextView commitTextView;
            TextView hashTextView;

            public Mainview(View itemView) {
                super(itemView);
                authorTextView = (TextView)itemView.findViewById(R.id.textView);
                commitTextView = (TextView)itemView.findViewById(R.id.textView2);
                hashTextView = (TextView) itemView.findViewById(R.id.textView3);
            }
        }
    }


}