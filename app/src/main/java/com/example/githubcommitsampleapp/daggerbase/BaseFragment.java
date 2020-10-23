package com.example.githubcommitsampleapp.daggerbase;

import android.content.Context;

import dagger.android.support.DaggerFragment;

public class BaseFragment extends DaggerFragment {

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

}
