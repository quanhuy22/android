package com.example.test26_04;

import android.content.Context;
import android.os.Bundle;

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
import com.example.test26_04.utils.OrderItemViewModel;

import java.util.ArrayList;


public class CancelledOrdersFragment extends Fragment {

    private Context context;
    private RecyclerView rvConfirmedOrders;
    private ConfirmedAndCancelledOrderAdapter adapter;
    private ArrayList<Order> cancelledOrders;
    private OrderItemViewModel viewModel;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public CancelledOrdersFragment(ArrayList<Order> cancelledOrders, Context context) {
        // Required empty public constructor
        this.context = context;
        this.cancelledOrders = cancelledOrders;
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
        viewModel = new ViewModelProvider(requireActivity()).get(OrderItemViewModel.class);

        View view = inflater.inflate(R.layout.fragment_cancelled_orders, container, false);
        rvConfirmedOrders = view.findViewById(R.id.rv_cancelled_orders);

        adapter = new ConfirmedAndCancelledOrderAdapter(cancelledOrders, context);
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
        Order havingCancelledOrder = viewModel.getCancelledOrder().getValue();
        cancelledOrders.add(havingCancelledOrder);
        adapter.notifyDataSetChanged();
    }
}