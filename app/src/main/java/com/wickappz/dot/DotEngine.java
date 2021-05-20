package com.wickappz.dot;

public class DotEngine {

    static int MODE_MAIN_MENU = 0;
    static int MODE_GAME_1 = 1;
    static int MODE_GAME_2 = 2;

    Long startTime;
    Long finishTime;
    int livesRemaining;

    boolean inTapMode;
    boolean isFalseStart;

    public void DotEngine() {

    }

    private int gameMode;

    public  int getGameMode() {
        return gameMode;
    }

    public void setGameMode(int gameMode) {
        this.gameMode = gameMode;
    }

}
