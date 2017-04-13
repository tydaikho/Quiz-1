package com.test.sampleconstrintsexample;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;

public class QuizInstructionActivity extends AppCompatActivity
{

    WebView mWebViewInstruction;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_instruction);

        intiActionBar();

        mWebViewInstruction = (WebView)findViewById(R.id.webViewInstruction);
        mWebViewInstruction.loadUrl("file:///android_asset/quizinstruction.html");
    }

    private void intiActionBar()
    {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setLogo(R.drawable.trophy);
        actionBar.setDisplayUseLogoEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);
    }
}
