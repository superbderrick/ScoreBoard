package io.github.superbderrick.scoreboard.utils;

import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;


public class MatchTimer {

    //thread on which the callbacks will be called

    private Handler mainThreadHandler = new Handler(Looper.getMainLooper());



    //listener interface which is to be implemented by the users of the count down timer

    public interface TimerTickListener{

        /**

         * Callback on each tick

         * @param millisLeft time left in millisec for the timer to shutdown

         */

        void onTick(long millisLeft);



        /**

         * Callback to be invokded when timer's time finishes

         */

        void onFinish();



        /**

         * Callback to be invokded when timer is canceled

         */

        void onCancel();

    }



    /**

     * Inner class which delegates the events to callbacks provided in the TimerTickListener

     */

    private class TimerRunnable implements Runnable{

        public void run(){

            mainThreadHandler.post(new Runnable() {

                long millisLeft = stopTimeInFuture - SystemClock.elapsedRealtime();

                @Override

                public void run() {

                    if (isCancelled){

                        //shutdown the scheduler

                        tickListener.onCancel();

                        scheduler.shutdown();

                    }

                    else if (millisLeft <= 0) {

                        tickListener.onFinish();

                        scheduler.shutdown();

                    }

                    else{

                        tickListener.onTick(millisLeft);

                    }

                }

            });

        }

    }



    //Millis since epoch when alarm should stop.

    private long millisInFuture;



    //The interval in millis that the user receives callbacks

    private final long countdownInterval;



    //the time at which timer is to stop

    private long stopTimeInFuture;



    //boolean representing if the timer was cancelled

    private boolean isCancelled = false;



    //listener which listens to the timer events

    private TimerTickListener tickListener;



    //scheduler which provides the thread to create timer

    private ScheduledExecutorService scheduler;



    /**

     * Constructor

     * @param millisInFuture time in millisec for which timer is to run

     * @param countDownInterval interval frequency in millisec at which the callback will be invoked

     * @param tickListener implementation of TimerTickListener which provides callbacks code

     */

    public MatchTimer(long millisInFuture, long countDownInterval, TimerTickListener tickListener) {

        this.millisInFuture = millisInFuture;

        stopTimeInFuture = SystemClock.elapsedRealtime() + this.millisInFuture;

        countdownInterval = countDownInterval;

        this.tickListener = tickListener;

        scheduler = Executors.newSingleThreadScheduledExecutor();

    }



    /**

     * Start the countdown.

     */

    public synchronized void start() {

        isCancelled = false;

        scheduler.scheduleWithFixedDelay(new TimerRunnable(), 0, countdownInterval,

                TimeUnit.MILLISECONDS);

    }



    /**

     * Cancels the countdown timer

     */

    public synchronized final void cancel() {

        isCancelled = true;

    }



    /**

     * Extends the time of the countdown timer

     * @param delta time in millisec by which timer is to be extended

     */

    public void extendTime(long delta){

        stopTimeInFuture = stopTimeInFuture + delta;

        millisInFuture = millisInFuture + delta;

    }




}
