package com.example.test26_04.utils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.test26_04.R;
import com.example.test26_04.models.Product;
import com.example.test26_04.models.ProductIdAndQuantity;

import java.util.ArrayList;

public class ConfirmBuyingProductAdapter extends RecyclerView.Adapter<ConfirmBuyingProductAdapter.ProductViewHolder>{

    private Context mContext;
    private ArrayList<ProductIdAndQuantity> productIdAndQuantities;
    private ArrayList<Product> buyingProductList;

    public ConfirmBuyingProductAdapter(
            Context mContext,
            ArrayList<ProductIdAndQuantity> productIdAndQuantities,
            ArrayList<Product> buyingProductList
    ) {
        this.mContext = mContext;
        this.productIdAndQuantities = productIdAndQuantities;
        this.buyingProductList = buyingProductList;
    }

    @NonNull
    @Override
    public ConfirmBuyingProductAdapter.ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_list_item_confirm,parent,false);
        return new ConfirmBuyingProductAdapter.ProductViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull ConfirmBuyingProductAdapter.ProductViewHolder holder,  int position) {
        ProductIdAndQuantity productIdAndQuantity = productIdAndQuantities.get(position);
        Product buyingProduct = buyingProductList.get(position);
        if (productIdAndQuantity == null){
            return;
        }

        Glide.with(mContext).load(buyingProductList.get(position).getImage()).into(holder.ivBuyingProductImage);
        holder.tvBuyingProductName.setText(buyingProduct.getName());
        holder.tvBuyingProductPrice.setText("Price: " + buyingProduct.getPrice());
        holder.tvBuyingProductQuantity.setText("Quantity: " + productIdAndQuantity.getQuantity());
    }

    public void release(){
        mContext = null;
    }

    @Override
    public int getItemCount() {
        if(buyingProductList != null ) {
            return buyingProductList.size();
        }
        return 0;
    }

    public ArrayList<ProductIdAndQuantity> getBuyingProductList(){
        return this.productIdAndQuantities;
    }

    public float getTotalPrice(){
        float totalPrice = 0;
        for(int i=0; i<buyingProductList.size(); i++){
            float price = buyingProductList.get(i).getPrice();
            int quantity = productIdAndQuantities.get(i).getQuantity();
            totalPrice += price * quantity;
        }

        return totalPrice;
    }

    public class ProductViewHolder extends RecyclerView.ViewHolder{

        private ImageView ivBuyingProductImage;
        private TextView tvBuyingProductName;
        private TextView tvBuyingProductPrice;
        private TextView tvBuyingProductQuantity;

        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            ivBuyingProductImage = itemView.findViewById(R.id.iv_buying_product_image);
            tvBuyingProductName = itemView.findViewById(R.id.tv_buying_product_name);
            tvBuyingProductPrice = itemView.findViewById(R.id.tv_buying_product_price);
            tvBuyingProductQuantity = itemView.findViewById(R.id.tv_buying_product_quantity);
        }

        public ImageView getIvBuyingProductImage() {
            return ivBuyingProductImage;
        }

        public TextView getTvBuyingProductName() {
            return tvBuyingProductName;
        }

        public TextView getTvBuyingProductPrice() {
            return tvBuyingProductPrice;
        }

        public TextView getTvBuyingProductQuantity() {
            return tvBuyingProductQuantity;
        }
    }
}

