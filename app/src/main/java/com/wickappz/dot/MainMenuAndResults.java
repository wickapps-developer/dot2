package com.wickappz.dot;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainMenuAndResults extends AppCompatActivity {

    Button gameMode1Button, gameMode2Button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu_and_results);

        gameMode1Button = findViewById(R.id.gameMode1Button);
        gameMode2Button = findViewById(R.id.gameMode2Button);

        gameMode1Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent newIntent = new Intent(MainMenuAndResults.this, MainActivity.class);
                newIntent.putExtra("GAME_MODE_CHOICE", 1);
                startActivity(newIntent);
            }
        });

        gameMode2Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent newIntent = new Intent(MainMenuAndResults.this, MainActivity.class);
                newIntent.putExtra("GAME_MODE_CHOICE", 2);
                startActivity(newIntent);
            }
        });

    }
}