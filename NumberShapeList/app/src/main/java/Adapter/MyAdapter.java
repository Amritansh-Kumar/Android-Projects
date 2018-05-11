    package Adapter;

    import android.content.Context;
    import android.content.Intent;
    import android.support.v7.widget.RecyclerView;
    import android.view.LayoutInflater;
    import android.view.View;
    import android.view.ViewGroup;
    import android.widget.TextView;
    import android.widget.Toast;

    import com.example.acer.numbershapelist.NumberShape;
    import com.example.acer.numbershapelist.R;

    import java.util.ArrayList;

    public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

        Context context;
        public ArrayList<String> numbers;

        public MyAdapter(ArrayList<String> numbers, Context context) {
            this.numbers = numbers;
            this.context = context;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.my_row, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            holder.number.setText(numbers.get(position));
        }


        @Override
        public int getItemCount() {
            return numbers.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

            TextView number;

            public ViewHolder(View itemView) {
                super(itemView);

                itemView.setOnClickListener(this);
                number = itemView.findViewById(R.id.value);

            }

            @Override
            public void onClick(View view) {
                int pos = getAdapterPosition();
                String num = numbers.get(pos);
                Toast.makeText(context, "number " + num + " is at position " + pos, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(context, NumberShape.class);
                intent.putExtra("number", num);
                context.startActivity(intent);
            }
        }
    }





