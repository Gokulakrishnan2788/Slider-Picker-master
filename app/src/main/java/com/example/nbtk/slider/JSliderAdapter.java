package com.example.nbtk.slider;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Gokul on 30/1/19.
 */
public class JSliderAdapter extends RecyclerView.Adapter<JSliderItemViewHolder> implements View.OnClickListener {

    private List<String> data = new ArrayList<>();
    ItemClickListener itemClickListener = null;

    public JSliderAdapter(List<String> data, ItemClickListener itemClickListener) {
        this.data = data;
        this.itemClickListener = itemClickListener;
    }

    @NonNull
    @Override
    public JSliderItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_slider_item, parent, false);
        itemView.setOnClickListener(this);
        JSliderItemViewHolder horizontalViewHolder = new JSliderItemViewHolder(itemView);

        return horizontalViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull JSliderItemViewHolder holder, int position) {
        holder.textView.setText(data.get(position));


    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @Override
    public int getItemCount() {
        return data != null ? data.size() : 0;
    }

    @Override
    public void onClick(View view) {
        itemClickListener.onItemClicked(view);
    }


    interface ItemClickListener {
        void onItemClicked(View view);
    }

    public void setData(ArrayList<String> data) {
        this.data.clear();
        this.data.addAll(data);
        notifyDataSetChanged();
    }
}
