package br.com.tercomfuncionario.Boundary.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

import br.com.tercomfuncionario.Adapter.ProductValueAdapter;
import br.com.tercomfuncionario.Adapter.ServicePriceAdapter;
import br.com.tercomfuncionario.Boundary.BoundaryUtil.AbstractAppCompatActivity;
import br.com.tercomfuncionario.Entity.ProductValue;
import br.com.tercomfuncionario.Entity.ServicePrice;
import br.com.tercomfuncionario.Interface.RecyclerViewOnClickListenerHack;
import br.com.tercomfuncionario.R;
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
