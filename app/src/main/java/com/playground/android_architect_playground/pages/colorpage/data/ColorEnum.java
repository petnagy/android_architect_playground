package com.playground.android_architect_playground.pages.colorpage.data;

import com.playground.android_architect_playground.R;

/**
 * Created by petnagy on 2017. 07. 06..
 */

public enum ColorEnum {

    GREEN(R.color.green),
    BLUE(R.color.blue),
    RED(R.color.red),
    YELLOW(R.color.yellow);

    private int colorResId;

    ColorEnum(int colorResId) {
        this.colorResId = colorResId;
    }

    public int getColorResId() {
        return colorResId;
    }

    public static ColorEnum calculateColor(ColorEnum baseColor, int offset) {
        int baseOrdinal = baseColor.ordinal();
        int newOrdinal = (baseOrdinal + offset) % 4;
        return values()[newOrdinal];
    }
}
