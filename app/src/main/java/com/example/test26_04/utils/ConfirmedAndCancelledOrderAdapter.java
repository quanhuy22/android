package com.example.test26_04.utils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.test26_04.R;
import com.example.test26_04.models.Order;

import java.util.ArrayList;

public class ConfirmedAndCancelledOrderAdapter extends RecyclerView.Adapter<ConfirmedAndCancelledOrderAdapter.OrderViewHolder> {

    private ArrayList<Order> orders;
    private Context context;
    private OrderItemViewModel viewModel;

    public ConfirmedAndCancelledOrderAdapter(ArrayList<Order> orders, Context context) {
        this.orders = orders;
        this.context = context;
    }

    @NonNull
    @Override
    public OrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.confirmed_and_cancelled_order_item,parent,false);
        return new OrderViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderViewHolder holder, int position) {
        Order order = orders.get(position);

        if (order == null){
            return;
        }

        holder.tvCustomerName.setText(order.getCustomerInfo().getName());
        holder.tvOrderId.setText(order.get_id());
        holder.tvTotalPrice.setText(String.valueOf(order.getTotalPrice()));
        holder.tvStatus.setText(order.getStatus());
    }


    @Override
    public int getItemCount() {
        if (orders != null)
            return orders.size();
        else return 0;
    }

    public class OrderViewHolder extends RecyclerView.ViewHolder  {
        private TextView tvCustomerName;
        private TextView tvOrderId;
        private TextView tvStatus;
        private TextView tvTotalPrice;



        public OrderViewHolder(@NonNull View itemView) {
            super(itemView);
            tvCustomerName = itemView.findViewById(R.id.tv_order_customer_name);
            tvOrderId = itemView.findViewById(R.id.tv_order_id);
            tvStatus = itemView.findViewById(R.id.tv_order_status);
            tvTotalPrice = itemView.findViewById(R.id.tv_order_total_price);

        }

        public TextView getTvCustomerName() {
            return tvCustomerName;
        }

        public TextView getTvOrderId() {
            return tvOrderId;
        }

        public TextView getTvStatus() {
            return tvStatus;
        }

        public TextView getTvTotalPrice() {
            return tvTotalPrice;
        }
    }
}
