package com.test.sampleconstrintsexample;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class QuizDeskBoardActivity extends AppCompatActivity implements View.OnClickListener
{

    Button btnStart;
    Button btnInstruction;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_desk_board);

        intiActionBar();

        btnStart = (Button)findViewById(R.id.btnStart);
        btnInstruction = (Button)findViewById(R.id.btnInstruction);

        btnStart.setOnClickListener(this);
        btnInstruction.setOnClickListener(this);

    }

    private void intiActionBar()
    {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setLogo(R.drawable.trophy);
        actionBar.setDisplayUseLogoEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);
    }

    @Override
    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.btnStart:
                final Intent intentMain = new Intent(this,QuizMainActivity.class);
                AlertDialog.Builder b = new AlertDialog.Builder(QuizDeskBoardActivity.this)
                        .setTitle("Start Quiz")
                        .setPositiveButton("Ok", new DialogInterface.OnClickListener()
                        {
                            @Override
                            public void onClick(DialogInterface dialog, int which)
                            {
                                startActivity(intentMain);
                            }
                        })
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener()
                        {
                            @Override
                            public void onClick(DialogInterface dialog, int which)
                            {
                                    dialog.cancel();
                            }
                        })
                        .setMessage("All the Best!!!");
                b.create().show();

                break;

            case R.id.btnInstruction:
                Intent intentInstruction = new Intent(this,QuizInstructionActivity.class);
                startActivity(intentInstruction);
                break;
        }


    }
}
