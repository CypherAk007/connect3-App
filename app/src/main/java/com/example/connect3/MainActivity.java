package com.example.connect3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    //0- yellow ,1- red
    int activePlayer =0;
//    to store the location of the coins placed
//    2-unplayed
    int[] gameState = {2,2,2,2,2,2,2,2,2};

    int [][] winningPositions={{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void dropIn(View view){
        ImageView counter= (ImageView) view;
//        when user taps on an empty image the view is captured
//        we used view here as it was the one that was tapped on so no need of findviewbyid.
//        now we move image view off the screen set the image and bring it back to the screen .


//         Toast out the tag
        Toast.makeText(this, counter.getTag().toString(), Toast.LENGTH_SHORT).show();
        int tappedCounter=Integer.parseInt(counter.getTag().toString());

        if (gameState[tappedCounter]==2) {
            gameState[tappedCounter] = activePlayer;
            counter.setTranslationY(-1000f);


            if (activePlayer == 0) {
                counter.setImageResource(R.drawable.yellowcoin);
                activePlayer = 1;
            } else {
                counter.setImageResource(R.drawable.redcoin);
                activePlayer = 0;
            }

            counter.animate().translationYBy(1000f).rotation(3600).setDuration(300);
            for (int[] winningPosition:winningPositions){
                if (gameState[winningPosition[0]]==gameState[winningPosition[1]] &&
                        gameState[winningPosition[1]]==gameState[winningPosition[2]] &&
                        gameState[winningPosition[0]]!=2){
                    Toast.makeText(this, "someone has won", Toast.LENGTH_SHORT).show();
//                someone has won

                    String winner ="Red";
                    if (gameState[winningPosition[0]]==0){// we are already inside the winner so any valueth index value is the winner
                        winner="yellow";
                    }

                    TextView winnerMessage=(TextView)findViewById(R.id.winnerMessage);
                    winnerMessage.setText(winner+" has won!!");
                    LinearLayout layout= (LinearLayout) findViewById(R.id.playAgainLayout);
                    layout.setVisibility(View.VISIBLE);
                }
            }

        }
    }
    public void playAgain (View view){
        LinearLayout layout= (LinearLayout) findViewById(R.id.playAgainLayout);
        layout.setVisibility(View.INVISIBLE);

        activePlayer =0;
        for(int i=0;i<gameState.length;i++){
            gameState[i]=2;
        }

        GridLayout gridLayout=(GridLayout) findViewById(R.id.gridLayout);
        for(int i=0;i<gridLayout.getChildCount();i++){//no. of items ie images-tells no. of views -child count
            ((ImageView)gridLayout.getChildAt(i)).setImageResource(0);
        }

    }
}