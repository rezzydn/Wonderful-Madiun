package com.example.wonderfulmadiun.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wonderfulmadiun.R;
import com.example.wonderfulmadiun.model.PrayplaceModel;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.List;


public class PrayplaceAdapter extends RecyclerView.Adapter<PrayplaceAdapter.ViewHolder> {

    private List<PrayplaceModel> items;
    private PrayplaceAdapter.onSelectData onSelectData;
    private Context mContext;

    public interface onSelectData {
        void onSelected(PrayplaceModel modelPrayplace);
    }

    public PrayplaceAdapter(Context context, List<PrayplaceModel> items, PrayplaceAdapter.onSelectData xSelectData) {
        this.mContext = context;
        this.items = items;
        this.onSelectData = xSelectData;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_prayplace, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final PrayplaceModel data = items.get(position);

        //Get Image
        Glide.with(mContext)
                .load(data.getGambarPrayplace())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.imgPrayplace);

        holder.tvKategori.setText(data.getKategoriPrayplace());
        holder.tvPrayplace.setText(data.getTxtNamaPrayplace());
        holder.cvPrayplace.setOnClickListener(new View.OnClickListener() {
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

        public TextView tvPrayplace;
        public TextView tvKategori;
        public CardView cvPrayplace;
        public ImageView imgPrayplace;

        public ViewHolder(View itemView) {
            super(itemView);
            cvPrayplace = itemView.findViewById(R.id.cvPrayplace);
            tvPrayplace = itemView.findViewById(R.id.tvPrayplace);
            tvKategori = itemView.findViewById(R.id.tvKategori);
            imgPrayplace = itemView.findViewById(R.id.imgPrayplace);
        }
    }

}

