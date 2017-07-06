package com.playground.android_architect_playground.pages.colorpage.model;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.playground.android_architect_playground.pages.colorpage.data.ColorEnum;

/**
 * Created by petnagy on 2017. 07. 06..
 */

public class ColorViewModel extends ViewModel {

    private MutableLiveData<ColorEnum> baseColor;

    public MutableLiveData<ColorEnum> getBaseColor() {
        if (baseColor == null) {
            baseColor = new MutableLiveData<>();
        }
        return baseColor;
    }

    public void setBaseColor(ColorEnum color) {
        baseColor.setValue(color);
    }
}
