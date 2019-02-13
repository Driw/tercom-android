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
import br.com.tercom.Entity.OrderItemService;
import br.com.tercom.Entity.Product;
import br.com.tercom.Entity.Provider;
import br.com.tercom.Entity.Services;
import br.com.tercom.Interface.RecyclerViewOnClickListenerHack;
import br.com.tercom.Interface.iNewOrderItem;
import br.com.tercom.R;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class InicializedOrderListActivity extends AbstractAppCompatActivity {

    private ArrayList<iNewOrderItem> list;
    private Product product;
    private Provider provider;
    private Manufacture manufacturer;

    @BindView(R.id.rv_InicializedOrderDetail)
    RecyclerView rv_InicializedOrderDetail;

    @OnClick(R.id.btnRespondOrderFromList)
    void finilizeOrder() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicialized_order_detail);
        ButterKnife.bind(this);
        //createToolbar();
        populate();
        DetailOrderListAdapter detailOrderListAdapter = new DetailOrderListAdapter(this, list);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rv_InicializedOrderDetail.setLayoutManager(layoutManager);
        rv_InicializedOrderDetail.setAdapter(detailOrderListAdapter);
        detailOrderListAdapter.setmRecyclerViewOnClickListenerHack(new RecyclerViewOnClickListenerHack() {
            @Override
            public void onClickListener(View view, int position) {
                createIntentAbs(OrderInsertValueActivity.class);
            }
        });
    }

    public void populate() {
        list = new ArrayList<iNewOrderItem>();
        OrderItemProduct orderItemProduct = new OrderItemProduct();
        OrderItemService orderItemService = new OrderItemService();
        Product product = new Product();
        Services services = new Services();
        Provider provider = new Provider();
        Manufacture manufacture = new Manufacture();

        product.setName("Produto Teste");
        services.setName("Serviço Teste");
        provider.setFantasyName("Fornecedor Teste");
        manufacture.setFantasyName("Fabricante Teste");

        orderItemProduct.setId(1);
        orderItemProduct.setProduct(product);
        orderItemProduct.setProvider(provider);
        orderItemProduct.setManufacturer(manufacture);
        orderItemProduct.setObservations("teste");

        orderItemService.setId(1);
        orderItemService.setService(services);
        orderItemService.setProvider(provider);
        orderItemService.setObservations("teste");

        list.add(orderItemProduct);
        list.add(orderItemService);
    }

}