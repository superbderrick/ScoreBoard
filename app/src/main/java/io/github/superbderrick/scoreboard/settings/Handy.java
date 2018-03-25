package io.github.superbderrick.scoreboard.settings;

/**
 * Created by derrick on 25/03/2018.
 */

public class Handy {

    public static final int LEFT_USER = 0;
    public static final int RIGHT_USER = 1;

    private int mDirection;
    private int mHandyPoint;

    public int getDirection() {
        return mDirection;
    }

    public void setDirection(int mDirection) {
        this.mDirection = mDirection;
    }

    public int getHandyPoint() {
        return mHandyPoint;
    }

    public void setHandyPoint(int mHandyPoint) {
        this.mHandyPoint = mHandyPoint;
    }

    public Handy() {
    }
}
