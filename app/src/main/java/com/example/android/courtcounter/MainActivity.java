package com.example.android.courtcounter;

import android.app.Activity;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Chronometer;
import android.widget.TextView;

public class MainActivity extends Activity {

    /*
    A bit of redundancy here with "Team" in every variable name bu wanted to make it consistent.
     */

    int scoreHomeTeam = 0;
    int scoreAwayTeam = 0;

    int yellowCountHomeTeam = 0;
    int yellowCountAwayTeam = 0;

    int redCountHomeTeam = 0;
    int redCountAwayTeam = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

//    public void displayScore(String team, int score) {
//        TextView scoreView = (TextView) findViewById(getResources().getIdentifier(team+"_score", "id", getPackageName()));
//        scoreView.setText(String.valueOf(score));
//    }

    /*
    Timer start and stop
    */

    public void startTimer(View view){
        Chronometer timer = findViewById(R.id.timer);
        timer.setBase(SystemClock.elapsedRealtime());
        timer.start();
    }

    public void stopTimer(View view){
        Chronometer timer = findViewById(R.id.timer);
        timer.setBase(SystemClock.elapsedRealtime());
        timer.stop();
    }

    /*
    Home team methods.
    */

    public void displayScoreHomeTeam(int score) {
        TextView scoreView = (TextView) findViewById(R.id.home_score);
        scoreView.setText(String.valueOf(score));
    }

    public void yellowCardHomeTeam(int yellowCountHomeTeam) {
        TextView yellowCard= (TextView) findViewById(R.id.home_yellow_card);
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
    Away team methods. Literally TON of redundancy here, would rather wrote methods that take team as parameter like:

    public void displayScore(String team, int score) {
    TextView scoreView = (TextView) findViewById(getResources().getIdentifier(team+"_score", "id", getPackageName()));
    scoreView.setText(String.valueOf(score));
    }

    but I want to move to the next projects :-)
    */

    public void displayScoreAwayTeam(int score) {
        TextView scoreView = (TextView) findViewById(R.id.away_score);
        scoreView.setText(String.valueOf(score));
    }

    /*
    Card count displayer with formatting that depends on value -  wanted to get rid of YELLOW CARD (0) after resetting
    */

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
        stopTimer(view);
    }

}