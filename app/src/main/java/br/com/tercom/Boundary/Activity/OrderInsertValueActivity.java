package br.com.tercom.Boundary.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.CardView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

import br.com.tercom.Adapter.ProductValueAdapter;
import br.com.tercom.Adapter.ServicePriceAdapter;
import br.com.tercom.Boundary.BoundaryUtil.AbstractAppCompatActivity;
import br.com.tercom.Entity.LastUpdate;
import br.com.tercom.Entity.Manufacture;
import br.com.tercom.Entity.Product;
import br.com.tercom.Entity.ProductPackage;
import br.com.tercom.Entity.ProductType;
import br.com.tercom.Entity.ProductValue;
import br.com.tercom.Entity.Provider;
import br.com.tercom.Entity.ServicePrice;
import br.com.tercom.Interface.RecyclerViewOnClickListenerHack;
import br.com.tercom.R;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class OrderInsertValueActivity extends AbstractAppCompatActivity {

    private static int selectedItemType;
    private static final int typeProduct = 1;
    private static final int typeService = 2;

    ArrayList<ProductValue> produtos;
    ArrayList<ServicePrice> services;

    @BindView(R.id.rv_OrderInsertValue)
    RecyclerView rv_OrderInsertValue;
    @BindView(R.id.txtOrderItemAddinfo)
    TextView txtOrderItemAddinfo;

    @OnClick(R.id.btnOrderInsertPrices) void insertValuesInOrder() {

    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_insert_values);
        ButterKnife.bind(this);
        //createToolbar();
        Intent i = getIntent();

        setAdapter(selectedItemType);
    }

    private void setAdapter(int type){
        if(rv_OrderInsertValue.getAdapter() != null){
            rv_OrderInsertValue.setAdapter(null);
        }
        switch (type){
            case typeProduct:
                final ProductValueAdapter productValueAdapter = new ProductValueAdapter(this, produtos);
                GridLayoutManager layoutManagerProducts = new GridLayoutManager(this, 2);
                rv_OrderInsertValue.setLayoutManager(layoutManagerProducts);
                rv_OrderInsertValue.setAdapter(productValueAdapter);
                productValueAdapter.setmRecyclerViewOnClickListenerHack(new RecyclerViewOnClickListenerHack() {
                    @Override
                    public void onClickListener(View view, int position) {
                        if (produtos.get(position).isSelected()){
                            produtos.get(position).setSelected(false);
                        } else {produtos.get(position).setSelected(true);}
                        productValueAdapter.notifyItemChanged(position);
                    }
                });
                break;
            case typeService:
                final ServicePriceAdapter servicePriceAdapter = new ServicePriceAdapter(this, services);
                GridLayoutManager layoutManagerServices = new GridLayoutManager(this, 2);
                rv_OrderInsertValue.setLayoutManager(layoutManagerServices);
                rv_OrderInsertValue.setAdapter(servicePriceAdapter);
                servicePriceAdapter.setmRecyclerViewOnClickListenerHack(new RecyclerViewOnClickListenerHack() {
                    @Override
                    public void onClickListener(View view, int position) {
                        if (produtos.get(position).isSelected()){
                            produtos.get(position).setSelected(false);
                        } else {produtos.get(position).setSelected(true);}
                        servicePriceAdapter.notifyItemChanged(position);
                    }
                });
        }
    }

}
