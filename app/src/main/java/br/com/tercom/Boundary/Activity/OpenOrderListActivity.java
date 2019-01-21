package br.com.tercom.Boundary.Activity;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

import br.com.tercom.Adapter.DetailOrderListAdapter;
import br.com.tercom.Boundary.BoundaryUtil.AbstractAppCompatActivity;
import br.com.tercom.Entity.Manufacture;
import br.com.tercom.Entity.OrderItemProduct;
import br.com.tercom.Entity.Product;
import br.com.tercom.Entity.Provider;
import br.com.tercom.R;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class OpenOrderListActivity extends AbstractAppCompatActivity {

    private ArrayList<OrderItemProduct> orderItemProducts;
    private Product product;
    private Provider provider;
    private Manufacture manufacturer;

    @BindView(R.id.rv_OpenOrderDetail)
    RecyclerView rv_OpenOrderDetail;

    @OnClick(R.id.btnAttributeOrderFromList) void inicializeOrder(){
        createIntentAbs(InicializedOrderListActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open_order_detail);
        ButterKnife.bind(this);
        //createToolbar();
        populate();
        DetailOrderListAdapter detailOrderListAdapter = new DetailOrderListAdapter(this, orderItemProducts);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rv_OpenOrderDetail.setLayoutManager(layoutManager);
        rv_OpenOrderDetail.setAdapter(detailOrderListAdapter);
    }

    public void populate(){
        orderItemProducts = new ArrayList<OrderItemProduct>();
        for(int i = 0; i < 5; i++){
            OrderItemProduct orderItemProductPopulate = new OrderItemProduct();
            product = new Product();
            provider = new Provider();
            manufacturer = new Manufacture();
            product.setName("Teste");
            provider.setFantasyName("Teste");
            manufacturer.setFantasyName("Teste");
            orderItemProductPopulate.setProduct(product);
            orderItemProductPopulate.setProvider(provider);
            orderItemProductPopulate.setManufacturer(manufacturer);
            orderItemProductPopulate.setObservations("Teste");
            orderItemProducts.add(orderItemProductPopulate);
        }
    }

}
