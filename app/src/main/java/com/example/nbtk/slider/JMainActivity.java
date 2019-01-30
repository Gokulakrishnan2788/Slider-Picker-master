package com.example.nbtk.slider;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import java.util.Arrays;
import java.util.List;

public class JMainActivity extends AppCompatActivity {
    private RecyclerView rvHorizontalPicker;
    private TextView tvSelectedItem;
    private List<String> data = Arrays.asList("1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setTvSelectedItem();
        setHorizontalPicker();
    }

    private void setTvSelectedItem() {
        tvSelectedItem = findViewById(R.id.tv_selected_item);
    }

    private void setHorizontalPicker() {
        rvHorizontalPicker = findViewById(R.id.rv_horizontal_picker);

        // Setting the padding such that the items will appear in the middle of the screen
        int padding = (int) (JScreenUtils.getScreenWidth(this) / 2 - JScreenUtils.dpToPx(20));
        rvHorizontalPicker.setPadding(padding, 0, padding, 0);

        // Setting layout manager
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        JSliderLayoutManager sliderLayoutManager = new JSliderLayoutManager(this, LinearLayoutManager.HORIZONTAL,false);

        PickerLayoutManager pickerLayoutManager = new PickerLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        rvHorizontalPicker.setLayoutManager(sliderLayoutManager);
        sliderLayoutManager.setOnItemSelectedListener(new JSliderLayoutManager.OnItemSelectedListener() {
            @Override
            public void onItemSelected(int layoutPosition) {
                tvSelectedItem.setText(data.get(layoutPosition));

            }
        });

        JSliderAdapter jSliderAdapter = new JSliderAdapter(data, new JSliderAdapter.ItemClickListener() {
            @Override
            public void onItemClicked(View view) {
                rvHorizontalPicker.smoothScrollToPosition(rvHorizontalPicker.getChildLayoutPosition(view));
            }
        });
        rvHorizontalPicker.setAdapter(jSliderAdapter);

    }
}
