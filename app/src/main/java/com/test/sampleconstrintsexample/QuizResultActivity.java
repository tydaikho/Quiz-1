package com.test.sampleconstrintsexample;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;

public class QuizResultActivity extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_result);

        intiActionBar();
    }

    private void intiActionBar()
    {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setLogo(R.drawable.trophy);
        actionBar.setDisplayUseLogoEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);
    }
}
