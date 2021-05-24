package com.wickappz.dot;

import java.util.Random;

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

    public void DotEngine() {

    }

    public int[] getDotPlacement(float width, float height) {
        int[] circlePlacement = new int[2];
        Random rand = new Random();

        dotWidth = rand.nextInt((int) width);
        dotHeight = rand.nextInt((int) height);

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

}
