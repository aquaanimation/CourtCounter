package com.example.android.courtcounter;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;

import android.widget.Toast;

import java.util.HashMap;



public class TeamAPlayers extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team_aplayers);
        EditText TeamAMessage = (EditText) findViewById(R.id.teamAEdit);
        EditText TeamBMessage = (EditText) findViewById(R.id.teamBEdit);


        final SharedPreferences prefs = PreferenceManager
                .getDefaultSharedPreferences(this);

        TeamAMessage.setText(prefs.getString("autoSave", ""));
        TeamAMessage.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before,
                                      int count) {
                prefs.edit().putString("autoSave", s.toString()).apply();
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
        TeamBMessage.setText(prefs.getString("autoSaveSig", ""));
        TeamBMessage.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before,
                                      int count) {
                prefs.edit().putString("autoSaveSig", s.toString()).apply();
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });


    }
    int i =0;
    EditText newElement;
    String playerNumber;
    HashMap<String, Integer> newElementsMap;
    private void addEditView() {
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        i ++;
        newElementsMap = new HashMap<>();
        playerNumber = "playerNumber" + Integer.toString(i);
        newElement = new EditText(this);
        newElement.setId(i);
        newElementsMap.put(playerNumber, i);
        newElement.setLayoutParams(params);
        Toast.makeText(getApplicationContext(),
                "element " + playerNumber + "," + newElement.getText().toString(),
                Toast.LENGTH_SHORT).show();
        LinearLayout container = (LinearLayout)findViewById(R.id.activity_team_aplayers);
        container.addView(newElement);
        final SharedPreferences prefs = PreferenceManager
                .getDefaultSharedPreferences(this);
        newElement.setText(prefs.getString("autoSave"+i, ""));
        newElement.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before,
                                      int count) {
                prefs.edit().putString("autoSave"+i, s.toString()).apply();
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

    }
    public void saveTextClick(View v) {

        EditText teamA = (EditText) findViewById(R.id.teamAEdit);
        EditText teamB = (EditText) findViewById(R.id.teamBEdit);
        Intent intent = new Intent(TeamAPlayers.this, MainActivity.class);
        intent.putExtra("team_A_saved", teamA.getText().toString());
        intent.putExtra("team_B_saved", teamB.getText().toString());


        /* Dev code
        intent.putExtra( playerNumber, newElement.getText().toString());
        Toast.makeText(getApplicationContext(),
                "putExtra " + playerNumber + newElementsMap.get(playerNumber),
                Toast.LENGTH_SHORT).show();*/
        startActivity(intent);


    }
    public void addEdit(View v) {

        addEditView();

    }


}