package com.jszweda.kitchen;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class FoodAdapter extends RecyclerView.Adapter<FoodAdapter.FoodViewHolder> {
    private ArrayList<Food> listOfFood;
    private OnItemClickListener listener;

    public FoodAdapter(ArrayList<Food> listOfFood) {
        this.listOfFood = listOfFood;
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        this.listener = listener;
    }

    @NonNull
    @Override
    public FoodViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View viewListItem = inflater.inflate(R.layout.recyclerview_item, parent, false);

        FoodViewHolder viewHolder = new FoodViewHolder(viewListItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull FoodViewHolder holder, int position) {
        final Food selectedFood = listOfFood.get(position);

        String days = selectedFood.getDaysLeft() == 1 ? " dzień" : " dni";
        holder.tvFoodName.setText(selectedFood.getFoodName());
        holder.tvExpDate.setText("Ważność: " + selectedFood.getDaysLeft() + days);
        holder.tvQuantity.setText("Ilość: " + selectedFood.getQuantity());
        holder.tvWeight.setText("Waga: " + selectedFood.getWeight());

        holder.itemView.setOnClickListener(view -> {
            if (listener != null){
                listener.onItemClick(position);
            }
        });

    }

    @Override
    public int getItemCount() {
        return listOfFood.size();
    }

    public class FoodViewHolder extends RecyclerView.ViewHolder {
        public TextView tvFoodName, tvWeight, tvExpDate, tvQuantity;

        public FoodViewHolder(@NonNull View itemView) {
            super(itemView);
            this.tvFoodName = itemView.findViewById(R.id.tvFoodName);
            this.tvWeight = itemView.findViewById(R.id.tvWeight);
            this.tvExpDate = itemView.findViewById(R.id.tvExpDate);
            this.tvQuantity = itemView.findViewById(R.id.tvQuantity);
        }
    }

}

