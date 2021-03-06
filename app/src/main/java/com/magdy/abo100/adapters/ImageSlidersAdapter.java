package com.magdy.abo100.adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.bumptech.glide.Glide;
import com.magdy.abo100.R;
import com.magdy.abo100.activities.SearchProductsActivity;
import com.magdy.abo100.helpers.LoopingPagerAdapter;
import com.magdy.abo100.helpers.StaticMembers;
import com.magdy.abo100.models.slider_models.SliderItem;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class ImageSlidersAdapter extends PagerAdapter implements LoopingPagerAdapter {

    private Context context;
    private List<SliderItem> imageList;
    private int pos = 0;

    public ImageSlidersAdapter(Context context, List<SliderItem> imageList) {
        this.context = context;
        this.imageList = imageList;
    }

    public int getPos() {
        return pos;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_image_slider, container, false);
        ImageView imageView = view.findViewById(R.id.image);
        Glide.with(context).load(imageList.get(pos).getPhoto()).fitCenter().into(imageView);
        view.setOnClickListener(v -> {
            int p = position % imageList.size();
            Intent intent = new Intent(context, SearchProductsActivity.class);
            intent.putExtra(StaticMembers.SUB_CATEGORY_ID, imageList.get(p).getSubcategoryId());
            intent.putExtra(StaticMembers.CATEGORY_ID, imageList.get(p).getCategoryId());
            context.startActivity(intent);
        });
        container.addView(view);
        if (pos >= imageList.size() - 1)
            pos = 0;
        else
            ++pos;
        return view;
    }

    @Override
    public int getCount() {
        return imageList.isEmpty() ? 0 : Integer.MAX_VALUE;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return (view ==(ImageView) o);
    }

    @Override
    public void destroyItem(@NotNull ViewGroup container, int position, @NotNull Object object) {
        container.removeView((ImageView) object);
    }

    @Override
    public Parcelable saveState() {
        return null;
    }

    @Override
    public int getRealCount() {
        return imageList.size();
    }
}
