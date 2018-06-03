package io.github.superbderrick.scoreboard.theme;

import android.content.Context;

/**
 * Created by derrick on 03/06/2018.
 */

public class ThemeOperator {
    private int mThemeStyle = 0;
    private Context mContext = null;

    private ThemeFactory mThemeFactory;
    private Theme mCurrentGameTheme;


    public ThemeOperator(int mThemeStyle , Context context) {
        init(mThemeStyle , context);
    }

    private void init(int theme , Context context) {

        mCurrentGameTheme = ThemeFactory.getTheme(theme);
        mCurrentGameTheme.setupThemeColors();

        applyWholeColor();
    }

    private void applyWholeColor() {
        


    }

    public void applyTheme() {

    }

}

 class ThemeFactory {
    public static Theme getTheme(int gameTheme) {

        if(gameTheme > 0) {
            return new LightTheme();
        } else {
            return new DarkTheme();
        }
    }
}
