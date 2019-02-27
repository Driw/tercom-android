package br.com.tercom.Boundary.Activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;

import br.com.tercom.Adapter.OrderListAllAdapter;
import br.com.tercom.Adapter.OrderListInitializedOrderAdapter;
import br.com.tercom.Adapter.OrderListOpenOrderAdapter;
import br.com.tercom.Boundary.BoundaryUtil.AbstractAppCompatActivity;
import br.com.tercom.Entity.CustomerEmployee;
import br.com.tercom.Entity.OrderRequest;
import br.com.tercom.Entity.TercomEmployee;
import br.com.tercom.Interface.RecyclerViewOnClickListenerHack;
import br.com.tercom.R;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class OpenOrderListActivity extends AbstractAppCompatActivity {

    private ArrayList<OrderRequest> list;
    private int selectedType;
    private static final int typeAll = 1;
    private static final int typeOpen = 2;
    private static final int typeInitialized = 3;

    @BindView(R.id.rv_OpenOrderDetail)
    RecyclerView rv_OpenOrderDetail;
    @OnClick(R.id.btnOrderListAll) void displayAllOrders(){
        setAdapter(typeAll);
    }
    @OnClick(R.id.btnOrderListOpen) void displayOpenOrder(){
        setAdapter(typeOpen);
    }
    @OnClick(R.id.btnOrderListInicialized) void displayInicializedOrder(){
        setAdapter(typeInitialized);
    }

    private void setAdapter(int type){
        if(rv_OpenOrderDetail.getAdapter() != null){
            rv_OpenOrderDetail.setAdapter(null);
        }
        switch (type){
            case typeAll:
                OrderListAllAdapter orderListAllAdapter = new OrderListAllAdapter(this, list);
                LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
                rv_OpenOrderDetail.setLayoutManager(layoutManager);
                rv_OpenOrderDetail.setAdapter(orderListAllAdapter);
                orderListAllAdapter.setmRecyclerViewOnClickListenerHack(new RecyclerViewOnClickListenerHack() {
                    @Override
                    public void onClickListener(View view, int position) {
                        createIntentAbs(InicializedOrderListActivity.class);
                    }
                });
                break;
            case typeOpen:
                OrderListOpenOrderAdapter orderListOpenOrderAdapter = new OrderListOpenOrderAdapter(this, list);
                LinearLayoutManager layoutManager1 = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
                rv_OpenOrderDetail.setLayoutManager(layoutManager1);
                rv_OpenOrderDetail.setAdapter(orderListOpenOrderAdapter);
                orderListOpenOrderAdapter.setmRecyclerViewOnClickListenerHack(new RecyclerViewOnClickListenerHack() {
                    @Override
                    public void onClickListener(View view, int position) {
                        createIntentAbs(InicializedOrderListActivity.class);
                    }
                });
                break;
            case typeInitialized:
                OrderListInitializedOrderAdapter orderListInitializedOrderAdapter = new OrderListInitializedOrderAdapter(this, list);
                LinearLayoutManager layoutManager2 = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
                rv_OpenOrderDetail.setLayoutManager(layoutManager2);
                rv_OpenOrderDetail.setAdapter(orderListInitializedOrderAdapter);
                orderListInitializedOrderAdapter.setmRecyclerViewOnClickListenerHack(new RecyclerViewOnClickListenerHack() {
                    @Override
                    public void onClickListener(View view, int position) {
                        createIntentAbs(InicializedOrderListActivity.class);
                    }
                });
                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open_order_detail);
        ButterKnife.bind(this);
        //createToolbar();
        populate();
        setAdapter(typeAll);
    }

    public void populate(){
        list = new ArrayList<OrderRequest>();
        for(int i = 0; i < 6; i++){
            OrderRequest orderRequest = new OrderRequest();
            CustomerEmployee customerEmployee = new CustomerEmployee();
            TercomEmployee tercomEmployee = new TercomEmployee();
            customerEmployee.setName("Customer Employee Teste");
            tercomEmployee.setName("Tercom Employee Teste");
            orderRequest.setCustomerEmployee(customerEmployee);
            orderRequest.setStatus(i);
            orderRequest.setTercomEmployee(tercomEmployee);
            list.add(orderRequest);
        }
    }

}
