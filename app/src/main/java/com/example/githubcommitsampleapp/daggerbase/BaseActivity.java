package com.example.githubcommitsampleapp.daggerbase;

import android.os.Bundle;
import androidx.annotation.Nullable;
import dagger.android.support.DaggerAppCompatActivity;

public class BaseActivity extends DaggerAppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}
