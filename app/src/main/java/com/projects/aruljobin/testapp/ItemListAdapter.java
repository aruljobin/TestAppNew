package com.projects.aruljobin.testapp;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by aruljobin on 01/05/18.
 */

public class ItemListAdapter extends RecyclerView.Adapter<ItemListAdapter.MyItemViewHolder> {
    private final Activity activity;
    private String shared_url,item_name,item_image,item_price,user_id;

    //private static final String LOG_TAG = MyItemViewHolder.class.getSimpleName();

    private List<ManageItems> mData;

    public ItemListAdapter(Activity activity, List<ManageItems> data) {
        this.activity=activity;
        mData = data;
    }

    // Called when a new view for an item must be created. This method does not return the view of
    // the item, but a ViewHolder, which holds references to all the elements of the view.
    @Override
    public MyItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // The view for the item
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_layout, parent, false);
        // Create a ViewHolder for this view and return it
        return new MyItemViewHolder(itemView);
    }

    // Populate the elements of the passed view (represented by the ViewHolder) with the data of
    // the item at the specified position.
    @Override
    public void onBindViewHolder(final MyItemViewHolder holder, final int position) {

        holder.name.setText(mData.get(position).getUserName());
        holder.comments.setText(mData.get(position).getCommentCount()+" Comments");
        holder.address.setText(mData.get(position).getUserCity());
        holder.times.setText(mData.get(position).getCreatedOn());
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class MyItemViewHolder extends RecyclerView.ViewHolder {
        public TextView times,comments,address,name;
        private ImageView image;

        // Create a viewHolder for the passed view (item view)
        public MyItemViewHolder(View view) {
            super(view);

            name = (TextView) view.findViewById(R.id.name);
            address = (TextView) view.findViewById(R.id.address);
            comments = (TextView) view.findViewById(R.id.comments);
            times = (TextView) view.findViewById(R.id.times);
            image = (ImageView) view.findViewById(R.id.image);

        }
    }
}
