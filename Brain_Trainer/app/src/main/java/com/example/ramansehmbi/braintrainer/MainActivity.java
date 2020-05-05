package com.example.ramansehmbi.braintrainer;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Button startButton;
    Button buttonA;
    Button buttonB;
    Button buttonC;
    Button buttonD;
    TextView timerTextView;
    TextView correctAnswerView;
    TextView scoreTextView;
    CountDownTimer countDownTimer;
    TextView sumView;
    int totalNumberOfQuestions = 10;
    int Score = 0 ;
    int counter = 0 ;
    ArrayList<Integer> answers = new ArrayList<Integer>();
    int locationCorrectAnswer;

    public void Start(View view)
    {
        startButton.setVisibility(View.INVISIBLE);
        buttonA.setVisibility(View.VISIBLE);
        buttonB.setVisibility(View.VISIBLE);
        buttonC.setVisibility(View.VISIBLE);
        buttonD.setVisibility(View.VISIBLE);
        Score = 0;
        counter = 0;
        onStartSumView();
        countDownTimer.cancel();

        countDownTimer = new CountDownTimer( 30000, 1000) {

            @Override
            public void onTick(long millisUntilFinished) {
                updateTimer((int) millisUntilFinished / 1000);
            }

            @Override
            public void onFinish() {
                Toast.makeText(getApplicationContext(), "Time is Up !!", Toast.LENGTH_SHORT).show();
                if(counter != totalNumberOfQuestions)
                {
                    Result();
                }
            }
        }.start();
    }

    public void onStartSumView()
    {
        Random randomnNumber = new Random();

        int numberA = randomnNumber.nextInt(21);
        int numberB = randomnNumber.nextInt(21);

        sumView.setText(Integer.toString(numberA) + "+" + Integer.toString(numberB));
        scoreTextView.setText(Integer.toString(Score) + "/" + Integer.toString(totalNumberOfQuestions));

        Random rand = new Random();
        locationCorrectAnswer = rand.nextInt(4);
        answers.clear();
        for (int i =0;i<4 ;i++)
        {
            if(i==locationCorrectAnswer)
            {
                answers.add(numberA+numberB);
            }
            else
            {
                int wrongAnswer = rand.nextInt(41);
                while(wrongAnswer == numberA+numberB)
                {
                    wrongAnswer = rand.nextInt(41);
                }
                answers.add(wrongAnswer);
            }

        }

        buttonA.setText(Integer.toString(answers.get(0)));
        buttonB.setText(Integer.toString(answers.get(1)));
        buttonC.setText(Integer.toString(answers.get(2)));
        buttonD.setText(Integer.toString(answers.get(3)));
    }

    public void chooseAnswer(View view)
    {
        counter++;
        Log.i("INFO","The value of the counter is"+ counter);
        if(counter != totalNumberOfQuestions) {
            if (Integer.toString(locationCorrectAnswer).equals(view.getTag().toString())) {
                Toast.makeText(getApplicationContext(), "Correct !!", Toast.LENGTH_SHORT).show();
                correctAnswerView.setVisibility(View.VISIBLE);
                Score++;
                scoreTextView.setText(Integer.toString(Score) + "/" + Integer.toString(totalNumberOfQuestions));
                onStartSumView();
            } else {
                Toast.makeText(getApplicationContext(), "Incorrect !!", Toast.LENGTH_SHORT).show();
                correctAnswerView.setText("Incorrect !");
                onStartSumView();
            }
        }
        else
        {
            Result();
        }
    }

    public void updateTimer(int progress) {
        int minutes = progress / 60;
        int seconds = progress - (minutes * 60);
        String secondString = Integer.toString(seconds);

        if (seconds <= 9) {
            secondString = "0" + secondString;
        }
        timerTextView.setText(Integer.toString(minutes) + ":" + secondString);
    }

    public void Result()
    {
        correctAnswerView.setText("You Total Score is "+ Score);
        buttonA.setVisibility(View.INVISIBLE);
        buttonB.setVisibility(View.INVISIBLE);
        buttonC.setVisibility(View.INVISIBLE);
        buttonD.setVisibility(View.INVISIBLE);
        startButton.setText("Play Again");
        startButton.setVisibility(View.VISIBLE);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startButton =(Button) findViewById(R.id.startButton);
        sumView = (TextView) findViewById(R.id.questionTextView);

        buttonA = (Button) findViewById(R.id.button0);
        buttonB = (Button) findViewById(R.id.button1);
        buttonC = (Button) findViewById(R.id.button2);
        buttonD  = (Button) findViewById(R.id.button3);
        correctAnswerView =(TextView) findViewById(R.id.resultTextView);
        scoreTextView =(TextView) findViewById(R.id.numberTextView);
        timerTextView =(TextView) findViewById(R.id.timerTextView);
        countDownTimer = new CountDownTimer( 30000, 1000) {

            @Override
            public void onTick(long millisUntilFinished) {
                updateTimer((int) millisUntilFinished / 1000);
            }

            @Override
            public void onFinish() {
                Toast.makeText(getApplicationContext(), "Time is Up !!", Toast.LENGTH_SHORT).show();
                if(counter != totalNumberOfQuestions)
                {
                    Result();
                }
            }
        }.start();


        onStartSumView();

    }
}
