package com.playground.android_architect_playground.pages.colorpage.view;

import android.arch.lifecycle.LifecycleRegistry;
import android.arch.lifecycle.LifecycleRegistryOwner;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.playground.android_architect_playground.R;
import com.playground.android_architect_playground.pages.colorpage.data.ColorEnum;
import com.playground.android_architect_playground.pages.colorpage.model.ColorViewModel;

import javax.inject.Inject;

import dagger.android.support.DaggerFragment;

/**
 * Created by petnagy on 2017. 07. 06..
 */

public class ColorFragment extends DaggerFragment implements LifecycleRegistryOwner {

    private static final String OFFSET_EXTRA = "offset";

    @Inject
    LifecycleRegistry lifecycleRegistry;

    private int offset;

    private ColorEnum fragmentColor;

    public static ColorFragment newInstance(int offset) {
        ColorFragment instance = new ColorFragment();
        Bundle args = new Bundle();
        args.putInt(OFFSET_EXTRA, offset);
        instance.setArguments(args);
        return instance;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        offset = getArguments().getInt(OFFSET_EXTRA);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final ColorViewModel colorViewModel = ViewModelProviders.of(getActivity()).get(ColorViewModel.class);
        View colorHolder = inflater.inflate(R.layout.color_fragment_layout, container, false);
        final View color = colorHolder.findViewById(R.id.color);
        colorViewModel.getBaseColor().observe(this, new Observer<ColorEnum>() {
            @Override
            public void onChanged(@Nullable ColorEnum baseColor) {
                fragmentColor = ColorEnum.calculateColor(baseColor, offset);
                color.setBackgroundResource(fragmentColor.getColorResId());
            }
        });
        color.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                colorViewModel.setBaseColor(fragmentColor);
            }
        });
        return colorHolder;
    }

    @Override
    public LifecycleRegistry getLifecycle() {
        return lifecycleRegistry;
    }
}
