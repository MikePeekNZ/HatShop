package com.example.hatshop.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.hatshop.R;
import com.example.hatshop.activities.HatActivity;
import com.example.hatshop.model.Hat;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder>{

    private Context mContext;
    private List<Hat> mData;
    private RequestOptions option;

    public RecyclerViewAdapter(Context mContext, List<Hat> mData) {
        this.mContext = mContext;
        this.mData = mData;

        // Request option for Glide
        option = new RequestOptions()
                .centerCrop()
                .placeholder(R.drawable.loading_shape)
                .error(R.drawable.loading_shape);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view;
        LayoutInflater inflater = LayoutInflater.from(mContext);
        view = inflater.inflate(R.layout.hat_row_item, viewGroup, false);
        final MyViewHolder viewHolder = new MyViewHolder(view);
        viewHolder.view_container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, HatActivity.class);
                intent.putExtra("title", mData.get(viewHolder.getAdapterPosition()).getTitle());
                intent.putExtra("price", mData.get(viewHolder.getAdapterPosition()).getPrice());
                intent.putExtra("image", mData.get(viewHolder.getAdapterPosition()).getImageURL());
                intent.putExtra("description",
                        mData.get(viewHolder.getAdapterPosition()).getDescription());

                // get sizes array
                JSONArray jSizes = mData.get(viewHolder.getAdapterPosition()).getSizes();
                ArrayList<String> sizes = new ArrayList<String>();
                for (int i=0; i<jSizes.length(); i++) {
                    try {
                        sizes.add(jSizes.getString(i));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                intent.putStringArrayListExtra("sizes", sizes);

                // get seller object
                JSONObject seller = mData.get(viewHolder.getAdapterPosition()).getSeller();
                String sellerName = "";
                try {
                    sellerName = seller.getString("name");
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                intent.putExtra("seller", sellerName);

                mContext.startActivity(intent);
            }
        });

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        holder.tv_title.setText(mData.get(position).getTitle());

        // Load image from URL and set it into ImageView using Glide
        Glide.with(mContext).load(mData.get(position).getImageURL()).apply(option).into(holder.img_thumbnail);
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tv_title;
        ImageView img_thumbnail;
        LinearLayout view_container;

        public MyViewHolder(View itemView) {
            super(itemView);

            view_container = itemView.findViewById(R.id.container);
            tv_title = itemView.findViewById(R.id.hat_title);
            img_thumbnail = itemView.findViewById(R.id.thumbnail);
        }
    }
}
