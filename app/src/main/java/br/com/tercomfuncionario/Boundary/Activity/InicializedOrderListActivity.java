package br.com.tercomfuncionario.Boundary.Activity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Looper;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.google.gson.Gson;

import java.util.ArrayList;

import br.com.tercomfuncionario.Adapter.DetailOrderListAdapter;
import br.com.tercomfuncionario.Boundary.BoundaryUtil.AbstractAppCompatActivity;
import br.com.tercomfuncionario.Control.OrderItemControl;
import br.com.tercomfuncionario.Control.OrderQuoteControl;
import br.com.tercomfuncionario.Entity.ApiResponse;
import br.com.tercomfuncionario.Entity.OrderItemProductList;
import br.com.tercomfuncionario.Entity.OrderItemServiceList;
import br.com.tercomfuncionario.Entity.OrderQuote;
import br.com.tercomfuncionario.Entity.OrderRequest;
import br.com.tercomfuncionario.Interface.RecyclerViewOnClickListenerHack;
import br.com.tercomfuncionario.Interface.iNewOrderItem;
import br.com.tercomfuncionario.R;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static br.com.tercomfuncionario.Util.Util.toast;

public class InicializedOrderListActivity extends AbstractAppCompatActivity {

    private GetAllProductListTask getAllProductListTask;
    private OrderRequest orderRequest;
    private FinalizeOrderTask finalizeOrderTask;
    @BindView(R.id.rv_InicializedOrderDetail)
    RecyclerView rv_InicializedOrderDetail;

    @OnClick(R.id.btnRespondOrderFromList)
    void finilizeOrder() {
        finilize();
    }

    private void finilize() {
        if(finalizeOrderTask  == null || finalizeOrderTask.getStatus() != AsyncTask.Status.RUNNING){
            finalizeOrderTask = new FinalizeOrderTask(orderRequest.getId());
            finalizeOrderTask.execute();

        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicialized_order_detail);
        ButterKnife.bind(this);
//        createToolbar();
        try{
            orderRequest = new Gson().fromJson(getIntent().getExtras().getString("order"),OrderRequest.class);
        }catch (Exception e){
            e.printStackTrace();
        }
        initGetAllProducts();

    }

    private void createNewOrderList(final ArrayList<? extends iNewOrderItem> list) {
        DetailOrderListAdapter detailOrderListAdapter= new DetailOrderListAdapter(this, list);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        detailOrderListAdapter.setmRecyclerViewOnClickListenerHack(new RecyclerViewOnClickListenerHack() {
            @Override
            public void onClickListener(View view, int position) {
                Intent intent = new Intent();
                intent.setClass(InicializedOrderListActivity.this,OrderInsertValueActivity.class);
                intent.putExtra("orderId",orderRequest.getId());
                intent.putExtra("itemId",list.get(position).getId());
                intent.putExtra("isProduct",list.get(position).isProduct());
                startActivity(intent);
            }
        });
        rv_InicializedOrderDetail.setLayoutManager(layoutManager);
        rv_InicializedOrderDetail.setAdapter(detailOrderListAdapter);
    }


    private void initGetAllProducts(){
        if(getAllProductListTask == null || getAllProductListTask.getStatus() != AsyncTask.Status.RUNNING){
            getAllProductListTask = new GetAllProductListTask(orderRequest.getId());
            getAllProductListTask.execute();
        }
    }


    private class GetAllProductListTask extends AsyncTask<Void, Void, Void> {
        private ArrayList<iNewOrderItem> list;

        private ApiResponse<OrderItemProductList> apiResponseProduct;
        private ApiResponse<OrderItemServiceList> apiResponseService;
        private int id;

        public GetAllProductListTask (int id){
            list = new ArrayList<>();
            this.id = id;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            if(Looper.myLooper() == null) {
                Looper.prepare();
            }
            OrderItemControl orderItemControl = new OrderItemControl(InicializedOrderListActivity.this);
            apiResponseProduct = orderItemControl.getAllProducts(id);
            apiResponseService = orderItemControl.getAllServices(id);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            if(apiResponseProduct.getStatusBoolean()){
                list.addAll(apiResponseProduct.getResult().getList());
            }
            if(apiResponseService.getStatusBoolean()){
                list.addAll(apiResponseService.getResult().getList());
            }
            if(list.size() > 0)
                createNewOrderList(list);

        }
    }

    private class FinalizeOrderTask extends AsyncTask<Void,Void,Void> {
        private ApiResponse<OrderQuote> apiResponse;
        private int orderId;

        public FinalizeOrderTask(int orderId) {
            this.orderId = orderId;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            if(Looper.myLooper() == null)
                Looper.prepare();
            apiResponse = new OrderQuoteControl(InicializedOrderListActivity.this).quoted(orderId);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            if(apiResponse.getStatusBoolean()){
                toast(InicializedOrderListActivity.this,apiResponse.getMessage());
                finish();
                createIntentAbs(MenuActivity.class);
            }else{
                toast(InicializedOrderListActivity.this,apiResponse.getMessage());
            }
        }
    }
}