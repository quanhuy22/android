package com.example.test26_04.utils;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.lifecycle.Lifecycle;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.test26_04.CancelledOrdersFragment;
import com.example.test26_04.ConfirmedOrdersFragment;
import com.example.test26_04.PendingOrdersFragment;
import com.example.test26_04.models.Order;

import java.util.ArrayList;

public class ViewPagerAdapter extends FragmentStatePagerAdapter {

    private Context context;
    private ArrayList<Order> pendingOrders;
    private ArrayList<Order> confirmedOrders;
    private ArrayList<Order> cancelledOrders;

    public ViewPagerAdapter(Context context,
                            ArrayList<Order> pendingOrders,
                            ArrayList<Order> confirmedOrders,
                            ArrayList<Order> cancelledOrders,
                            @NonNull FragmentManager fm,
                            int behavior
    ) {
        super(fm, behavior);
        this.context = context;
        this.pendingOrders = pendingOrders;
        this.confirmedOrders = confirmedOrders;
        this.cancelledOrders = cancelledOrders;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new PendingOrdersFragment(pendingOrders, context);
            case 1:
                return new ConfirmedOrdersFragment(confirmedOrders, context);
            case 2:
                return new CancelledOrdersFragment(cancelledOrders, context);
            default:
                return new PendingOrdersFragment(pendingOrders, context);
        }
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        if (position == 0) return "Pending";
        else if (position == 1) return "Confirmed";
        else return "Cancelled";
    }

    public void addCreatedOrder(Order order){
        pendingOrders.add(order);
        notifyDataSetChanged();
    }


}
