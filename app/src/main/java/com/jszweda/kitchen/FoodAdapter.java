package com.jszweda.kitchen;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.jaredrummler.fastscrollrecyclerview.FastScrollRecyclerView;
import com.jszweda.kitchen.databinding.RecyclerviewItemBinding;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

public class FoodAdapter extends RecyclerView.Adapter<FoodAdapter.FoodViewHolder>
        implements FastScrollRecyclerView.SectionedAdapter {
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

        RecyclerviewItemBinding recyclerviewItemBinding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                R.layout.recyclerview_item,
                parent,
                false
        );
//        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
//        View viewListItem = inflater.inflate(R.layout.recyclerview_item, parent, false);
//        FoodViewHolder viewHolder = new FoodViewHolder(viewListItem);
        return new FoodViewHolder(recyclerviewItemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull FoodViewHolder holder, int position) {
       final Food selectedFood = listOfFood.get(position);
       holder.recyclerviewItemBinding.setFood(selectedFood);

        String days = selectedFood.getDaysLeft() == 1 ? " dzień" : " dni";
//        holder.tvFoodName.setText(selectedFood.getFoodName());
//        holder.tvExpDate.setText("Ważność: " + selectedFood.getDaysLeft() + days);
//        holder.tvQuantity.setText("Ilość: " + selectedFood.getQuantity());
//        holder.tvWeight.setText("Waga: " + selectedFood.getWeight());
        holder.recyclerviewItemBinding.tvExpDate.setText(selectedFood.getDaysLeft() + days + " ważności");
        holder.itemView.setOnClickListener(view -> {
            if (listener != null){
                listener.onItemClick(position);
            }
        });

    }

    @Override
    public int getItemCount() {
        if (listOfFood != null) {
            return listOfFood.size();
        } else {
            return 0;
        }
    }


    @NonNull
    @Override
    public String getSectionName(int position) {
        return listOfFood.get(position).getFoodName();
    }


    public class FoodViewHolder extends RecyclerView.ViewHolder {
//        public TextView tvFoodName, tvWeight, tvExpDate, tvQuantity;

        private RecyclerviewItemBinding recyclerviewItemBinding;

        public FoodViewHolder(@NonNull RecyclerviewItemBinding recyclerviewItemBinding) {
            super(recyclerviewItemBinding.getRoot());
            this.recyclerviewItemBinding = recyclerviewItemBinding;
//            this.tvFoodName = itemView.findViewById(R.id.tvFoodName);
//            this.tvWeight = itemView.findViewById(R.id.tvWeight);
//            this.tvExpDate = itemView.findViewById(R.id.tvExpDate);
//            this.tvQuantity = itemView.findViewById(R.id.tvQuantity);
        }
    }

}

