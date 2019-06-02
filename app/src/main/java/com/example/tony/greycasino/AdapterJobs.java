package com.example.tony.greycasino;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.tony.greycasino.R;
import com.example.tony.greycasino.Jobs;


import java.util.ArrayList;

/**
 * Created by Tony Geara  on June 1, 2019 .
 */

public class AdapterJobs extends RecyclerView.Adapter<AdapterJobs.MyViewHolder> {

    private Context ctx;
    private ArrayList<Jobs> mData;
    private OnJobClick editor;


    public AdapterJobs(Context context, ArrayList<Jobs> mData, OnJobClick editor) {
        if (mData != null)
            this.mData = mData;
        else
            this.mData = new ArrayList<>();
        this.ctx = context;
        this.editor = editor;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(ctx).inflate(R.layout.item_show_jobs,
                viewGroup, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder p0, int position) {

        p0.tvTitle.setText(mData.get(position).getTitle());
        p0.tvDetails.setText(mData.get(position).getDetails());
        p0.tvApply.setText(mData.get(position).getApply());


    }

    @Override
    public int getItemCount() {
        return mData.size();
    }



    interface OnJobClick {
        void  jobClicked(Jobs orderItemModel );
        void showPdf(String url);
    }
    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tvApply, tvTitle, tvDetails;
        ConstraintLayout clEvent ;
        Button btnReadMore;

        public MyViewHolder(View view) {
            super(view);
            //llChallengeItem = (LinearLayout) itemView.findViewById(R.id.ll_challenge_item);

            tvApply = view.findViewById(R.id.tvApply);
            tvTitle = view.findViewById(R.id.tvTitle);
            tvDetails = view.findViewById(R.id.tvDetails);
            clEvent = view.findViewById(R.id.clEvent);
            btnReadMore = view.findViewById(R.id.btnReadMore);



            clEvent.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    editor.jobClicked(mData.get(getAdapterPosition()));

                }
            });

            btnReadMore.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (mData.get(getAdapterPosition()).getFileLink()!= null)
                        editor.showPdf(mData.get(getAdapterPosition()).getFileLink());
                }
            });
        }
    }
}

