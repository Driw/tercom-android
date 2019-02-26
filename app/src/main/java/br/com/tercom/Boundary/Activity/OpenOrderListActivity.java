package br.com.tercom.Boundary.Activity;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;

import br.com.tercom.Adapter.DetailOrderListAdapter;
import br.com.tercom.Boundary.BoundaryUtil.AbstractAppCompatActivity;
import br.com.tercom.Entity.Manufacture;
import br.com.tercom.Entity.OrderItemProduct;
import br.com.tercom.Entity.Product;
import br.com.tercom.Entity.Provider;
import br.com.tercom.Interface.RecyclerViewOnClickListenerHack;
import br.com.tercom.Interface.iNewOrderItem;
import br.com.tercom.R;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class OpenOrderListActivity extends AbstractAppCompatActivity {

    private ArrayList<iNewOrderItem> list;
    private Product product;
    private Provider provider;
    private Manufacture manufacturer;

    @BindView(R.id.rv_OpenOrderDetail)
    RecyclerView rv_OpenOrderDetail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open_order_detail);
        ButterKnife.bind(this);
        //createToolbar();
        populate();
        DetailOrderListAdapter detailOrderListAdapter = new DetailOrderListAdapter(this, list);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rv_OpenOrderDetail.setLayoutManager(layoutManager);
        rv_OpenOrderDetail.setAdapter(detailOrderListAdapter);
        detailOrderListAdapter.setmRecyclerViewOnClickListenerHack(new RecyclerViewOnClickListenerHack() {
            @Override
            public void onClickListener(View view, int position) {
                createIntentAbs(InicializedOrderListActivity.class);
            }
        });
    }

    public void populate(){
        list = new ArrayList<iNewOrderItem>();
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
            list.add(orderItemProductPopulate);
        }
    }

}
