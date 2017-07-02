package com.playground.android_architect_playground.decor;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;

/**
 * Created by petnagy on 2017. 07. 02..
 */

public class SpaceItemDecor extends RecyclerView.ItemDecoration {

    private final int space;

    public SpaceItemDecor(int space) {
        this.space = space;
    }

    @Override
    public void getItemOffsets(Rect outRect, android.view.View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);

        outRect.top = space;
        outRect.left = space;
        outRect.right = space;
        if (parent.getChildAdapterPosition(view) != parent.getAdapter().getItemCount() - 1) {
            outRect.bottom = space;
        }
    }

}
