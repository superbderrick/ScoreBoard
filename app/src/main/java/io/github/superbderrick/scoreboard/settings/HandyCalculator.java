package io.github.superbderrick.scoreboard.settings;

/**
 * Created by derrick on 25/03/2018.
 */

final public class HandyCalculator {
    public static Handy getHandy(int point) {
        int direction = 0;

        if(point < 7) {
            direction = 0;
        } else {
            direction = 1;
            point = point - 6;
        }

        Handy handy = new Handy();
        handy.setHandyPoint(point);
        handy.setDirection(direction);

        return  handy;
    }
}
