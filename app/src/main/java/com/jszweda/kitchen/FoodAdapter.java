package com.jszweda.kitchen;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class FoodAdapter extends RecyclerView.Adapter<FoodAdapter.FoodViewHolder> {
    private Food[] listOfFood;

    public FoodAdapter(Food[] listOfFood) {
        this.listOfFood = listOfFood;
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
        final Food selectedFood = listOfFood[position];
        holder.tvFoodName.setText(selectedFood.getFoodName());
        holder.tvExpDate.setText(selectedFood.getExpirationDate().toString());
        holder.tvPieces.setText("Ilość sztuk: " + selectedFood.getPieces());
        holder.tvQuantity.setText("Waga: " + selectedFood.getQuantity());

    }

    @Override
    public int getItemCount() {
        return listOfFood.length;
    }

    public class FoodViewHolder extends RecyclerView.ViewHolder {
        public TextView tvFoodName, tvQuantity, tvExpDate, tvPieces;

        public FoodViewHolder(@NonNull View itemView) {
            super(itemView);
            this.tvFoodName = itemView.findViewById(R.id.tvFoodName);
            this.tvQuantity = itemView.findViewById(R.id.tvQuantity);
            this.tvExpDate = itemView.findViewById(R.id.tvExpDate);
            this.tvPieces = itemView.findViewById(R.id.tvPieces);
        }
    }

}
