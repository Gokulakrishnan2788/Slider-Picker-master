package com.example.nbtk.slider;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

/**
 * Created by Gokul on 30/1/19.
 */
public class JSliderItemViewHolder extends RecyclerView.ViewHolder {
    TextView textView;

    public JSliderItemViewHolder(View itemView) {
        super(itemView);
        textView=itemView.findViewById(R.id.tv_item);
    }
}
