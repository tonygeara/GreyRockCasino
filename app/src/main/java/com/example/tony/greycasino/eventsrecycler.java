package com.example.tony.greycasino;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class eventsrecycler extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Context mContext;
    String [] items ;
    String [] items2 ;
    String [] items3 ;
    int [] Images ;




    public eventsrecycler(Context context , String[] items,String[] items2,String[] items3,int [] Images) {
        this.mContext = context;
        this.items = items;
        this.Images = Images;
        this.items2 = items2;
        this.items3 = items3;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View row = inflater.inflate(R.layout.custom_view_recycler,viewGroup,false);
        ItemEvents item = new ItemEvents(row);

        return item;
    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder viewHolder, int i) {
        ((ItemEvents)viewHolder).text.setText(items[i]);
        ((ItemEvents)viewHolder).text1.setText(items2[i]);
        ((ItemEvents)viewHolder).text2.setText(items3[i]);
        ((ItemEvents)viewHolder).image.setImageResource(Images[i]);

        ((ItemEvents) viewHolder).parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String job = String.valueOf(viewHolder.getAdapterPosition());
                eventsrecyclerview activity = (eventsrecyclerview) mContext;
                activity.called(job);
            }
        });





    }

    @Override
    public int getItemCount() {
        return items.length;
    }

    public class ItemEvents extends RecyclerView.ViewHolder{
        TextView text;
        TextView text1;
        TextView text2;
        ImageView image;

        ConstraintLayout parentLayout;



        ImageView mImageView;
        public ItemEvents (View itemView){
            super(itemView);
            text = (TextView) itemView.findViewById(R.id.item1);
            text1 = (TextView) itemView.findViewById(R.id.item2);
            text2 = (TextView) itemView.findViewById(R.id.item3);
            image = (ImageView) itemView.findViewById(R.id.imageview001);
            parentLayout = itemView.findViewById(R.id.idd);
        }
    }
}

