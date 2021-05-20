package com.wickappz.dot;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Button startButton;
    TextView countdownTimerText;
    TextView livesRemainingText;
    ConstraintLayout mainLayout;
    Long startTime;
    Long finishTime;
    int livesRemaining;

    boolean inTapMode;
    boolean isFalseStart;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        int gameMode = getIntent().getIntExtra("GAME_MODE_CHOICE", 0);

        DotEngine currentGame = new DotEngine();
        currentGame.setGameMode(gameMode);

        if(currentGame.getGameMode() == DotEngine.MODE_MAIN_MENU) {
            startActivity(new Intent(MainActivity.this, MainMenuAndResults.class));
            finish();
        }

        initLayout();

        inTapMode = false;

        startButton = findViewById(R.id.startButton);

        countdownTimerText = findViewById(R.id.countdownTimerText);
        livesRemainingText = findViewById(R.id.livesRemainingText);

        mainLayout = findViewById(R.id.mainLayout);

        livesRemaining = 3;
        livesRemainingText.setText(livesRemaining + "");

        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                countdownTimerText.setText("start");

                Random rand = new Random();
                int countdownTime = 3000 + rand.nextInt(2000);
                startTime = 0L;
                finishTime = 0L;

                isFalseStart = false;

                new CountDownTimer(countdownTime, 1000) {
                    int tickCount = 3;

                    @Override
                    public void onTick(long millisUntilFinished) {
                        countdownTimerText.setText("" + (tickCount));
                        if(tickCount != 1) {
                            tickCount--;
                        }
                    }

                    @Override
                    public void onFinish() {
                        if(!isFalseStart) {
                            countdownTimerText.setText("time=" + countdownTime);
                            inTapMode = true;
                            startTime = System.currentTimeMillis();
                        } else {
                            livesRemaining--;
                            livesRemainingText.setText(livesRemaining + "");
                            countdownTimerText.setText("False Start! You lose a life");
                        }
                    }
                }.start();
            }
        });

        mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(inTapMode) {

                    inTapMode = false;
                    finishTime = System.currentTimeMillis();

                    long differenceInMillis = finishTime - startTime;

                    countdownTimerText.setText(differenceInMillis + "");

                } else {
                    isFalseStart = true;
                }
            }
        });

    }

    public void initLayout() {

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(MainActivity.this, MainMenuAndResults.class));
        finish();
    }
}