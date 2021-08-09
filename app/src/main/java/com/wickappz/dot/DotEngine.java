package com.wickappz.dot;

import android.util.Log;

import java.util.Random;
import java.util.logging.Logger;

import static android.content.ContentValues.TAG;

public class DotEngine {

    static int MODE_MAIN_MENU = 0;
    static int MODE_GAME_1 = 1;
    static int MODE_GAME_2 = 2;

    Long startTime;
    Long finishTime;

    int livesRemaining;

    int dotHeight;
    int dotWidth;
    int touchHeight;
    int touchWidth;

    private int gameMode;

    boolean inTapMode;
    boolean isFalseStart;

    public int level;
    public long score;

    public int levelTimeLimit;
    public int levelDistanceLimit;

    public DotEngine() {
        level = 1;
        score = 0;

        livesRemaining = 3;

        levelTimeLimit = 1000;
        levelDistanceLimit = 750;
    }

    public int[] getDotPlacement(float width, float height, float maxHeight) {
        int[] circlePlacement = new int[2];
        Random rand = new Random();

        // 150 dp can not be used since it is for the top scoring layout
        int usableHeight = (int) height - (int) maxHeight;

        dotWidth = rand.nextInt((int) width);
        dotHeight = rand.nextInt((int) usableHeight) + (int) maxHeight;

        circlePlacement[0] = dotWidth;
        circlePlacement[1] = dotHeight;

        return circlePlacement;
    }

    public int getTouchDistanceFromDot(int x, int y) {
        touchWidth= x;
        touchHeight = y;

        int difference = (int) Math.hypot(dotWidth - touchWidth, dotHeight - touchHeight);

        return difference;
    }

    // Returning true indicates the game is over
    public boolean getResults(long time, int distance) {

        if(time > levelTimeLimit || distance > levelDistanceLimit) {
            livesRemaining--;

            if(livesRemaining == 0) {
                return true;
            }
        } else {

            score += ((levelTimeLimit - time) + (levelDistanceLimit - distance)) * (level);
            levelIncrease();

        }

        return false;
    }

    public void levelIncrease() {
        level++;

        if(level % 5 == 0) {
            levelTimeLimit -= 25;
        }

        if(level % 10 == 0) {
            levelDistanceLimit -= 50;
        }

        System.out.println("New Level! Level " + level + ": Time - " + levelTimeLimit + " | Distance - " + levelDistanceLimit);
    }


    public  int getGameMode() {
        return gameMode;
    }

    public void setGameMode(int gameMode) {
        this.gameMode = gameMode;
    }

    public int getDotHeight() {
        return dotHeight;
    }

    public void setDotHeight(int dotHeight) {
        this.dotHeight = dotHeight;
    }

    public int getDotWidth() {
        return dotWidth;
    }

    public void setDotWidth(int dotWidth) {
        this.dotWidth = dotWidth;
    }

    public int getTouchHeight() {
        return touchHeight;
    }

    public void setTouchHeight(int touchHeight) {
        this.touchHeight = touchHeight;
    }

    public int getTouchWidth() {
        return touchWidth;
    }

    public void setTouchWidth(int touchWidth) {
        this.touchWidth = touchWidth;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public long getScore() {
        return score;
    }

    public void setScore(long score) {
        this.score = score;
    }

    public int getLivesRemaining() {
        return livesRemaining;
    }

    public void setLivesRemaining(int livesRemaining) {
        this.livesRemaining = livesRemaining;
    }

    public int getLevelTimeLimit() {
        return levelTimeLimit;
    }

    public void setLevelTimeLimit(int levelTimeLimit) {
        this.levelTimeLimit = levelTimeLimit;
    }

    public int getLevelDistanceLimit() {
        return levelDistanceLimit;
    }

    public void setLevelDistanceLimit(int levelDistanceLimit) {
        this.levelDistanceLimit = levelDistanceLimit;
    }

}
