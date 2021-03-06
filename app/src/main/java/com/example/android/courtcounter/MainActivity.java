package com.example.android.courtcounter;

import android.app.Activity;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Chronometer;
import android.widget.TextView;

public class MainActivity extends Activity {

    /*
    A bit of redundancy here with "Team" in every variable name but wanted to make it consistent.
    */

    private int scoreHomeTeam = 0;
    private int scoreAwayTeam = 0;
    private int yellowCountHomeTeam = 0;
    private int yellowCountAwayTeam = 0;
    private int redCountHomeTeam = 0;
    private int redCountAwayTeam = 0;

    /*
    Values for saved states on rotates
    */

    private final String SCORE_HOME_TEAM = "scoreHomeTeam";
    private final String SCORE_AWAY_TEAM = "scoreAwayTeam";
    private final String YELLOW_CNT_HOME = "yellowCountHomeTeam";
    private final String YELLOW_CNT_AWAY = "yellowCountAwayTeam";
    private final String RED_CNT_HOME = "redCountHomeTeam";
    private final String RED_CNT_AWAY = "redCountAwayTeam";
    private final String TIME_PASSED = "timerState";

    /*
    Setting layout elements
    */

    private Chronometer timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        timer = (Chronometer) findViewById(R.id.timer);
    }

    /*
    Saving variable  states and timer upon turning phone landscape - solution found on stack overflow
    */

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);

        savedInstanceState.putInt(SCORE_HOME_TEAM, scoreHomeTeam);
        savedInstanceState.putInt(SCORE_AWAY_TEAM, scoreAwayTeam);
        savedInstanceState.putInt(YELLOW_CNT_HOME, yellowCountHomeTeam);
        savedInstanceState.putInt(YELLOW_CNT_AWAY, yellowCountAwayTeam);
        savedInstanceState.putInt(RED_CNT_HOME, redCountHomeTeam);
        savedInstanceState.putInt(RED_CNT_AWAY, redCountAwayTeam);
        savedInstanceState.putLong(TIME_PASSED, timer.getBase());
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        scoreHomeTeam = savedInstanceState.getInt(SCORE_HOME_TEAM);
        scoreAwayTeam = savedInstanceState.getInt(SCORE_AWAY_TEAM);
        yellowCountHomeTeam = savedInstanceState.getInt(YELLOW_CNT_HOME);
        yellowCountAwayTeam = savedInstanceState.getInt(YELLOW_CNT_AWAY);
        redCountHomeTeam = savedInstanceState.getInt(RED_CNT_HOME);
        redCountAwayTeam = savedInstanceState.getInt(RED_CNT_AWAY);
        timer.setBase(savedInstanceState.getLong(TIME_PASSED));

        displayScoreHomeTeam(scoreHomeTeam);
        displayScoreAwayTeam(scoreAwayTeam);
        yellowCardHomeTeam(yellowCountHomeTeam);
        yellowCardAwayTeam(yellowCountAwayTeam);
        redCardHomeTeam(redCountHomeTeam);
        redCardAwayTeam(redCountAwayTeam);
        timer.start();

    }

    /*
    Timer start and stop
    */

    public void startTimer(View view){
        timer.setBase(SystemClock.elapsedRealtime());
        timer.start();
    }

    /*
    Home team methods.
    */

    public void displayScoreHomeTeam(int score) {
        TextView scoreView = (TextView) findViewById(R.id.home_score);
        scoreView.setText(String.valueOf(score));
    }

    /*
    Yellow and red cards method use string formatting to display current card count like  RED CARD(2)
    and if statement so it doesn't display 0 in brackets when there is no card or after resetting values
    */

    public void yellowCardHomeTeam(int yellowCountHomeTeam) {
        TextView yellowCard = (TextView) findViewById(R.id.home_yellow_card);
        if (yellowCountHomeTeam == 0){
            yellowCard.setText(R.string.yellow_card);
        } else {
            String text = String.format(getResources().getString(R.string.yellow_card_count), yellowCountHomeTeam);
            yellowCard.setText(text);
        }
    }

    public void redCardHomeTeam(int redCountHomeTeam) {
        TextView redCard = (TextView) findViewById(R.id.home_red_card);
        if (redCountHomeTeam == 0){
            redCard.setText(R.string.red_card);
        } else {
            String text = String.format(getResources().getString(R.string.red_card_count), redCountHomeTeam);
            redCard.setText(text);
        }
    }

    public void goalHomeTeam(View view) {
        scoreHomeTeam +=1;
        displayScoreHomeTeam(scoreHomeTeam);
    }

    public void addYellowCardHomeTeam(View view) {
        if (yellowCountHomeTeam == 0) {
            yellowCountHomeTeam += 1;
            yellowCardHomeTeam(yellowCountHomeTeam);
        } else {
            redCountHomeTeam += 1;
            redCardHomeTeam(redCountHomeTeam);
            yellowCountHomeTeam = 0;
            yellowCardHomeTeam(yellowCountHomeTeam);
        }
    }

    public void addRedCardHomeTeam(View view) {
        redCountHomeTeam +=1;
        redCardHomeTeam(redCountHomeTeam);
    }

    /*
    Away team methods. A lot of redundancy here as it's copy paste of , would rather wrote methods that take team as parameter like:

    public void displayScore(String team, int score) {
    TextView scoreView = (TextView) findViewById(getResources().getIdentifier(team+"_score", "id", getPackageName()));
    scoreView.setText(String.valueOf(score));
    }


    */

    public void displayScoreAwayTeam(int score) {
        TextView scoreView = (TextView) findViewById(R.id.away_score);
        scoreView.setText(String.valueOf(score));
    }

    public void yellowCardAwayTeam(int yellowCountAwayTeam) {
        TextView yellowCard= (TextView) findViewById(R.id.away_yellow_card);
        if (yellowCountAwayTeam == 0){
            yellowCard.setText(R.string.yellow_card);
        } else {
            String text = String.format(getResources().getString(R.string.yellow_card_count), yellowCountAwayTeam);
            yellowCard.setText(text);
        }
    }

    public void redCardAwayTeam(int redCountAwayTeam) {
        TextView redCard = (TextView) findViewById(R.id.away_red_card);
        if (redCountAwayTeam == 0){
            redCard.setText(R.string.red_card);
        } else {
            String text = String.format(getResources().getString(R.string.red_card_count), redCountAwayTeam);
            redCard.setText(text);
        }
    }

    public void goalAwayTeam(View view) {
        scoreAwayTeam +=1;
        displayScoreAwayTeam(scoreAwayTeam);
    }

    public void addYellowCardAwayTeam(View view) {
        if (yellowCountAwayTeam == 0) {
            yellowCountAwayTeam += 1;
            yellowCardAwayTeam(yellowCountAwayTeam);
        } else {
            redCountAwayTeam += 1;
            redCardAwayTeam(redCountAwayTeam);
            yellowCountAwayTeam = 0;
            yellowCardAwayTeam(yellowCountAwayTeam);
        }
    }

    public void addRedCardAwayTeam(View view) {
        redCountAwayTeam +=1;
        redCardAwayTeam(redCountAwayTeam);
    }

    /*
    Reset all
    */

    public void resetScore(View view) {
        scoreHomeTeam = 0;
        scoreAwayTeam = 0;
        yellowCountHomeTeam = 0;
        yellowCountAwayTeam = 0;
        redCountHomeTeam = 0;
        redCountAwayTeam = 0;
        displayScoreHomeTeam(scoreHomeTeam);
        displayScoreAwayTeam(scoreAwayTeam);
        yellowCardHomeTeam(yellowCountHomeTeam);
        yellowCardAwayTeam(yellowCountAwayTeam);
        redCardHomeTeam(redCountHomeTeam);
        redCardAwayTeam(redCountAwayTeam);
        timer.stop();
        timer.setBase(SystemClock.elapsedRealtime());
    }

}