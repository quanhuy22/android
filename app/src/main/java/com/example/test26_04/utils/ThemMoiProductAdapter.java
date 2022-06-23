package com.example.test26_04.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.test26_04.R;
import com.example.test26_04.models.ProductIdAndQuantity;
import com.example.test26_04.models.Product;

import java.util.ArrayList;

public class ThemMoiProductAdapter extends RecyclerView.Adapter<ThemMoiProductAdapter.ProductViewHolder>{
    private ArrayList<Product> mListProduct;
    private Context mContext;
    private ArrayList<ProductIdAndQuantity> productIdAndQuantities = new ArrayList<>();

    public ThemMoiProductAdapter(Context context, ArrayList<Product> mListProduct) {
        this.mListProduct = mListProduct;
        this.mContext = context;
        for(Product product : mListProduct){
            productIdAndQuantities.add(new ProductIdAndQuantity(product.get_id(), 0));
        }
    }

    @NonNull
    @Override
    public ThemMoiProductAdapter.ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_list_item,parent,false);
        return new ProductViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull ThemMoiProductAdapter.ProductViewHolder holder, @SuppressLint("RecyclerView") int position) {
        final Product product = mListProduct.get(position);

        if (product == null){
            return;
        }

        Glide.with(mContext).load(mListProduct.get(position).getImage()).into(holder.imgProduct);
        holder.tvName.setText(product.getName());
        holder.tvPrice.setText("Price: " + product.getPrice());
        holder.tvStock.setText("Remaining: " + product.getStock());
        holder.tvQuantity.setText("0");

        holder.increaseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int currentQuantity = Integer.parseInt(holder.tvQuantity.getText().toString());
                holder.tvQuantity.setText(String.valueOf(currentQuantity + 1));
                productIdAndQuantities.get(position).setQuantity(currentQuantity + 1);
            }
        });

        holder.decreaseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int currentQuantity = Integer.parseInt(holder.tvQuantity.getText().toString());
                if (currentQuantity > 0){
                    holder.tvQuantity.setText(String.valueOf(currentQuantity - 1));
                    productIdAndQuantities.get(position).setQuantity(currentQuantity - 1);
                }
            }
        });
    }

    public void release(){
        mContext = null;
    }

    @Override
    public int getItemCount() {
        if(mListProduct != null ) {
            return mListProduct.size();
        }
        return 0;
    }

    public ArrayList<ProductIdAndQuantity> getProductIdAndQuantities(){
        return this.productIdAndQuantities;
    }

    public ArrayList<Product> getmListProduct() {
        return mListProduct;
    }

    public class ProductViewHolder extends RecyclerView.ViewHolder{

        private ImageView imgProduct;
        private TextView tvName;
        private TextView tvPrice;
        private TextView tvQuantity;
        private TextView tvStock;
        private ImageButton increaseBtn;
        private ImageButton decreaseBtn;


        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            imgProduct = itemView.findViewById(R.id.item_product_image);
            tvName = itemView.findViewById(R.id.item_product_name);
            tvPrice = itemView.findViewById(R.id.item_product_price);
            tvStock = itemView.findViewById(R.id.item_product_stock);
            tvQuantity = itemView.findViewById(R.id.item_product_quantity);
            increaseBtn = itemView.findViewById(R.id.increase_button);
            decreaseBtn = itemView.findViewById(R.id.decrease_button);
        }

        public ImageView getImgProduct() {
            return imgProduct;
        }

        public TextView getTvName() {
            return tvName;
        }

        public TextView getTvPrice() {
            return tvPrice;
        }

        public TextView getTvQuantity() {
            return tvQuantity;
        }

        public TextView getTvStock() {
            return tvStock;
        }

        public ImageButton getIncreaseBtn() {
            return increaseBtn;
        }

        public ImageButton getDecreaseBtn() {
            return decreaseBtn;
        }
    }
}
