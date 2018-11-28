package com.example.tony.greycasino;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class Adapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Context mContext;
    String [] items ;
    String [] items2 ;
    String [] items3 ;

    int [] images;


    public Adapter(Context context , String[] items,String[] items2,String[] items3,int [] images) {
        this.mContext = context;
        this.items = items;
        this.images = images;
        this.items2 = items2;
        this.items3 = items3;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View row = inflater.inflate(R.layout.custom_view_recycler,viewGroup,false);
        Item item = new Item(row);

        return item;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        ((Item)viewHolder).text.setText(items[i]);
        ((Item)viewHolder).text2.setText(items2[i]);
        ((Item)viewHolder).text3.setText(items3[i]);
        ((Item)viewHolder).mImageView.setImageResource(images[i]);

    }

    @Override
    public int getItemCount() {
       return items.length;
    }

    public class Item extends RecyclerView.ViewHolder{
        TextView text;
        TextView text2;
        TextView text3;

        ImageView mImageView;
        public Item (View itemView){
            super(itemView);
            text = (TextView) itemView.findViewById(R.id.item1);
            text2 = (TextView) itemView.findViewById(R.id.item2);
            text3 = (TextView) itemView.findViewById(R.id.item3);

            mImageView = (ImageView)itemView.findViewById(R.id.imageview001);
        }
    }
}
