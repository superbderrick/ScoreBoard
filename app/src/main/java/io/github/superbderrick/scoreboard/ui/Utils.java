package io.github.superbderrick.scoreboard.ui;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;

import io.github.superbderrick.scoreboard.activities.SettingActivity;

/**
 * Created by derrick on 25/05/2018.
 */

public final class Utils {
    public static void showDialog(final Activity activity, final String title, final String message)
    {
        final AlertDialog.Builder alertbox = new AlertDialog.Builder(activity);
        alertbox.setTitle(title);
        alertbox.setCancelable(false);
        alertbox.setMessage(message);
        alertbox.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                moveSettingScreen(activity);
            }
        });

        alertbox.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        alertbox.show();
    }

    private static void moveSettingScreen(Activity activity) {
        Intent intent = new Intent(activity , SettingActivity.class);
        activity.startActivity(intent);
    }

    public static String getHandaySettingSentence(String prefSentence) {


        final int handyValue = Integer.parseInt(prefSentence);
        String handSentence = "same";

        if(handyValue == 0) {
            handSentence = "No Handy - Same Score";
        } else if (handyValue > 6) {
            handSentence = "Right User " + " + "+ (handyValue - 6) ;
        } else {
            handSentence = "Left User" + " + "+ handyValue;
        }

        return handSentence;
    }
    public static String  getSetScoreSettingSentence(String prefSentence) {
        final int setValue = Integer.parseInt(prefSentence);
        String setSentence =  "Game Set : " + setValue;

        return setSentence;
    }

    public static String  getGameThemeSentence(String prefSentence) {
        final int setValue = Integer.parseInt(prefSentence);
        String theme = "Dark";
        if(setValue == 0) {
            theme = "Dark";
        } else {
            theme = "Light";
        }

        String gameTheme =  "Game Theme  " + theme;

        return gameTheme;
    }
}
