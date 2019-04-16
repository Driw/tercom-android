package br.com.tercomfuncionario.Boundary.Activity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

import br.com.tercomfuncionario.Adapter.ProductValueAdapter;
import br.com.tercomfuncionario.Adapter.ServicePriceAdapter;
import br.com.tercomfuncionario.Boundary.BoundaryUtil.AbstractAppCompatActivity;
import br.com.tercomfuncionario.Control.QuotedProductPriceControl;
import br.com.tercomfuncionario.Control.QuotedServicePriceControl;
import br.com.tercomfuncionario.Entity.ApiResponse;
import br.com.tercomfuncionario.Entity.ProductValue;
import br.com.tercomfuncionario.Entity.ProductValueList;
import br.com.tercomfuncionario.Entity.ServicePrice;
import br.com.tercomfuncionario.Entity.ServicePriceList;
import br.com.tercomfuncionario.Interface.RecyclerViewOnClickListenerHack;
import br.com.tercomfuncionario.R;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class OrderInsertValueActivity extends AbstractAppCompatActivity {

    private static int selectedItemType;
    private static final int typeProduct = 1;
    private static final int typeService = 2;
    private int orderId;
    private int itemId;
    private ProductValueTask productValueTask;
    private ServicePriceTask servicePriceTask;
    private AddProductTask addProductTask;
    private RemoveProductTask removeProductTask;
    private AddServiceTask addServiceTask;
    private RemoveServiceTask removeServiceTask;
    private ServicePriceAdapter servicePriceAdapter;
    private ProductValueAdapter productValueAdapter;
    private int orderQuoteId;


    ArrayList<ProductValue> produtos;
    ArrayList<ServicePrice> services;

    @BindView(R.id.rv_OrderInsertValue)
    RecyclerView rv_OrderInsertValue;
    @BindView(R.id.txtOrderItemAddinfo)
    TextView txtOrderItemAddinfo;

    @OnClick(R.id.btnOrderInsertPrices) void insertValuesInOrder() {
        finish();

    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_insert_values);
        ButterKnife.bind(this);
        Intent i = getIntent();
        orderId = i.getExtras().getInt("requestId");
        orderQuoteId = i.getExtras().getInt("quoteId");
        itemId = i.getExtras().getInt("itemId");
        selectedItemType = i.getExtras().getBoolean("isProduct")? typeProduct : typeService;
        if(selectedItemType == typeProduct){
            initProductTask();
        }else{
            initServiceTask();
        }
    }

    private void initProductTask(){
        if(productValueTask == null || productValueTask.getStatus() != AsyncTask.Status.RUNNING){
            productValueTask = new ProductValueTask(orderId,itemId);
            productValueTask.execute();
        }
    }

    private void initServiceTask(){
        if(servicePriceTask == null || servicePriceTask.getStatus() != AsyncTask.Status.RUNNING){
            servicePriceTask = new ServicePriceTask(orderId,itemId);
            servicePriceTask.execute();
        }
    }

    private void setAdapter(int type){
        if(rv_OrderInsertValue.getAdapter() != null){
            rv_OrderInsertValue.setAdapter(null);
        }
        switch (type){
            case typeProduct:
                productValueAdapter = new ProductValueAdapter(this, produtos);
                GridLayoutManager layoutManagerProducts = new GridLayoutManager(this, 2);
                rv_OrderInsertValue.setLayoutManager(layoutManagerProducts);
                rv_OrderInsertValue.setAdapter(productValueAdapter);
                productValueAdapter.setmRecyclerViewOnClickListenerHack(new RecyclerViewOnClickListenerHack() {
                    @Override
                    public void onClickListener(View view, int position) {
                        if (!produtos.get(position).isSelected())
                        {
                            addProduct(orderId,produtos.get(position).getId(),itemId,position);
                        } else
                        {
                            removeProduct(orderId,produtos.get(position).getId(),position);
                        }
                    }
                });
                break;
            case typeService:
                servicePriceAdapter = new ServicePriceAdapter(this, services);
                GridLayoutManager layoutManagerServices = new GridLayoutManager(this, 2);
                rv_OrderInsertValue.setLayoutManager(layoutManagerServices);
                rv_OrderInsertValue.setAdapter(servicePriceAdapter);
                servicePriceAdapter.setmRecyclerViewOnClickListenerHack(new RecyclerViewOnClickListenerHack() {
                    @Override
                    public void onClickListener(View view, int position) {
                        if (!produtos.get(position).isSelected()){
                            addService(orderId,services.get(position).getId(),itemId,position);
                        } else
                        {
                            removeService(orderId,services.get(position).getId(),position);
                        }
                    }
                });
        }
    }

    private void addProduct(int idQuote, int idQuotedProduct, int idProduct, int position)
    {
        if(addProductTask == null || addProductTask.getStatus() != AsyncTask.Status.RUNNING){
            addProductTask = new AddProductTask(idQuote,idQuotedProduct,idProduct,position);
            addProductTask.execute();
        }
    }

    private void removeProduct(int idQuote, int idQuotedProduct, int position){
        if(removeProductTask == null || removeProductTask.getStatus() != AsyncTask.Status.RUNNING){
            removeProductTask = new RemoveProductTask(idQuote,idQuotedProduct,position);
            removeProductTask.execute();
        }
    }

    private void addService(int idQuote, int idQuotedService, int idService, int position){
        if(addServiceTask == null || addServiceTask.getStatus() != AsyncTask.Status.RUNNING){
            addServiceTask = new AddServiceTask(idQuote,idQuotedService,idService,position);
            addServiceTask.execute();
        }
    }

    private void removeService(int idQuote, int idQuotedService, int position){
        if(removeServiceTask == null || removeServiceTask.getStatus() != AsyncTask.Status.RUNNING){
            removeServiceTask = new RemoveServiceTask(idQuote,idQuotedService,position);
            removeServiceTask.execute();
        }
    }



    private class ProductValueTask extends AsyncTask<Void,Void,Void>{
        ApiResponse<ProductValueList> apiResponse;
        private int orderId;
        private int itemId;

        public ProductValueTask(int orderId, int itemId) {
            this.orderId = orderId;
            this.itemId = itemId;
        }


        @Override
        protected Void doInBackground(Void... voids) {
            if(Looper.myLooper() == null)
                Looper.prepare();
            apiResponse = new QuotedProductPriceControl(OrderInsertValueActivity.this).getProductPrices(orderId,itemId);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            if(apiResponse.getStatusBoolean()){
                produtos = apiResponse.getResult().getList();
                setAdapter(typeProduct);
            }
        }
    }

    private class ServicePriceTask extends AsyncTask<Void,Void,Void>{

        private  ApiResponse<ServicePriceList> apiResponse;
        private int orderId;
        private int itemId;

        public ServicePriceTask(int orderId, int itemId) {
            this.orderId = orderId;
            this.itemId = itemId;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            if(Looper.myLooper() == null)
                Looper.prepare();
            apiResponse = new QuotedServicePriceControl(OrderInsertValueActivity.this).getServicePrice(orderId,itemId);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            if(apiResponse.getStatusBoolean()){
                services = apiResponse.getResult().getList();
                setAdapter(typeService);
            }
        }
    }

    private class AddProductTask extends AsyncTask<Void,Void,Void>{
        private ApiResponse<ProductValue> apiResponse;
        private int idQuote;
        private int idQuotedProduct;
        private int idProduct;
        private int position;

        public AddProductTask(int idQuote, int idQuotedProduct, int idProduct, int position) {
            this.idQuote = idQuote;
            this.idQuotedProduct = idQuotedProduct;
            this.idProduct = idProduct;
            this.position = position;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            if(Looper.myLooper() == null)
                Looper.prepare();
            apiResponse = new QuotedProductPriceControl(OrderInsertValueActivity.this).addProductPrices(idQuote,idQuotedProduct,idProduct);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            if(apiResponse.getStatusBoolean()){
                produtos.get(position).setSelected(true);
                productValueAdapter.notifyItemChanged(position);

            }
        }
    }


    private class RemoveProductTask extends AsyncTask<Void,Void,Void>{
        private ApiResponse<ProductValue> apiResponse;
        private int idQuote;
        private int idQuotedProduct;
        private int position;

        public RemoveProductTask(int idQuote, int idQuotedProduct, int position) {
            this.idQuote = idQuote;
            this.idQuotedProduct = idQuotedProduct;
            this.position = position;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            if(Looper.myLooper() == null)
                Looper.prepare();
            apiResponse = new QuotedProductPriceControl(OrderInsertValueActivity.this).removeProductPrice(idQuote,idQuotedProduct);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            if(apiResponse.getStatusBoolean()){
                produtos.get(position).setSelected(false);
                productValueAdapter.notifyItemChanged(position);
            }
        }
    }


    private class AddServiceTask extends AsyncTask<Void,Void,Void>{
        private ApiResponse<ProductValue> apiResponse;
        private int idQuote;
        private int idQuotedService;
        private int idService;
        private int position;

        public AddServiceTask(int idQuote, int idQuotedService, int idService, int position) {
            this.idQuote = idQuote;
            this.idQuotedService = idQuotedService;
            this.idService = idService;
            this.position = position;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            if(Looper.myLooper() == null)
                Looper.prepare();
            apiResponse = new QuotedServicePriceControl(OrderInsertValueActivity.this).addServicePrice(idQuote,idQuotedService,idService);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            if(apiResponse.getStatusBoolean()){
                produtos.get(position).setSelected(true);
                servicePriceAdapter.notifyItemChanged(position);
            }
        }
    }


    private class RemoveServiceTask extends AsyncTask<Void,Void,Void>{
        private ApiResponse<ProductValue> apiResponse;
        private int idQuote;
        private int idQuotedService;
        private int position;

        public RemoveServiceTask(int idQuote, int idQuotedService, int position) {
            this.idQuote = idQuote;
            this.idQuotedService = idQuotedService;
            this.position = position;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            if(Looper.myLooper() == null)
                Looper.prepare();
            apiResponse = new QuotedServicePriceControl(OrderInsertValueActivity.this).removeServicePrice(idQuote,idQuotedService);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            if(apiResponse.getStatusBoolean()){
                services.get(position).setSelected(false);
                servicePriceAdapter.notifyItemChanged(position);
            }
        }
    }








}
