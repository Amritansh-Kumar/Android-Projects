    package com.example.acer.learningphrases;

    import android.media.MediaPlayer;
    import android.support.v7.app.AppCompatActivity;
    import android.os.Bundle;
    import android.view.View;
    import android.widget.Button;
    import android.widget.GridLayout;
    import android.widget.Toast;

    import java.util.ArrayList;
    import java.util.List;

    public class MainActivity extends AppCompatActivity {




        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
        }


        public void translate(View view) {

            int id = view.getId();

            String phrase = "";

            phrase = view.getResources().getResourceEntryName(id);

            int resourceId = getResources().getIdentifier(phrase, "raw", "com.example.acer.learningphrases");

            MediaPlayer mediaPlayer = MediaPlayer.create(this, resourceId);

            mediaPlayer.start();

        }

    }
