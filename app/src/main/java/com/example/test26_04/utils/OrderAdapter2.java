package com.example.test26_04.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.recyclerview.widget.RecyclerView;

import com.example.test26_04.R;
import com.example.test26_04.api_controller.orderAPI;
import com.example.test26_04.models.Order;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OrderAdapter2 extends RecyclerView.Adapter<OrderAdapter2.OrderViewHolder> {

    private ArrayList<Order> pendingOrders;
    private Context context;
    private OrderItemViewModel viewModel;

    public OrderAdapter2(ArrayList<Order> pendingOrders, Context context, OrderItemViewModel viewModel) {
        this.pendingOrders = pendingOrders;
        this.context = context;
        this.viewModel = viewModel;
    }

    @NonNull
    @Override
    public OrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_list_item_index,parent,false);
        return new OrderViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Order order = pendingOrders.get(position);
        if (order == null){
            return;
        }

        holder.tvCustomerName.setText(order.getCustomerInfo().getName());
        holder.tvOrderId.setText(order.get_id());
        holder.tvTotalPrice.setText(String.valueOf(order.getTotalPrice()));
        holder.tvStatus.setText(order.getStatus());

        holder.btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                orderAPI.apiService
                        .putConfirmOrder(order.get_id())
                        .enqueue(new Callback<String>() {
                            @Override
                            public void onResponse(Call<String> call, Response<String> response) {
                                Toast.makeText(context, response.body(), Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onFailure(Call<String> call, Throwable t) {
                                Toast.makeText(context, "Can not call api", Toast.LENGTH_SHORT).show();
                            }
                        });

                Order havingConfirmedOrder = pendingOrders.get(position);
                havingConfirmedOrder.setStatus("confirmed");
                viewModel.setConfirmedOrder(havingConfirmedOrder);
                pendingOrders.remove(position);
                notifyItemRemoved(position);
                notifyItemRangeChanged(position,pendingOrders.size());
            }
        });

        holder.btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                orderAPI.apiService
                        .putCancelOrder(order.get_id())
                        .enqueue(new Callback<String>() {
                            @Override
                            public void onResponse(Call<String> call, Response<String> response) {
                                Toast.makeText(context, response.body(), Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onFailure(Call<String> call, Throwable t) {
                                Toast.makeText(context, "Can not call api", Toast.LENGTH_SHORT).show();
                            }
                        });

                Order havingCancelledOrder = pendingOrders.get(position);
                havingCancelledOrder.setStatus("cancelled");
                viewModel.setCancelledOrder(havingCancelledOrder);
                pendingOrders.remove(position);
                notifyItemRemoved(position);
                notifyItemRangeChanged(position,pendingOrders.size());
            }
        });

    }

    @Override
    public int getItemCount() {
        if (pendingOrders != null)
            return pendingOrders.size();
        else return 0;
    }

    public void addCreatedOrder(Order order){
        pendingOrders.add(order);
        notifyDataSetChanged();
    }


    public class OrderViewHolder extends RecyclerView.ViewHolder {
        private TextView tvCustomerName;
        private TextView tvOrderId;
        private TextView tvStatus;
        private TextView tvTotalPrice;
        private Button btnConfirm;
        private Button btnCancel;


        public OrderViewHolder(@NonNull View itemView) {
            super(itemView);
            tvCustomerName = itemView.findViewById(R.id.tv_order_customer_name);
            tvOrderId = itemView.findViewById(R.id.tv_order_id);
            tvStatus = itemView.findViewById(R.id.tv_order_status);
            tvTotalPrice = itemView.findViewById(R.id.tv_order_total_price);
            btnConfirm = itemView.findViewById(R.id.btn_confirm_order);
            btnCancel = itemView.findViewById(R.id.btn_cancel_order);
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

        public Button getBtnConfirm() {
            return btnConfirm;
        }

        public Button getBtnCancel() {
            return btnCancel;
        }
    }
}
