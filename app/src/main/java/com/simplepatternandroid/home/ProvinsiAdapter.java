package com.simplepatternandroid.home;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.simplepatternandroid.R;
import com.simplepatternandroid.databinding.ItemProvinsiBinding;

import java.util.ArrayList;
import java.util.List;

public class ProvinsiAdapter extends RecyclerView.Adapter<ProvinsiAdapter.ViewHolder> {
    private List<ProvinsiDetailResponse> provinsiDetailResponseList;
    private OnItemClickListener itemClickListener;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ItemProvinsiBinding binding;

        public ViewHolder(ItemProvinsiBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

    }

    public ProvinsiAdapter(List<ProvinsiDetailResponse> provinsiDetailResponseList, OnItemClickListener onItemClickListener) {
        this.provinsiDetailResponseList = provinsiDetailResponseList;
        this.itemClickListener = onItemClickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemProvinsiBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_provinsi, parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.binding.setViewModel(new ProvinsiViewModel(provinsiDetailResponseList.get(position)));
        holder.binding.executePendingBindings();
        holder.binding.layoutItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                itemClickListener.onItemClick(position, provinsiDetailResponseList.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return provinsiDetailResponseList.size();
    }

    public interface OnItemClickListener {
        void onItemClick(int position, ProvinsiDetailResponse provinsiDetailResponse);
    }
}