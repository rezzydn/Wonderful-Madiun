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
import com.example.wonderfulmadiun.model.PerbelanjaanModel;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.List;


public class PerbelanjaanAdapter extends RecyclerView.Adapter<PerbelanjaanAdapter.ViewHolder> {

    private List<PerbelanjaanModel> items;
    private PerbelanjaanAdapter.onSelectData onSelectData;
    private Context mContext;

    public interface onSelectData {
        void onSelected(PerbelanjaanModel modelNews);
    }

    public PerbelanjaanAdapter(Context context, List<PerbelanjaanModel> items, PerbelanjaanAdapter.onSelectData xSelectData) {
        this.mContext = context;
        this.items = items;
        this.onSelectData = xSelectData;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_perbelanjaan, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final PerbelanjaanModel data = items.get(position);

        //Get Image
        Glide.with(mContext)
                .load(data.getGambarShop())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.imgShop);

        holder.tvNamaShop.setText(data.getTxtNamaShop());
        holder.rlListShop.setOnClickListener(new View.OnClickListener() {
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

        public TextView tvNamaShop;
        public RelativeLayout rlListShop;
        public ImageView imgShop;

        public ViewHolder(View itemView) {
            super(itemView);
            rlListShop = itemView.findViewById(R.id.rlListShop);
            tvNamaShop = itemView.findViewById(R.id.tvNamaShop);
            imgShop = itemView.findViewById(R.id.imgShop);
        }
    }
}
