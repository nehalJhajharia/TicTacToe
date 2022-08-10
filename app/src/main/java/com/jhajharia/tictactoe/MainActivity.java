package com.jhajharia.tictactoe;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {
    boolean gameActive = true;
    // Top message to show player's turn
    // true --> O
    // false --> X
    boolean activePlayer = true;
    int gameState[] = {2, 2, 2, 2, 2, 2, 2, 2, 2};
    // gameState meanings
    // 0 --> O
    // 1 --> X
    // 2 --> Blank
    final int winPositions[][] = {{0, 1, 2},
                                  {3, 4, 5},
                                  {6, 7, 8},
                                  {0, 3, 6},
                                  {1, 4, 7},
                                  {2, 5, 8},
                                  {0, 4, 8},
                                  {2, 4, 6}};

    public void gameReset(View view) {
        gameActive = true;
        TextView topStatus = findViewById(R.id.heading1);
        topStatus.setText("WELCOME");
        topStatus.setTextColor(Color.rgb(127, 127, 127));
        TextView bottomMsg = findViewById(R.id.textView);
        bottomMsg.setText("Tic-Tac-Toe");
        activePlayer = true;
        for (int i = 0; i < 9; i++) {
            gameState[i] = 2;
        }

        ((ImageView)findViewById(R.id.imageView0)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView1)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView2)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView3)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView4)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView5)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView6)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView7)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView8)).setImageResource(0);
    }

    public void onTap(View view) {
        TextView topStatus = findViewById(R.id.heading1);
        TextView bottomMsg = findViewById(R.id.textView);
        if (gameActive == false) {
            gameReset(view);
            return;
        }

        if (Integer.parseInt(view.getTag().toString()) == 9) {
            return;
        }

        topStatus.setText("'O's turn");
        ImageView img = (ImageView) view;
        int tappedImg = Integer.parseInt(img.getTag().toString());
        if (gameState[tappedImg] == 2) {
            if (activePlayer == true) { // O
                img.setImageResource(R.drawable.zero);
                gameState[tappedImg] = 0;
                activePlayer = false;
                topStatus.setText("'X's turn");
                topStatus.setTextColor(Color.rgb(255, 97, 95));
            }
            else {                      // X
                img.setImageResource(R.drawable.cross);
                gameState[tappedImg] = 1;
                activePlayer = true;
                topStatus.setText("'O's turn");
                topStatus.setTextColor(Color.rgb(64, 197, 243));
            }
        }

        // Check win position
        for (int i = 0; i < 8; i++) {
            if (gameState[winPositions[i][0]] == gameState[winPositions[i][1]] &&
                gameState[winPositions[i][0]] == gameState[winPositions[i][2]] &&
                gameState[winPositions[i][0]] != 2) {
                // A victory is encountered
                // Check who won
                String winMessage;
                if (gameState[winPositions[i][0]] == 0) {
                    // O wins
                    winMessage = "!!!'O' WON!!!";
                    topStatus.setTextColor(Color.rgb(64, 197, 243));
                }
                else {
                    // X wins
                    winMessage = "!!!'X' WON!!!";
                    topStatus.setTextColor(Color.rgb(255, 97, 95));
                }

                topStatus.setText(winMessage);
                //gameReset(view);
                gameActive = false;
                bottomMsg.setText("Tap to restart!");
                return;
            }
        }
        boolean emptyFlag = false;
        for (int i = 0; i < 9; i++) {
            if (gameState[i] == 2) {
                emptyFlag = true;
                break;
            }
        }
        if (emptyFlag == false) {
            gameActive = false;
            topStatus.setText("!!!DRAW!!!");
            bottomMsg.setText("Tap to restart!");
            return;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}