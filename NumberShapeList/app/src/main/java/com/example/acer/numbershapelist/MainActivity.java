    package com.example.acer.numbershapelist;

    import android.support.v7.app.AppCompatActivity;
    import android.os.Bundle;
    import android.support.v7.widget.LinearLayoutManager;
    import android.support.v7.widget.RecyclerView;

    import java.util.ArrayList;
    import java.util.Random;

    import Adapter.MyAdapter;

    public class MainActivity extends AppCompatActivity {

        MyAdapter adapter;
        RecyclerView recyclerView;

        ArrayList<String> numberList = new ArrayList<String>();

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
            for (int i = 0; i < 20; i++) {
                Random random = new Random();
                int numb = random.nextInt(25) + 1;
                numberList.add(String.valueOf(numb));
            }

            recyclerView = findViewById(R.id.recycler_view);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            adapter = new MyAdapter(numberList, this);
            recyclerView.setAdapter(adapter);

        }
    }
