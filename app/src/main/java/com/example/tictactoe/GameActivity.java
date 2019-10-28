/**
 * Activity in which the game of tic tac toe is played
 * Gives users the option to play the game again or to exit back to the front page of the app (main activity)
 */

package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class GameActivity extends AppCompatActivity {

    boolean player1Turn = true;
    Cell [][] grid = initializeGrid(3);
    TicTacToeBoard myBoard = new TicTacToeBoard(3, grid);
    Coordinates coordinates = new Coordinates();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        Button button = (Button) findViewById(R.id.quitButton);
        ImageView zeroZero = (ImageView) findViewById(R.id.imageView);
        Intent intent = getIntent();
        TextView dispText = (TextView) findViewById(R.id.displayText);


        String p1Name = intent.getStringExtra("player1Name");
        String p2Name = intent.getStringExtra("player2Name");
        dispText.setText(p1Name + "'s turn");
        ImageView playerView = (ImageView) findViewById(R.id.playerView);
        playerView.setImageResource(R.drawable.dog);


        Button playAgainButton = (Button) findViewById(R.id.playAgainButton);
        playAgainButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GameActivity.this.recreate();
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GameActivity.this.finish();
            }
        });


    }

    /**
     * onButtonClick method that calls when an ImageView in the 3x3 square of ImageViews is clicked
     * Checks to see if their is a winner of the game or if it is a scratch game
     * Checks to see if the users clicks a square that hasn't already been chosen
     * Updates the ImageViews and textView with the appropriate values
     * @param view
     */
    public void onButtonClick(View view){
        ImageView imageView = (ImageView) view;
        String rowS = imageView.getTag().toString();
        String columnS = imageView.getTag().toString();

        int row = Character.getNumericValue(rowS.charAt(0));
        int column = Character.getNumericValue(columnS.charAt(1));

        ImageView playerView = (ImageView) findViewById(R.id.playerView);
        TextView textView = (TextView) findViewById(R.id.displayText);
        Intent intent = getIntent();
        String p1Name = intent.getStringExtra("player1Name");
        String p2Name = intent.getStringExtra("player2Name");

        Button playAgainButton = (Button) findViewById(R.id.playAgainButton);
        TextView dispText = (TextView) findViewById(R.id.displayText);

        coordinates.setRow(row);
        coordinates.setColumn(column);


        if (!myBoard.gameOver() && !myBoard.isWinner('O') && !myBoard.isWinner('X')) {
            if (player1Turn) {

                if (myBoard.isValidMove(coordinates)) {
                    imageView.setImageResource(R.drawable.dog);
                    playerView.setImageResource(R.drawable.clown_fish);
                    textView.setText(p2Name + "'s turn");

                    myBoard.makeMove(coordinates, 'O');

                    player1Turn = false;
                }
                else
                {
                    Toast.makeText(this, "Invalid move, try again", Toast.LENGTH_SHORT).show();
                }
            } else {

                if (myBoard.isValidMove(coordinates)) {
                    imageView.setImageResource(R.drawable.clown_fish);
                    playerView.setImageResource(R.drawable.dog);
                    textView.setText(p1Name + "'s turn");

                    myBoard.makeMove(coordinates, 'X');

                    player1Turn = true;
                }
                else
                {
                    Toast.makeText(this, "Invalid move, try again", Toast.LENGTH_SHORT).show();
                }
            }
        }

        if (myBoard.isWinner('O')){
            dispText.setText("Congrats " + p1Name + "! You Won! Play again?");
            playAgainButton.setVisibility(View.VISIBLE);
        }

        if (myBoard.isWinner('X')){
            dispText.setText("Congrats " + p2Name + "! You Won! Play again?");
            playAgainButton.setVisibility(View.VISIBLE);
        }
        if (myBoard.gameOver()) {
            dispText.setText("Scratch game, too bad!");
            playAgainButton.setVisibility(View.VISIBLE);
        }


    }

    /**
     * initializes the 2D array of cells with '-' in each for the tic tac toe board
     * @param gridSize the size of the grid, if gridSize is 3, then the grid will be 3x3
     * @return the set up 2D array of type Cell
     */
    public static Cell[][] initializeGrid(int gridSize){

        Cell[][] grid;

        grid = new Cell[gridSize][gridSize];

        for(int i = 0; i < gridSize; i++)
        {
            for(int j = 0; j < gridSize; j++)
            {
                grid[i][j] = new Cell();
                grid[i][j].setSymbol('-');
            }
        }

        return grid;
    }

}
