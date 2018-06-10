    package com.example.acer.firstmediaplayer;

    import android.media.AudioManager;
    import android.media.MediaPlayer;
    import android.os.Handler;
    import android.support.v7.app.AppCompatActivity;
    import android.os.Bundle;
    import android.util.Log;
    import android.view.View;
    import android.widget.Button;
    import android.widget.SeekBar;
    import android.widget.Toast;

    import java.util.Timer;
    import java.util.TimerTask;

    public class MainActivity extends AppCompatActivity  {

        MediaPlayer mediaPlayer;
        SeekBar seekBar;


        @Override
        protected void onCreate(Bundle savedInstanceState) {

            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

            mediaPlayer = MediaPlayer.create(this, R.raw.song);

            seekBar = findViewById(R.id.seekbar);


            Button play = findViewById(R.id.play);

            play.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {

                    mediaPlayer.start();
                }
            });

            Button pause = findViewById(R.id.pause);

            pause.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {

                    mediaPlayer.pause();
                }
            });


            // handling the larger seekBar to change the progress of the audio with the help of seekBar

            seekBar.setMax(mediaPlayer.getDuration());

            new Timer().scheduleAtFixedRate(new TimerTask() {
                @Override
                public void run() {
                    seekBar.setProgress(mediaPlayer.getCurrentPosition());
                }
            },0, 200);



            seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                @Override
                public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

                    if (mediaPlayer != null && b) {

                        mediaPlayer.seekTo(i);

                    }
                }

                @Override
                public void onStartTrackingTouch(SeekBar seekBar) {

                    Log.i("Seek", "ProgressBar touched");
                }

                @Override
                public void onStopTrackingTouch(SeekBar seekBar) {

                    Log.i("Seek", "progress stopped");
                }

            });


            // using Audiomanager to make smaller seekBar to control Volume

            final SeekBar volBar = findViewById(R.id.volBar);

            final AudioManager audioManager = (AudioManager) getSystemService(AUDIO_SERVICE);

            int maxVol = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);

            int currentVol = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);

            volBar.setMax(maxVol);

            volBar.setProgress(currentVol);

            volBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                @Override
                public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

                    if (volBar != null && b) {

                        audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, i, 1);


                    }
                }

                @Override
                public void onStartTrackingTouch(SeekBar seekBar) {
                    Log.i("Vol", "volumeBar touched");
                }

                @Override
                public void onStopTrackingTouch(SeekBar seekBar) {
                    Log.i("Vol", "VolumeBar stopped");
                }
            });


        }


    }
