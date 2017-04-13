package com.test.sampleconstrintsexample;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.test.sampleconstrintsexample.models.QuizQuestions;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;


public class QuizMainActivity extends AppCompatActivity
{
    Button btnNext;
    TextView txtTimer, txtQue;
    CountDownTimer mCountDownTimer;
    ArrayList<QuizQuestions> queList;
    static int currentQue = 0;
    Animation anim;
    ArrayList<Integer> questionSequence;
    RadioGroup mRadioGroup;
    RadioButton rbOption1, rbOption2, rbOption3, rbOption4;
    ProgressDialog mProgressDialog;
    ProgressBar mProgressBarQuiz;
    TextView txtQno, txtQueTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        intiActionBar();
        initTimer();
        setUpQuizQuestions();

        blinkText();
        getUniqueRandomNumbers();
        getNextQuestion();

        anim.setRepeatCount(0);
    }

    private void intiActionBar()
    {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setLogo(R.drawable.trophy);
        actionBar.setDisplayUseLogoEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);
    }

    private void initTimer()
    {
        mCountDownTimer = new CountDownTimer(10000, 1000) {

            public void onTick(long millisUntilFinished) {
                long sec = millisUntilFinished / 1000;
                txtTimer.setText(sec + " Sec");
                txtTimer.setTextColor(Color.BLACK);
                if(sec <=5)
                {
                    txtTimer.setTextColor(Color.RED);
                    anim.setRepeatCount(Animation.INFINITE);
                    blinkText();
                }else{
                    anim.setRepeatCount(0);
                }
            }

            public void onFinish() {
                txtTimer.setText("Done!");
                getNextQuestion();
            }
        };
    }

    private  void initView()
    {
        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setMessage("Wait!!! Question Loading...");
        mProgressDialog.setCancelable(false);

        mProgressBarQuiz = (ProgressBar)findViewById(R.id.progressQuiz);
        txtQno = (TextView)findViewById(R.id.txtQno);

        txtTimer = (TextView)findViewById(R.id.txtTimer);
        txtQue = (TextView)findViewById(R.id.txtQue);
        txtQueTitle = (TextView)findViewById(R.id.txtQueTitle);
        txtTimer.setText("00 Sec");

        btnNext = (Button)findViewById(R.id.btnNext);
        btnNext.setOnClickListener(onButtonClick);

        mRadioGroup = (RadioGroup)findViewById(R.id.rbGroup);
        rbOption1 = (RadioButton)findViewById(R.id.rbOption1);
        rbOption2 = (RadioButton)findViewById(R.id.rbOption2);
        rbOption3 = (RadioButton)findViewById(R.id.rbOption3);
        rbOption4 = (RadioButton)findViewById(R.id.rbOption4);

        mRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId)
            {

            }
        });
    }

    private void setUpQuizQuestions()
    {
        QuizQuestions.initData();
        queList = QuizQuestions.getQuizQuestionsArrayList();
    }

    private View.OnClickListener onButtonClick = new View.OnClickListener()
    {
        @Override
        public void onClick(View v)
        {
            switch (v.getId())
            {
                case  R.id.btnNext:
                    mCountDownTimer.cancel();
                    mProgressDialog.show();
                    Thread timerThread = new Thread(){
                        public void run(){
                            try{
                                sleep(3000);
                            }catch(InterruptedException e){
                                e.printStackTrace();
                            }finally{
                                runOnUiThread(new Runnable()
                                {
                                    @Override
                                    public void run()
                                    {
                                        mProgressDialog.hide();
                                        getNextQuestion();
                                    }
                                });
                            }
                        }
                    };
                    timerThread.start();
                    break;


                //case btnShowDialog:
                //    MyDialogFragment dialogFragment = new MyDialogFragment();
                //    dialogFragment.show(getSupportFragmentManager(),"Test");
                //    break;

                default:
                    Toast.makeText(getApplicationContext(),((Button)v).getText().toString(),Toast.LENGTH_SHORT).show();

            }
        }
    };

    private void exitQuiz()
    {
        currentQue = 0;
        mCountDownTimer.cancel();

        AlertDialog.Builder b = new AlertDialog.Builder(QuizMainActivity.this)
                .setTitle("Exit")
                .setPositiveButton("Ok", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {
                        dialog.dismiss();
                        finish();
                        Intent intent = new Intent(getApplicationContext(),QuizResultActivity.class);
                        startActivity(intent);
                    }
                })
                .setMessage("You will end the quiz!!!");
        b.create().show();
    }

    private void getNextQuestion()
    {
        if(currentQue<queList.size())
        {
            //int c = randInt(0,4);
            int c = questionSequence.get(currentQue);
            currentQue++;
            mProgressBarQuiz.setProgress(currentQue);
            txtQno.setText(currentQue + "/" + queList.size());
            QuizQuestions question = queList.get(c);
            txtQueTitle.setText("[ Q : " + currentQue + " ] ");
            txtQue.setText(question.getQuestion());
            rbOption1.setText(question.getOptions1());
            rbOption2.setText(question.getOptions2());
            rbOption3.setText(question.getOptions3());
            rbOption4.setText(question.getOptions4());
            mCountDownTimer.start();
        }else
        {
            mProgressDialog.setMessage("Wait!!! Saving results....");
            mProgressDialog.show();
            Thread timerThread = new Thread(){
                public void run(){
                    try{
                        sleep(3000);
                    }catch(InterruptedException e){
                        e.printStackTrace();
                    }finally{
                        runOnUiThread(new Runnable()
                        {
                            @Override
                            public void run()
                            {
                                mProgressDialog.hide();
                                finish();
                                Intent intent = new Intent(getApplicationContext(),QuizResultActivity.class);
                                startActivity(intent);
                            }
                        });
                    }
                }
            };
            timerThread.start();
        }
    }

    private void blinkText(){

        anim = new AlphaAnimation(0.0f, 1.0f);
        anim.setDuration(50); //You can manage the blinking time with this parameter
        anim.setStartOffset(20);
        anim.setRepeatMode(Animation.REVERSE);
        txtTimer.setAnimation(anim);
    }

    public static int randInt(int min, int max) {

        // Usually this can be a field rather than a method variable
        Random rand = new Random();

        // nextInt is normally exclusive of the top value,
        // so add 1 to make it inclusive
        int randomNum = rand.nextInt((max - min) + 1) + min;

        return randomNum;
    }

    public void getUniqueRandomNumbers()
    {
        questionSequence = new ArrayList<>();
        for (int i=0; i<queList.size(); i++) {
            questionSequence.add(new Integer(i));
        }
        Collections.shuffle(questionSequence);
        for (int i=0; i<queList.size(); i++) {
            System.out.println(questionSequence.get(i));
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.mymenu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        if(item.getItemId() == R.id.mnuStop)
        {
            exitQuiz();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        // Checks the orientation of the screen
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            Toast.makeText(this, "landscape", Toast.LENGTH_SHORT).show();
        } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT){
            Toast.makeText(this, "portrait", Toast.LENGTH_SHORT).show();
        }
    }
}
