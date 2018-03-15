    package com.example.acer.braintrainer;

    import android.media.MediaPlayer;
    import android.os.CountDownTimer;
    import android.support.v7.app.AppCompatActivity;
    import android.os.Bundle;
    import android.view.View;
    import android.widget.Button;
    import android.widget.TextView;

    import java.util.Random;

    public class MainActivity extends AppCompatActivity {

        Button submitButton, button1, button2, button3, button4, playAgain;
        TextView clockTimer, scoreUpdater, question, answer;
        Boolean answerStatus = true, gameFinish = false;
        int sum, tag, attempts = 0, score = 0, a, b, mOption;
        Random generateNumber;
        MediaPlayer mediaPlayer;


        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

            clockTimer = findViewById(R.id.clock);
            playAgain = findViewById(R.id.playAgain);
            scoreUpdater = findViewById(R.id.score);
            answer = findViewById(R.id.review);
            question = findViewById(R.id.question);
            generateNumber = new Random();

            button1 = findViewById(R.id.Button1);
            button2 = findViewById(R.id.Button2);
            button3 = findViewById(R.id.Button3);
            button4 = findViewById(R.id.Button4);

            gamePlay();

        }


        public void gamePlay() {

            new CountDownTimer(30100, 1000) {

                @Override
                public void onTick(long l) {

                    clockTimer.setText(Integer.toString ((int)(l / 1000))+"s");
                }

                @Override
                public void onFinish() {

                    gameFinish = true;
                    clockTimer.setText("0s");
                    answer.setVisibility(View.VISIBLE);
                    answer.setText("Your score: " + score + "/" + attempts);
                    playAgain.setVisibility(View.VISIBLE);
                    mediaPlayer = MediaPlayer.create(MainActivity.this, R.raw.air_horn);
                    mediaPlayer.start();


                    playAgain.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                            gameFinish = false;
                            playAgain.setVisibility(View.INVISIBLE);
                            answer.setVisibility(View.INVISIBLE);
                            attempts = 0;
                            score = 0;
                            scoreUpdater.setText(score + "/" + attempts);
                            clockTimer.setText("30s");

                            generateQuestion();
                            gamePlay();
                        }
                    });
                }
            }.start();

        }


        // function to set the quetion every time answer is submitted
        public void generateQuestion() {

            if (gameFinish == false) {

                if (answerStatus == true) {

                    answerStatus = false;
                    a = generateNumber.nextInt(25) + 1;
                    b = generateNumber.nextInt(25) + 1;
                    sum = a + b;
                    question.setText(a + " + " + b);

                    setAnswer(sum);
                }
            }

        }


        public void setAnswer(int sum) {

            tag = generateNumber.nextInt(4) + 1;

            for (int i = 0; i < 4; i++) {

                if (tag == 1) {

                    button1.setText(Integer.toString(sum));
                } else {
                    mOption = generateNumber.nextInt(50) + 1;
                    if (mOption == sum) {

                        mOption = (mOption + 1) % 50;
                    }
                    button1.setText(Integer.toString(mOption));
                }

                if (tag == 2) {

                    button2.setText(Integer.toString(sum));
                } else {
                    mOption = generateNumber.nextInt(50) + 1;
                    if (mOption == sum) {

                        mOption = (mOption + 1) % 50;
                    }
                    button2.setText(Integer.toString(mOption));
                }

                if (tag == 3) {

                    button3.setText(Integer.toString(sum));
                } else {
                    mOption = generateNumber.nextInt(50) + 1;
                    if (mOption == sum) {

                        mOption = (mOption + 1) % 50;
                    }
                    button3.setText(Integer.toString(mOption));
                }
                if (tag == 4) {

                    button4.setText(Integer.toString(sum));
                } else {
                    mOption = generateNumber.nextInt(50) + 1;
                    if (mOption == sum) {

                        mOption = (mOption + 1) % 50;
                    }
                    button4.setText(Integer.toString(mOption));
                }

            }

        }


        // function called on answer button click
        public void submission(View view) {

            if (gameFinish == false) {

                submitButton = (Button) view;
                answerStatus = true;

                String answerTag = (String) submitButton.getTag();

                if (answerTag != null) {
                    if (Integer.parseInt(answerTag) == tag) {

                        score++;
                        answer.setText("Correct!");

                    } else {

                        answer.setText("Wrong!");

                    }

                    attempts++;
                    answer.setVisibility(View.VISIBLE);
                    scoreUpdater.setText(score + "/" + attempts);
                    generateQuestion();
                }
            }
        }


    }
