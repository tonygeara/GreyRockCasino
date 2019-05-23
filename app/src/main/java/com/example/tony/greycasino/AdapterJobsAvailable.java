package com.example.tony.greycasino;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

class AdapterJobsAvailable extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Context mContext;
    String [] items ;
    String [] items2 ;
    String [] items3 ;
    String [] items4 ;




    public AdapterJobsAvailable(Context context , String[] items,String[] items2,String[] items3,String[] items4) {
        this.mContext = context;
        this.items = items;
        this.items4 = items4;
        this.items2 = items2;
        this.items3 = items3;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View row = inflater.inflate(R.layout.custom_recycler_jobs,viewGroup,false);
        ItemJobs item = new ItemJobs(row);

        return item;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        ((ItemJobs)viewHolder).text.setText(items[i]);
        ((ItemJobs)viewHolder).text2.setText(items2[i]);
        ((ItemJobs)viewHolder).text3.setText(items3[i]);
        ((ItemJobs)viewHolder).text1.setText(items4[i]);


    }

    @Override
    public int getItemCount() {
        return items.length;
    }

    public class ItemJobs extends RecyclerView.ViewHolder{
        TextView text;
        TextView text1;
        TextView text2;
        TextView text3;


        ImageView mImageView;
        public ItemJobs (View itemView){
            super(itemView);
            text = (TextView) itemView.findViewById(R.id.textView44);
            text1 = (TextView) itemView.findViewById(R.id.item1);
            text2 = (TextView) itemView.findViewById(R.id.item2);
            text3 = (TextView) itemView.findViewById(R.id.item3);
        }
    }
}
