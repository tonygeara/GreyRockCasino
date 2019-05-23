package com.example.tony.greycasino;


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class AdapterPromotions extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Context mContext;
    String [] items ;
    String [] items2 ;
    String [] items3 ;

    int [] images;



    public AdapterPromotions(Context context , String[] items,String[] items2,String[] items3,int [] images) {
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
        Itemss item = new Itemss(row);

        return item;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        ((Itemss)viewHolder).text.setText(items[i]);
        ((Itemss)viewHolder).text2.setText(items2[i]);
        ((Itemss)viewHolder).text3.setText(items3[i]);
        ((Itemss)viewHolder).mImageView.setImageResource(images[i]);

    }

    @Override
    public int getItemCount() {
        return items.length;
    }

    public class Itemss extends RecyclerView.ViewHolder{
        TextView text;
        TextView text2;
        TextView text3;

        ImageView mImageView;
        public Itemss (View itemView){
            super(itemView);
            text = (TextView) itemView.findViewById(R.id.item1);
            text2 = (TextView) itemView.findViewById(R.id.item2);
            text3 = (TextView) itemView.findViewById(R.id.item3);

            mImageView = (ImageView)itemView.findViewById(R.id.imageview001);
        }
    }
}
