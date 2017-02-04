package com.example.android.courtcounter;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.CountDownTimer;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.view.ContextThemeWrapper;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;
import java.util.concurrent.TimeUnit;


import static com.example.android.courtcounter.R.id.player;



public class MainActivity extends AppCompatActivity {
    private int scoreTeamA = 0;
    private int scoreTeamB = 0;
    private int points;
    private int team = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TextView mTextField = (TextView) findViewById(R.id.countDown);


        new CountDownTimer(720000, 1000) {
            public void onTick(long millisUntilFinished) {
                mTextField.setText(String.format(Locale.getDefault(), "%02d : %02d",
                        TimeUnit.MILLISECONDS.toMinutes( millisUntilFinished),
                        TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) -
                                TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished))));

                if((TimeUnit.MILLISECONDS.toMinutes( millisUntilFinished)==1) &&
                        TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) -
                                TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished))==0)
                {

                    Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                    // Vibrate for 500 milliseconds
                    v.vibrate(500);
                }
            }


            public void onFinish() {
                mTextField.setText("FINISH");

            }
        }.start();

        Intent intent = getIntent();
        TextView teamAEditable = (TextView) findViewById(R.id.teamAName);
        TextView teamBEditable = (TextView) findViewById(R.id.teamBName);

        String str = intent.getStringExtra("team_A_saved");
        String str_sig = intent.getStringExtra("team_B_saved");


        if (str != null && !str.isEmpty() && !str.equals("null")){
            teamAEditable.setText(str);
        }
        if (str_sig != null && !str_sig.isEmpty() && !str_sig.equals("null")){
            teamBEditable.setText(str_sig);
        }

        displayForTeamA(0);
        displayForTeamB(0);
        RelativeLayout logo = (RelativeLayout) findViewById(R.id.logoTeamA);
        logo.setVisibility((getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) ? View.GONE : View.VISIBLE);
        RelativeLayout logo2 = (RelativeLayout) findViewById(R.id.logoTeamB);
        logo2.setVisibility((getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) ? View.GONE : View.VISIBLE);

        final LinearLayout lm = (LinearLayout) findViewById(player);

        // create the layout params that will be used to define how your
        // button will be displayed
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);

        //Create four
        for(int j=0;j<=4;j++)
        {
            // Create LinearLayout
            LinearLayout ll = new LinearLayout(new ContextThemeWrapper(MainActivity.this, R.style.basePlayer), null, 0);
            ll.setOrientation(LinearLayout.HORIZONTAL);
            // Create TextView
            TextView playerNumber = new TextView(new ContextThemeWrapper(MainActivity.this, R.style.playerNumber), null, 0);
            int id = getResources().getIdentifier("player"+j, "string", getPackageName());
            int name = getResources().getIdentifier("player_name"+j, "string", getPackageName());
            final String playerString = getString(id);
            final String playerNameString = getString(name);
            playerNumber.setText(playerString);
            ll.addView(playerNumber);
            // Create Button
            final Button btn = new Button(new ContextThemeWrapper(MainActivity.this, R.style.baseButton), null, 0);
            // Give button an ID
            btn.setId(j+1);
            btn.setText("F");
            // set the layoutParams on the button
            btn.setLayoutParams(params);
            final Button btn2 = new Button(new ContextThemeWrapper(MainActivity.this, R.style.baseButton), null, 0);
            // Give button an ID
            btn2.setId(j+1);
            btn2.setText("2");
            // set the layoutParams on the button
            btn2.setLayoutParams(params);
            final Button btn3 = new Button(new ContextThemeWrapper(MainActivity.this, R.style.baseButton), null, 0);
            // Give button an ID
            btn3.setId(j+1);
            btn3.setText("3");
            // set the layoutParams on the button
            btn3.setLayoutParams(params);
            // Set click listeners for buttons
            btn3.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    points = 3;
                    team = 1;
                    scoreTeamA = scoreTeamA + points;
                    displayForTeamA(scoreTeamA);
                    Toast.makeText(getApplicationContext(),
                            "+3 points by " + playerNameString,
                            Toast.LENGTH_SHORT).show();
                }
            });
            btn2.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    points = 2;
                    team = 1;
                    scoreTeamA = scoreTeamA + points;
                    displayForTeamA(scoreTeamA);
                    Toast.makeText(getApplicationContext(),
                            "+2 points by " + playerNameString,
                            Toast.LENGTH_SHORT).show();
                }
            });
            btn.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    points = 1;
                    team = 1;
                    scoreTeamA = scoreTeamA + points;
                    displayForTeamA(scoreTeamA);
                    Toast.makeText(getApplicationContext(),
                            "Free Shot by " + playerNameString,
                            Toast.LENGTH_SHORT).show();
                }
            });
            //Add buttons to LinearLayout
            ll.addView(btn3);
            ll.addView(btn2);
            ll.addView(btn);
            //Add buttons to LinearLayout defined in XML
            lm.addView(ll);
        }




        final LinearLayout lm2 = (LinearLayout) findViewById(R.id.player2);

        // create the layout params that will be used to define how your
        // button will be displayed
        LinearLayout.LayoutParams params2 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);

        //Create four
        for(int q=0;q<=4;q++)
        {
            // Create LinearLayout
            LinearLayout ll = new LinearLayout(new ContextThemeWrapper(MainActivity.this, R.style.basePlayer), null, 0);
            ll.setOrientation(LinearLayout.HORIZONTAL);
            // Create TextView
            TextView playerNumber = new TextView(new ContextThemeWrapper(MainActivity.this, R.style.playerNumber), null, 0);
            int id = getResources().getIdentifier("playerB"+q, "string", getPackageName());
            int name = getResources().getIdentifier("player_nameB"+q, "string", getPackageName());
            final String playerString = getString(id);
            final String playerNameString = getString(name);
            playerNumber.setText(playerString);
            ll.addView(playerNumber);
            // Create Button
            final Button btn = new Button(new ContextThemeWrapper(MainActivity.this, R.style.baseButton), null, 0);
            // Give button an ID
            btn.setId(q+1);
            btn.setText("F");
            // set the layoutParams on the button
            btn.setLayoutParams(params2);
            final Button btn2 = new Button(new ContextThemeWrapper(MainActivity.this, R.style.baseButton), null, 0);
            // Give button an ID
            btn2.setId(q+1);
            btn2.setText("2");
            // set the layoutParams on the button
            btn2.setLayoutParams(params2);
            final Button btn3 = new Button(new ContextThemeWrapper(MainActivity.this, R.style.baseButton), null, 0);
            // Give button an ID
            btn3.setId(q+1);
            btn3.setText("3");
            // set the layoutParams on the button
            btn3.setLayoutParams(params2);
            // Set click listeners for buttons
            btn3.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    points = 3;
                    team = 2;
                    scoreTeamB = scoreTeamB + points;
                    displayForTeamB(scoreTeamB);
                    Toast.makeText(getApplicationContext(),
                            "+3 points by " + playerNameString,
                            Toast.LENGTH_SHORT).show();
                }
            });
            btn2.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    points = 2;
                    team = 2;
                    scoreTeamB = scoreTeamB + points;
                    displayForTeamB(scoreTeamB);
                    Toast.makeText(getApplicationContext(),
                            "+2 points by " + playerNameString,
                            Toast.LENGTH_SHORT).show();
                }
            });
            btn.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    points = 1;
                    team = 2;
                    scoreTeamB = scoreTeamB + points;
                    displayForTeamB(scoreTeamB);
                    Toast.makeText(getApplicationContext(),
                            "Free Shot by " + playerNameString,
                            Toast.LENGTH_SHORT).show();
                }
            });
            //Add buttons to LinearLayout
            ll.addView(btn3);
            ll.addView(btn2);
            ll.addView(btn);
            //Add buttons to LinearLayout defined in XML
            lm2.addView(ll);
        }





    }

    public void resetTeamPoints(View v){
        scoreTeamB = 0;
        scoreTeamA = 0;
        team = 0;
        points =0;
        displayForTeamB(scoreTeamB);
        displayForTeamA(scoreTeamA);
        Toast.makeText(getApplicationContext(),
                "Score Reset",
                Toast.LENGTH_SHORT).show();

    }
    public void undoLastAction(View v){
        TextView teamAEditable = (TextView) findViewById(R.id.teamAName);
        TextView teamBEditable = (TextView) findViewById(R.id.teamBName);
        String teamAEditableString = teamAEditable.getText().toString();
        String teamBEditableString = teamBEditable.getText().toString();
        if(team == 1 && points > 1){
            scoreTeamA = scoreTeamA - points;
            team = 0;
            Toast.makeText(getApplicationContext(),
                    "Removed " + points + " points from " + teamAEditableString,
                    Toast.LENGTH_SHORT).show();
        }else if(team == 1 && points == 1){
            scoreTeamA = scoreTeamA - points;
            team = 0;
            Toast.makeText(getApplicationContext(),
                    "Removed one point from " + teamBEditableString,
                    Toast.LENGTH_SHORT).show();
        }else if(team == 2 && points > 1){
            scoreTeamB = scoreTeamB - points;
            team = 0;
            Toast.makeText(getApplicationContext(),
                    "Removed " + points + " points from " + teamBEditableString,
                    Toast.LENGTH_SHORT).show();
        }else if(team == 2 && points == 1){
            scoreTeamB = scoreTeamB - points;
            team = 0;
            Toast.makeText(getApplicationContext(),
                    "Removed one point from " + teamBEditableString,
                    Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(getApplicationContext(),
                    "Can't undo more than the last shoot",
                    Toast.LENGTH_SHORT).show();
        }

        displayForTeamB(scoreTeamB);
        displayForTeamA(scoreTeamA);
    }
    private void displayForTeamA(int score){
        TextView scoreView = (TextView) findViewById(R.id.team_a_score);
        scoreView.setText(String.valueOf(score));
    }
    private void displayForTeamB(int score){
        TextView scoreView = (TextView) findViewById(R.id.team_b_score);
        scoreView.setText(String.valueOf(score));
    }

    public void editTextClick(View v) {

        startActivity(new Intent(getApplicationContext(), TeamAPlayers.class));
    }
}
