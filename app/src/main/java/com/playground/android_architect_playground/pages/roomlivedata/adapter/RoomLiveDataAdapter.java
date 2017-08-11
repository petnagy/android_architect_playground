package com.playground.android_architect_playground.pages.roomlivedata.adapter;

import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.playground.android_architect_playground.R;
import com.playground.android_architect_playground.database.entitiy.RecordItem;
import com.playground.android_architect_playground.pages.roomlivedata.view.DeleteCallback;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by petnagy on 2017. 08. 11..
 */

public class RoomLiveDataAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<RecordItem> items;

    private DeleteCallback callback;

    private static class RoomLiveDataViewHolder extends RecyclerView.ViewHolder {

        TextView planetName;

        ImageView delete;

        RoomLiveDataViewHolder(View view) {
            super(view);
            planetName = view.findViewById(R.id.planet_name);
            delete = view.findViewById(R.id.delete_icon);
        }
    }

    public RoomLiveDataAdapter() {
        items = new ArrayList<>();
    }

    public void setDeleteCallback(DeleteCallback callback) {
        this.callback = callback;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.roomlivedata_item, parent, false);
        return new RoomLiveDataViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        RecordItem item = items.get(position);
        ((RoomLiveDataViewHolder)holder).planetName.setText(item.getPlanetName());
        ((RoomLiveDataViewHolder)holder).delete.setOnClickListener(new DeleteClickListener(item, callback));
    }

    @Override
    public int getItemCount() {
        return items == null ? 0 : items.size();
    }

    public void setData(List<RecordItem> items) {
//        this.items = items;
//        notifyDataSetChanged();

        List<RecordItem> oldItems = new ArrayList<>(this.items);
        this.items.clear();
        this.items.addAll(items);
        DiffUtil.DiffResult result = DiffUtil.calculateDiff(new RecordItemDiffCallback(oldItems, this.items));
        result.dispatchUpdatesTo(this);
    }

    private static class RecordItemDiffCallback extends DiffUtil.Callback {

        private List<RecordItem> oldItems;

        private List<RecordItem> newItems;

        RecordItemDiffCallback(List<RecordItem> oldItems, List<RecordItem> newItems) {
            this.oldItems = oldItems;
            this.newItems = newItems;
        }

        @Override
        public int getOldListSize() {
            return oldItems.size();
        }

        @Override
        public int getNewListSize() {
            return newItems.size();
        }

        @Override
        public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
            return oldItems.get(oldItemPosition).equals(newItems.get(newItemPosition));
        }

        @Override
        public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
            return oldItems.get(oldItemPosition).equals(newItems.get(newItemPosition));
        }
    }

    private static class DeleteClickListener implements View.OnClickListener {

        private RecordItem item;

        private DeleteCallback callback;

        DeleteClickListener(RecordItem item, DeleteCallback callback) {
            this.item = item;
            this.callback = callback;
        }

        @Override
        public void onClick(View view) {
            callback.onItemDeletePressed(item);
        }
    }
}
