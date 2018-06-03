package io.github.superbderrick.scoreboard.theme;

import android.content.Context;
import android.util.Log;

/**
 * Created by derrick on 03/06/2018.
 */

public class ThemeOperator {
    private int mThemeStyle = 0;
    private Context mContext = null;

    public ThemeOperator(int mThemeStyle , Context context) {
        init(mThemeStyle , context);
    }

    private void init(int theme , Context context) {
        this.mThemeStyle = mThemeStyle;

        applyWholeColor();
    }

    private void applyWholeColor() {

    }

}
