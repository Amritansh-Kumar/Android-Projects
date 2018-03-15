    package com.example.acer.tictactoe;

    import android.support.v7.app.AppCompatActivity;
    import android.os.Bundle;
    import android.view.View;
    import android.widget.GridLayout;
    import android.widget.ImageView;
    import android.widget.LinearLayout;
    import android.widget.TextView;

    public class MainActivity extends AppCompatActivity {

        ImageView touch;
        int activePlayer = 0;
    //  activePlayer = 0 for Red  &  activePlayer = 1 for Yellow

        Boolean activeState = true;
        int checkBoxState[] = {2, 2, 2, 2, 2, 2, 2, 2, 2};
        int winSet[][] = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8}, {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, {2, 4, 6}, {0, 4, 8}};

        public void play(View view) {

            touch = (ImageView) view;

            int tag = Integer.parseInt(touch.getTag().toString());

            if (checkBoxState[tag] == 2 && activeState) {

                checkBoxState[tag] = activePlayer;

                if (activePlayer == 0) {

                    touch.setImageResource(R.drawable.red);

                    activePlayer = 1;

                } else if (activePlayer == 1) {

                    touch.setImageResource(R.drawable.yellow);

                    activePlayer = 0;
                }

                touch.animate().rotation(360).setDuration(300);

            }

            // matching the combinations for win
            for (int win[] : winSet) {

                if ((checkBoxState[win[0]] == checkBoxState[win[1]]) && (checkBoxState[win[0]] == checkBoxState[win[2]]) && (checkBoxState[win[0]] != 2)) {

                    String winner = "Red";

                    if (checkBoxState[tag] == 1) {

                        winner = "Yellow";
                    }

                    // someone has won

                    LinearLayout messageBox = findViewById(R.id.messageBox);

                    messageBox.setVisibility(View.VISIBLE);

                    TextView message = findViewById(R.id.message);

                    message.setText(winner + "Won");

                    activeState = false;

                } else {

                    boolean gameOver = true;

                    for (int myState : checkBoxState) {

                        if (myState == 2) gameOver = false;

                    }

                    if (gameOver) {

                        TextView message = findViewById(R.id.message);

                        message.setText("It is a Draw");

                        LinearLayout messageBox = findViewById(R.id.messageBox);

                        messageBox.setVisibility(View.VISIBLE);

                    }

                }

            }
        }

        public void playAgain(View view) {

            activeState = true;

            activePlayer = 0;

            LinearLayout messageBox = findViewById(R.id.messageBox);

            messageBox.setVisibility(View.GONE);

            GridLayout gridView = findViewById(R.id.myGrid);

            for (int i = 0; i < checkBoxState.length; i++) {

                checkBoxState[i] = 2;
            }

            for (int i = 0; i < gridView.getChildCount(); i++) {

                ((ImageView) gridView.getChildAt(i)).setImageResource(0);
            }

        }


        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
        }


    }
