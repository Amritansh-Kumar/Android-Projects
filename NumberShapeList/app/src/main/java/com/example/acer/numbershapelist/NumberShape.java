    package com.example.acer.numbershapelist;

    import android.app.ActionBar;
    import android.content.Context;
    import android.content.Intent;
    import android.support.v7.app.AppCompatActivity;
    import android.os.Bundle;
    import android.view.MenuItem;
    import android.widget.ArrayAdapter;
    import android.widget.ListView;
    import android.widget.TextView;

    import java.util.ArrayList;
    import java.util.List;

    public class NumberShape extends AppCompatActivity {
        Bundle extra;
        TextView triangular;
        TextView square;
        ListView mTable;
        List<String> table;
        String row;
        int number, numb = 1, sum = 1;
        ArrayAdapter<String> adapter;
        android.support.v7.app.ActionBar actionBar;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_number_shape);
            triangular = findViewById(R.id.triangular);
            square = findViewById(R.id.square);
            mTable = findViewById(R.id.table);
            table = new ArrayList<>();
            actionBar = getSupportActionBar();
            actionBar.setDisplayHomeAsUpEnabled(true);


                // retrieving the extra send by the calling intent
            extra = getIntent().getExtras();
            if (extra != null) {
                number = Integer.parseInt(extra.getString("number"));
            }


            // checking if the number is a triangular number or not
            while (sum < number) {
                sum += numb;
                numb++;
            }

            if (sum == number) {

                triangular.setText(number + " is a triangular number");
            } else {

                triangular.setText(number + " is not a triangular number");
            }


            //  checking if the number is a square number or not
            double num = Math.sqrt(number);

            if (Math.floor(num) == num) {

                square.setText(number + " is a square number");
            } else {

                square.setText(number + " is not a square number");


            }


            // generating the table
            for (int i = 1; i <= 10; i++) {

                row = number + "  *  " + i + "  =  " + (number * i);
                table.add(row);
            }

            adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, table);
            mTable.setAdapter(adapter);

        }
        @Override
        public boolean onNavigateUp() {
            finish();
            return true;
        }

        @Override
        public boolean onOptionsItemSelected(MenuItem item) {

            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);

            return super.onOptionsItemSelected(item);
        }
    }

