package com.example.githubcommitsampleapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.githubcommitsampleapp.daggerbase.BaseActivity;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Adding RepoInputFragment as initial fragment
        RepoInputFragment firsFragment = new RepoInputFragment();
        getSupportFragmentManager().beginTransaction().add(R.id.FragmentContainer,firsFragment).commit();
    }
}