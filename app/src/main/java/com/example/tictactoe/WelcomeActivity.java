/**
 * Main activity of the app
 * Users enter their names and press start when they are ready to play
 * Moves to the next activity GameActivity
 */

package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class WelcomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        Button playButton = (Button) findViewById(R.id.playButton);
        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(WelcomeActivity.this, GameActivity.class);

                EditText p1Name = (EditText) findViewById(R.id.editText);
                EditText p2Name = (EditText) findViewById(R.id.editText2);

                String player1Name = p1Name.getText().toString(); //= (EditText) findViewById(R.id.editText);
                String player2Name = p2Name.getText().toString();

                if (!player1Name.isEmpty() && !player2Name.isEmpty()){

                    //Intent intent1 = new Intent(WelcomeActivity.this, GameActivity.class);
                    intent.putExtra("player1Name", player1Name);
                    intent.putExtra("player2Name", player2Name);

                    startActivity(intent);
                }


            }
        });

    }

}
