package com.example.nbtk.slider;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.LinearSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SnapHelper;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Gokul on 30/1/19.
 */
public class JSliderLayoutManager extends LinearLayoutManager {

    int orientation = HORIZONTAL;
    OnItemSelectedListener onItemSelectedListener;
    private RecyclerView recyclerView;

    private float scaleDownBy = 0.45f;
    private float scaleDownDistance = 0.8f;

    @Override
    public void onAttachedToWindow(RecyclerView view) {
        super.onAttachedToWindow(view);
        recyclerView = view;
        LinearSnapHelper linearSnapHelper = new LinearSnapHelper();
        linearSnapHelper.attachToRecyclerView(recyclerView);
    }

    @Override
    public void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state) {
        super.onLayoutChildren(recycler, state);
        scaleDownView();
    }


    @Override
    public int scrollHorizontallyBy(int dx, RecyclerView.Recycler recycler, RecyclerView.State state) {
        int scrolled = super.scrollHorizontallyBy(dx, recycler, state);

        if (orientation == LinearLayoutManager.HORIZONTAL) {

            scaleDownView();

        }
//        else
//            return 0;
//        }
        return scrolled;

    }

    @Override
    public void onScrollStateChanged(int state) {
        super.onScrollStateChanged(state);


        // When scroll stops we notify on the selected item
        if (state == RecyclerView.SCROLL_STATE_IDLE) {

            // Find the closest child to the recyclerView center --> this is the selected item.
            int recyclerViewCenterX = getRecyclerViewCenterX();
            float minDistance = recyclerView.getWidth();
            int position = -1;
            for (int i = 0; i < recyclerView.getChildCount(); i++) {
                View child = recyclerView.getChildAt(i);
                float childCenterX = getDecoratedLeft(child) + (getDecoratedRight(child) - getDecoratedLeft(child)) / 2;
                float newDistance = Math.abs(childCenterX - recyclerViewCenterX);
                if (newDistance < minDistance) {
                    minDistance = newDistance;
                    position = recyclerView.getChildLayoutPosition(child);
                }
            }
            // Notify on item selection
            if (onItemSelectedListener != null)
                onItemSelectedListener.onItemSelected(position);
        }
    }

    public JSliderLayoutManager(Context context) {
        super(context);
    }

    public JSliderLayoutManager(Context context, int orientation, boolean reverseLayout) {
        super(context, orientation, reverseLayout);
    }

    public JSliderLayoutManager(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    interface OnItemSelectedListener {
        void onItemSelected(int layoutPosition);
    }

    private int getRecyclerViewCenterX() {
        return (recyclerView.getRight() - recyclerView.getLeft()) / 2 + recyclerView.getLeft();
    }


    private void scaleDownView() {


        float mid = getWidth() / 2.0f;
        float distanceFromCenter = 0.0f;
        float unitScaleDownDist = scaleDownDistance * mid;
        for (int i = 0; i < getChildCount(); i++) {
            View child = getChildAt(i);
            float childMid = (getDecoratedLeft(child) + getDecoratedRight(child)) / 2.0f;

            if (mid == childMid) {
                distanceFromCenter = Math.abs(mid - childMid);

            } else {
                distanceFromCenter = Math.abs(mid);
                if (i == 0 || i == getChildCount() - 1) {
                    distanceFromCenter = Math.abs(mid - childMid);
                }

            }


            float scale = 1 - (float) Math.sqrt((distanceFromCenter / getWidth())) * 0.66f;

            child.setScaleX(scale);
            child.setScaleY(scale);
        }

    }

    public void setOnItemSelectedListener(OnItemSelectedListener onItemSelectedListener) {
        this.onItemSelectedListener = onItemSelectedListener;
    }
}
