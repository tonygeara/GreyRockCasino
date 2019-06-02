package com.example.tony.greycasino;


import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.tony.greycasino.R;
import com.example.tony.greycasino.Events;

import java.util.ArrayList;

/**
 * Created by Tony May 30,2019
 */

public class AdapterEvent extends RecyclerView.Adapter<AdapterEvent.MyViewHolder> {

    private Context ctx;
    private ArrayList<Events> mData;
    private OnEventClick editor;


    public AdapterEvent(Context context, ArrayList<Events> mData, OnEventClick editor) {
        if (mData != null)
            this.mData = mData;
        else
            this.mData = new ArrayList<>();
        this.ctx = context;
        this.editor = editor;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(ctx).inflate(R.layout.item_show_event,
                viewGroup, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder p0, int position) {



        Glide.with(p0.ivThumbnail).load(mData.get(position).getThumbnail()).into(p0.ivThumbnail);

        p0.tvTitle.setText(mData.get(position).getTitle());
        p0.tvDetails.setText(Html.fromHtml(mData.get(position).getDetails()));
        p0.tvEventDate.setText(mData.get(position).getEventDate());


    }

    @Override
    public int getItemCount() {
        return mData.size();
    }



    interface OnEventClick {
        void eventClicked(Events orderItemModel);
    }
    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tvEventDate, tvTitle, tvDetails;
        ConstraintLayout clEvent ;
        ImageView ivThumbnail;

        public MyViewHolder(View view) {
            super(view);
            //llChallengeItem = (LinearLayout) itemView.findViewById(R.id.ll_challenge_item);

            tvEventDate = view.findViewById(R.id.tvEventDate);
            tvTitle = view.findViewById(R.id.tvTitle);
            tvDetails = view.findViewById(R.id.tvDetails);
            clEvent = view.findViewById(R.id.clEvent);
            ivThumbnail= view.findViewById(R.id.ivThumbnail);



            clEvent.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    editor.eventClicked(mData.get(getAdapterPosition()));

                }
            });


        }
    }
}

