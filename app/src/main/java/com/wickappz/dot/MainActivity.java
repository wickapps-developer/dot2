package com.wickappz.dot;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Button startButton;
    TextView countdownTimerText;
    TextView livesRemainingText;
    ImageView dotCircleImage;
    ImageView touchLocationBadImage;
    ImageView touchLocationGoodImage;
    ConstraintLayout mainLayout;
    Long startTime;
    Long finishTime;
    int livesRemaining;

    boolean inTapMode;
    boolean isFalseStart;

    public static int WIDTH = 0;
    public static int HEIGHT = 1;


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

        dotCircleImage = findViewById(R.id.dotCircleImage);
        touchLocationBadImage = findViewById(R.id.touchLocationBadImage);
        touchLocationGoodImage = findViewById(R.id.touchLocationGoodImage);

        mainLayout = findViewById(R.id.mainLayout);

        livesRemaining = 3;
        livesRemainingText.setText(livesRemaining + "");

        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                countdownTimerText.setText("start");

                resetDotsLayout();

                Random rand = new Random();
                int countdownTime = 3000 + rand.nextInt(2000);
                startTime = 0L;
                finishTime = 0L;

                isFalseStart = false;

                startButton.setVisibility(View.INVISIBLE);

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

                            Display display = getWindowManager().getDefaultDisplay();
                            DisplayMetrics outMetrics = new DisplayMetrics ();
                            display.getMetrics(outMetrics);

                            float density  = getResources().getDisplayMetrics().density;
                            float dpWidth  = outMetrics.widthPixels / density;
                            float dpHeight = outMetrics.heightPixels / density;


                            int[] dotPosition = currentGame.getDotPlacement(dpWidth, dpHeight);

                            dotCircleImage.setX(dotPosition[WIDTH]);
                            dotCircleImage.setY(dotPosition[HEIGHT]);
                            dotCircleImage.setVisibility(View.VISIBLE);


                            countdownTimerText.setText("time=" + countdownTime + " circle placement: H - " + dotPosition[HEIGHT] + " W - " + dotPosition[WIDTH]);


                        } else {
                            livesRemaining--;
                            livesRemainingText.setText(livesRemaining + "");
                            countdownTimerText.setText("False Start! You lose a life");
                        }
                    }
                }.start();
            }
        });

        mainLayout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int x = (int) event.getX();
                int y = (int) event.getY();

                startButton.setVisibility(View.VISIBLE);

                if(inTapMode) {

                    inTapMode = false;
                    finishTime = System.currentTimeMillis();

                    long differenceInMillis = finishTime - startTime;

                    Toast.makeText(getApplicationContext(), "Circle X: " + currentGame.getDotWidth() + " Circle Y: " + currentGame.getDotHeight(), Toast.LENGTH_SHORT).show();
                    int distance = currentGame.getTouchDistanceFromDot(x, y);

                    countdownTimerText.setText(differenceInMillis + "X - " + x + " Y - " + y + "Difference: " + distance);

                    if(distance <= 500) {
                        touchLocationGoodImage.setX(x);
                        touchLocationGoodImage.setY(y);
                        touchLocationGoodImage.setVisibility(View.VISIBLE);
                    } else {
                        touchLocationBadImage.setX(x);
                        touchLocationBadImage.setY(y);
                        touchLocationBadImage.setVisibility(View.VISIBLE);
                    }

                } else {
                    isFalseStart = true;
                }

                return false;
            }
        });


    }

    public void resetDotsLayout() {
        touchLocationGoodImage.setVisibility(View.INVISIBLE);
        touchLocationBadImage.setVisibility(View.INVISIBLE);
        dotCircleImage.setVisibility(View.INVISIBLE);
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