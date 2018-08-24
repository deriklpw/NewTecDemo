package com.example.derik.newtecdemo.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.derik.newtecdemo.R;

/**
 * Created by derik on 18-8-13.
 */

public class RecycleViewAdapter extends RecyclerView.Adapter<RecycleViewAdapter.MyHolder> {

    private static final String TAG = "RecycleViewAdapter";
    private OnItemClickListener mListener;
    private String[] mNames;
    private String[] mDescs;

    public RecycleViewAdapter(String[] datas, String[] descs) {
        mNames = datas;
        mDescs = descs;
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = (LayoutInflater) parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.item_list, parent, false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(final MyHolder holder, final int position) {
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener != null) {
                    mListener.onItemClick(holder.itemView, holder.getAdapterPosition());
                }
            }
        });
        holder.text1.setText(mNames[position]);
        holder.num.setText(String.valueOf(position));
        if (mDescs != null) {
            holder.text2.setVisibility(View.VISIBLE);
            holder.text2.setText(mDescs[position]);
        } else {
            holder.text1.setTextSize(30);
        }

    }

    @Override
    public int getItemCount() {
        return mNames.length;
    }

    class MyHolder extends RecyclerView.ViewHolder {
        TextView num;
        TextView text1;
        TextView text2;
        private MyHolder(View itemView) {
            super(itemView);
            num = itemView.findViewById(R.id.num);
            text1 = itemView.findViewById(R.id.text1);
            text2 = itemView.findViewById(R.id.text2);
            text1.setTextSize(20);
            text2.setTextSize(20);
            text1.setTextColor(Color.RED);
            text2.setTextColor(Color.BLUE);
        }
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }
}