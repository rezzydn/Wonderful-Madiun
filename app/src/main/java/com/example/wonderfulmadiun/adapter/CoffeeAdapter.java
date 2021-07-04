package com.example.wonderfulmadiun.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wonderfulmadiun.R;
import com.example.wonderfulmadiun.model.CoffeeModel;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.List;


public class CoffeeAdapter extends RecyclerView.Adapter<CoffeeAdapter.ViewHolder> {

    private List<CoffeeModel> items;
    private CoffeeAdapter.onSelectData onSelectData;
    private Context mContext;

    public interface onSelectData {
        void onSelected(CoffeeModel modelNews);
    }

    public CoffeeAdapter(Context context, List<CoffeeModel> items, CoffeeAdapter.onSelectData xSelectData) {
        this.mContext = context;
        this.items = items;
        this.onSelectData = xSelectData;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_coffee, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final CoffeeModel data = items.get(position);

        //Get Image
        Glide.with(mContext)
                .load(data.getGambarCoffee())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.imgCoffee);

        holder.tvNamaCoffee.setText(data.getTxtNamaCoffee());
        holder.detailCoffee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onSelectData.onSelected(data);
            }
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    //Class Holder
    class ViewHolder extends RecyclerView.ViewHolder {

        public TextView tvNamaCoffee;
        public CardView detailCoffee;
        public ImageView imgCoffee;

        public ViewHolder(View itemView) {
            super(itemView);
            detailCoffee = itemView.findViewById(R.id.detailCoffee);
            tvNamaCoffee = itemView.findViewById(R.id.tvNamaCoffee);
            imgCoffee = itemView.findViewById(R.id.imgCoffee);
        }
    }
}

