package com.example.tony.greycasino;


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class AdapterCareers extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Context mContext;
    String [] items ;
    String [] items2 ;



    public AdapterCareers(Context context , String[] items,String[] items2) {
        this.mContext = context;
        this.items = items;

        this.items2 = items2;

    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View row = inflater.inflate(R.layout.custom_recycler_careers,viewGroup,false);
        Itemcareer item = new Itemcareer(row);

        return item;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        ((Itemcareer)viewHolder).text.setText(items[i]);
        ((Itemcareer)viewHolder).text2.setText(items2[i]);

    }

    @Override
    public int getItemCount() {
        return items.length;
    }

    public class Itemcareer extends RecyclerView.ViewHolder{
        TextView text;
        TextView text2;

        public Itemcareer (View itemView){
            super(itemView);
            text = (TextView) itemView.findViewById(R.id.item1);
            text2 = (TextView) itemView.findViewById(R.id.item2);
        }
    }
}
