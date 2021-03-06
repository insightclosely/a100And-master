package com.magdy.abo100.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.asksira.loopingviewpager.LoopingPagerAdapter;
import com.bumptech.glide.Glide;
import com.magdy.abo100.R;
import com.magdy.abo100.activities.SearchProductsActivity;
import com.magdy.abo100.helpers.StaticMembers;
import com.magdy.abo100.models.slider_models.SliderItem;

import java.util.ArrayList;

public class ImageSliderAdapter extends LoopingPagerAdapter<SliderItem> {

    public ImageSliderAdapter(Context context, ArrayList<SliderItem> itemList) {
        super(context, itemList, true);
    }

    @Override
    protected View inflateView(int viewType, ViewGroup container, int listPosition) {
        return LayoutInflater.from(context).inflate(R.layout.item_image_slider, container, false);
    }

    @Override
    protected void bindView(View view, int listPosition, int viewType) {
        ImageView imageView = view.findViewById(R.id.image);
        Glide.with(context).load(getItem(listPosition).getPhoto()).fitCenter().into(imageView);
        view.setOnClickListener(v -> {
            Intent intent = new Intent(context, SearchProductsActivity.class);
            intent.putExtra(StaticMembers.SUB_CATEGORY_ID, getItem(listPosition).getSubcategoryId());
            intent.putExtra(StaticMembers.CATEGORY_ID, getItem(listPosition).getCategoryId());
            context.startActivity(intent);
        });
    }
}