package com.example.test26_04.utils;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.test26_04.R;
import com.example.test26_04.models.Product;

import java.util.List;

public class StorageAdapter  extends RecyclerView.Adapter<StorageAdapter.StorageViewHoler> {
    private List<Product> mListProduct;
    private Context mContext;
    private Class onClickItemDestination;

    public StorageAdapter(Context context, List<Product> mListProduct, Class onClickDestination) {
        this.mListProduct = mListProduct;
        this.mContext = context;
        this.onClickItemDestination = onClickDestination;
    }

    @NonNull
    @Override
    public StorageAdapter.StorageViewHoler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_listitem_nhap_xuat,parent,false);
        return new StorageAdapter.StorageViewHoler(view);
    }


    @Override
    public void onBindViewHolder(@NonNull StorageAdapter.StorageViewHoler holder, int position) {
        final Product product = mListProduct.get(position);

        if (product == null){
            return;
        }

        Glide.with(mContext).load(mListProduct.get(position).getImage()).into(holder.imgProduct);
        holder.tvName.setText(product.getName());
        holder.tvPrice.setText(String.valueOf(product.getPrice()));
        holder.tvStock.setText(String.valueOf(product.getStock()));

        holder.importBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, onClickItemDestination);
                Bundle bundle = new Bundle();
                bundle.putSerializable("importing product",product);
                intent.putExtras(bundle);
                mContext.startActivity(intent);
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

    public void updateProductList(Product product){
        for(int i=0; i<mListProduct.size(); i++){
            if (mListProduct.get(i).get_id().equals(product.get_id())){
                mListProduct.set(i, product);
                notifyItemChanged(i);
                break;
            }
        }
    }

    public class StorageViewHoler extends RecyclerView.ViewHolder{


        private ImageView imgProduct;
        private TextView tvName;
        private TextView tvPrice;
        private TextView tvStock;
        private Button importBtn;
        private Button exportBtn;



        public StorageViewHoler(@NonNull View itemView) {
            super(itemView);

            imgProduct = itemView.findViewById(R.id.itemImage);
            tvName = itemView.findViewById(R.id.itemName);
            tvPrice = itemView.findViewById(R.id.itemPrice);
            tvStock = itemView.findViewById(R.id.itemStock);
            importBtn = itemView.findViewById(R.id.importBtn);
            exportBtn = itemView.findViewById(R.id.exportBtn);

        }
    }
}
