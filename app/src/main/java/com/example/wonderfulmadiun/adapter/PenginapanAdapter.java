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
import com.example.wonderfulmadiun.model.PenginapanModel;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.List;


public class PenginapanAdapter extends RecyclerView.Adapter<PenginapanAdapter.ViewHolder> {

    private List<PenginapanModel> items;
    private PenginapanAdapter.onSelectData onSelectData;
    private Context mContext;

    public interface onSelectData {
        void onSelected(PenginapanModel modelNews);
    }

    public PenginapanAdapter(Context context, List<PenginapanModel> items, PenginapanAdapter.onSelectData xSelectData) {
        this.mContext = context;
        this.items = items;
        this.onSelectData = xSelectData;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_penginapan, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final PenginapanModel data = items.get(position);

        //Get Image
        Glide.with(mContext)
                .load(data.getGambarHotel())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.imgHotel);

        holder.tvNamaHotel.setText(data.getTxtNamaHotel());
        holder.rlListHotel.setOnClickListener(new View.OnClickListener() {
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

        public TextView tvNamaHotel;
        public RelativeLayout rlListHotel;
        public ImageView imgHotel;

        public ViewHolder(View itemView) {
            super(itemView);
            rlListHotel = itemView.findViewById(R.id.rlListHotel);
            tvNamaHotel = itemView.findViewById(R.id.tvNamaHotel);
            imgHotel = itemView.findViewById(R.id.imgHotel);
        }
    }
}

