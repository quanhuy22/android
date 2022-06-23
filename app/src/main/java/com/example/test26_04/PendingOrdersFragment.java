package com.example.test26_04;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.test26_04.models.Order;
import com.example.test26_04.utils.OrderAdapter2;
import com.example.test26_04.utils.OrderItemViewModel;
import com.google.gson.Gson;

import java.util.ArrayList;


public class PendingOrdersFragment extends Fragment  {

    private RecyclerView rvPendingOrders;
    private ArrayList<Order> pendingOrders;
    private Context context;
    private OrderItemViewModel viewModel;
    private OrderAdapter2 adapter;
    private OrderActivity mActivity;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public PendingOrdersFragment(ArrayList<Order>pendingOrders, Context context) {
        // Required empty public constructor
        this.pendingOrders = pendingOrders;
        this.context = context;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        viewModel = new ViewModelProvider(requireActivity()).get(OrderItemViewModel.class);

        View view = inflater.inflate(R.layout.fragment_pending_orders, container, false);
        rvPendingOrders = view.findViewById(R.id.rv_pending_orders);
        adapter = new OrderAdapter2(pendingOrders, context, viewModel);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        rvPendingOrders.setLayoutManager(linearLayoutManager);

        RecyclerView.ItemDecoration itemDecoration= new DividerItemDecoration(context, DividerItemDecoration.VERTICAL);
        rvPendingOrders.addItemDecoration(itemDecoration);
        rvPendingOrders.setAdapter(adapter);


        return view;
    }


}