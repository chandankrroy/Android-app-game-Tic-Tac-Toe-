package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    boolean gameActive=true;
    MediaPlayer player;
    MediaPlayer player1;
    MediaPlayer player2;

    //PLAYER REPRESENTATION
    // 0-X
    // 1-O
    int activeplayer=0;
    int gameState[]={2,2,2,2,2,2,2,2,2};
    // STATE MEANINGS
    //  0-X
    //  1-O
    //  2-BLANK CELL
    int winpositions[][]={{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7}
            ,{2,5,8},{0,4,8},{2,4,6}};
    public void playerTap(View view) {
        player2.start();
        //JIS GRID PE CLICK HOGA USKA VIEW PLAYERTAP () ME AEGA AS
        // PARAMETER AS A VIEW  USKO TYPE KARDENGE IMAGE VIEW.
        ImageView img = (ImageView) view;
        int tappedImage = Integer.parseInt(img.getTag().toString());
        if (!gameActive) {
            gameReset(view);
            return;
        }
        if (gameState[tappedImage] == 2 && gameActive) {
            gameState[tappedImage] = activeplayer;
            img.setTranslationY(-1000f); //pixels ghatane badhane ke liye.
            if (activeplayer == 0) {
                img.setImageResource(R.drawable.gx);
                activeplayer = 1;
                TextView status = findViewById(R.id.status);
                status.setText("O's Turn-Tap to play");
            } else {
                img.setImageResource(R.drawable.go);
                activeplayer = 0;
                TextView status = findViewById(R.id.status);
                status.setText("X's Turn-Tap to play");
            }

            img.animate().translationYBy(+1000f).setDuration(300);
        // CHECK IF ANY PLAYER HAS WON.
        for (int[] winposition : winpositions) {
            if ((gameState[winposition[0]] == gameState[winposition[1]]) && (gameState[winposition[1]] == gameState[winposition[2]])
                    && (gameState[winposition[0]] != 2)) {
                // Somnody has won.- FInd out WHO!
                String winnerstr;
                gameActive = false;
                if (gameState[winposition[0]] == 0) {
                    winnerstr = "X HAS WON";
                } else {
                    winnerstr = "O HAS WON";
                }
                //UPDATE THE STATUS BAR FO WINNER
                TextView status = findViewById(R.id.status);
                status.setText(winnerstr);
                player.start();
                return;
            }
        }
        boolean isTie = true;
        for (int state : gameState) {
            if (state == 2) {
                isTie = false;
                break;
            }
        }
        if (isTie) {
            gameActive = false;
            TextView status = findViewById(R.id.status);
            status.setText("It's a Tie! Tap To Play Again.");
            player1.start();
        }
    }
        }

    public void gameReset(View view)
    {
        gameActive=true;
        activeplayer=0;
        for(int i=0; i<gameState.length; i++)
        {
            gameState[i] = 2;
        }
        ((ImageView)findViewById(R.id.imageView1)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView2)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView3)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView4)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView5)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView6)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView7)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView8)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView9)).setImageResource(0);
        TextView status = findViewById(R.id.status);
        status.setText("X's Turn-Tap to play");
        player.release();
    }
    @Override
    protected void onCreate (Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        player=MediaPlayer.create(this,R.raw.mus);
        player1=MediaPlayer.create(this,R.raw.tie);
        player2=MediaPlayer.create(this,R.raw.tapsound);
    }
}