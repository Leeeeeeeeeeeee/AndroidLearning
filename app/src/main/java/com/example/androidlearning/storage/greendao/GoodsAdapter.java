package com.example.androidlearning.storage.greendao;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.androidlearning.R;
import com.example.androidlearning.storage.greendao.model.GoodsModel;

import java.util.List;

public class GoodsAdapter extends RecyclerView.Adapter<GoodsAdapter.MyViewHolder> {

    private List<GoodsModel> goodsModels;

    public void setGoodsModels(List<GoodsModel> goodsModels) {
        this.goodsModels = goodsModels;
        notifyDataSetChanged();
    }

    private Context context;

    public GoodsAdapter(Context context) {
        this.context = context;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView mTvName, mTvDescription;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            mTvName = itemView.findViewById(R.id.tv_name);
            mTvDescription = itemView.findViewById(R.id.tv_description);
        }
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.item_goods, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        final GoodsModel goodsModel = goodsModels.get(position);

        holder.mTvName.setText(goodsModel.getName());
        holder.mTvDescription.setText(goodsModel.getInfo());
        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, GoodsDetailsActivity.class);
            intent.putExtra("goodsModel", goodsModel);
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return goodsModels == null ? 0 : goodsModels.size();
    }
}
