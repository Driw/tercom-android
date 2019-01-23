package br.com.tercom.Boundary.Activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import java.util.ArrayList;

import br.com.tercom.Adapter.ProductValueAdapter;
import br.com.tercom.Boundary.BoundaryUtil.AbstractAppCompatActivity;
import br.com.tercom.Entity.LastUpdate;
import br.com.tercom.Entity.Manufacture;
import br.com.tercom.Entity.Product;
import br.com.tercom.Entity.ProductPackage;
import br.com.tercom.Entity.ProductType;
import br.com.tercom.Entity.ProductValue;
import br.com.tercom.Entity.Provider;
import br.com.tercom.R;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class OrderInsertValueActivity extends AbstractAppCompatActivity {

    private ArrayList<ProductValue> providers;

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
        populate();
        ProductValueAdapter productValueAdapter = new ProductValueAdapter(this, providers);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 2);
        rv_OrderInsertValue.setLayoutManager(layoutManager);
        rv_OrderInsertValue.setAdapter(productValueAdapter);
    }

    public void populate() {
        providers = new ArrayList<ProductValue>();
        for(int i = 0; i < 5; i++){
            ProductValue p = new ProductValue();
            Product pr = new Product();
            Manufacture m = new Manufacture();
            Provider pro = new Provider();
            ProductPackage p2 = new ProductPackage();
            ProductType p3 = new ProductType();
            LastUpdate l = new LastUpdate();
            p.setLastUpdate(l);
            p.setType(p3);
            p.setPackage(p2);
            p.setProduct(pr);
            p.setManufacture(m);
            p.setProvider(pro);
            p.setName("Teste");
            providers.add(p);
        }
    }

}
