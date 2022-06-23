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

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.test26_04.models.Order;
import com.example.test26_04.utils.ConfirmedAndCancelledOrderAdapter;
import com.example.test26_04.utils.OrderAdapter2;
import com.example.test26_04.utils.OrderItemViewModel;

import java.util.ArrayList;


public class ConfirmedOrdersFragment extends Fragment {

    private Context context;
    private RecyclerView rvConfirmedOrders;
    private ArrayList<Order> confirmedOrders;
    private OrderItemViewModel viewModel;
    private ConfirmedAndCancelledOrderAdapter adapter;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ConfirmedOrdersFragment(ArrayList<Order> confirmedOrders, Context context) {
        // Required empty public constructor
        this.context = context;
        this.confirmedOrders = confirmedOrders;
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

        View view = inflater.inflate(R.layout.fragment_confirmed_orders, container, false);
        rvConfirmedOrders = view.findViewById(R.id.rv_confirmed_orders);

        adapter = new ConfirmedAndCancelledOrderAdapter(confirmedOrders, context);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        rvConfirmedOrders.setLayoutManager(linearLayoutManager);

        RecyclerView.ItemDecoration itemDecoration= new DividerItemDecoration(context, DividerItemDecoration.VERTICAL);
        rvConfirmedOrders.addItemDecoration(itemDecoration);
        rvConfirmedOrders.setAdapter(adapter);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        Order havingConfirmedOrder = viewModel.getConfirmedOrder().getValue();
        confirmedOrders.add(havingConfirmedOrder);
        adapter.notifyDataSetChanged();
    }
}