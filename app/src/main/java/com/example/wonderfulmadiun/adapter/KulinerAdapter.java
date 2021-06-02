package com.example.wonderfulmadiun.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.wonderfulmadiun.R;
import com.example.wonderfulmadiun.model.KulinerModel;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.List;


public class KulinerAdapter extends RecyclerView.Adapter<KulinerAdapter.ViewHolder> {

    private List<KulinerModel> items;
    private KulinerAdapter.onSelectData onSelectData;
    private Context mContext;

    public interface onSelectData {
        void onSelected(KulinerModel modelNews);
    }

    public KulinerAdapter(Context context, List<KulinerModel> items, KulinerAdapter.onSelectData xSelectData) {
        this.mContext = context;
        this.items = items;
        this.onSelectData = xSelectData;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_kuliner, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final KulinerModel data = items.get(position);

        //Get Image
        Glide.with(mContext)
                .load(data.getGambarKuliner())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.imgKuliner);

        holder.tvNamaKuliner.setText(data.getTxtNamaKuliner());
        holder.rlListKuliner.setOnClickListener(new View.OnClickListener() {
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

        public TextView tvNamaKuliner;
        public RelativeLayout rlListKuliner;
        public ImageView imgKuliner;

        public ViewHolder(View itemView) {
            super(itemView);
            rlListKuliner = itemView.findViewById(R.id.rlListKuliner);
            tvNamaKuliner = itemView.findViewById(R.id.tvNamaKuliner);
            imgKuliner = itemView.findViewById(R.id.imgKuliner);
        }
    }
}

