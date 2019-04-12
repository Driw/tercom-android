package br.com.tercomfuncionario.Boundary.Activity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Looper;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import com.google.gson.Gson;

import java.util.ArrayList;

import br.com.tercomfuncionario.Adapter.OrderListAllAdapter;
import br.com.tercomfuncionario.Boundary.BoundaryUtil.AbstractAppCompatActivity;
import br.com.tercomfuncionario.Control.OrderQuoteControl;
import br.com.tercomfuncionario.Control.OrderRequestControl;
import br.com.tercomfuncionario.Entity.ApiResponse;
import br.com.tercomfuncionario.Entity.OrderItemProductList;
import br.com.tercomfuncionario.Entity.OrderQuote;
import br.com.tercomfuncionario.Entity.OrderRequest;
import br.com.tercomfuncionario.Entity.OrderRequestList;
import br.com.tercomfuncionario.Enum.EnumDialogOptions;
import br.com.tercomfuncionario.Interface.RecyclerViewOnClickListenerHack;
import br.com.tercomfuncionario.R;
import br.com.tercomfuncionario.Util.DialogConfirm;
import br.com.tercomfuncionario.Util.DialogLoading;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static br.com.tercomfuncionario.Application.AppTercom.USER_STATIC;

public class OpenOrderListActivity extends AbstractAppCompatActivity {

    private OrderRequestList list;
    private ArrayList<OrderRequest> adapterList;
    private int selectedType;
    private static final int typeAll = 1;
    private static final int typeOpen = 2;
    private static final int typeInitialized = 3;
    private static final int typeQuoted = 4;
    private GetOrdersTask getOrders;
    private QuoteTask quoteTask;
    private GetQuoteTask getQuoteTask;

    @BindView(R.id.rv_OpenOrderDetail)
    RecyclerView rv_OpenOrderDetail;
    @BindView(R.id.btnOrderListAll)
    Button btnOrderListAll;
    @BindView(R.id.btnOrderListOpen)
    Button btnOrderListOpen;
    @BindView(R.id.btnOrderListInicialized)
    Button btnOrderListInicialized;
    @BindView(R.id.btnOrderListQuoted)
    Button btnOrderListQuoted;

    @OnClick(R.id.btnOrderListAll) void displayAllOrders(){
        setSelected(typeAll);
        btnBarmanager();
        setAdapter(typeAll);
    }
    @OnClick(R.id.btnOrderListOpen) void displayOpenOrder(){
        setSelected(typeOpen);
        btnBarmanager();
        setAdapter(typeOpen);
    }
    @OnClick(R.id.btnOrderListInicialized) void displayInicializedOrder(){
        setSelected(typeInitialized);
        btnBarmanager();
        setAdapter(typeInitialized);
    }
    @OnClick(R.id.btnOrderListQuoted) void displayQuotedOrders(){
        setSelected(typeQuoted);
        btnBarmanager();
        setAdapter(typeQuoted);
    }

    private void btnBarmanager() {
        if (btnOrderListAll.isSelected()) {
            btnOrderListAll.setBackgroundColor(getResources().getColor(R.color.colorAccent));
            btnOrderListAll.setTextColor((getResources().getColor(R.color.colorPrimary)));
            btnOrderListAll.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, 3));
            btnOrderListOpen.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, 1));
            btnOrderListInicialized.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, 1));
            btnOrderListQuoted.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, 1));
        } else {
            btnOrderListAll.setBackgroundColor(getResources().getColor(R.color.cardview_shadow_end_color));
            btnOrderListAll.setTextColor((getResources().getColor(R.color.colorAccent)));
        }
        if (btnOrderListOpen.isSelected()) {
            btnOrderListOpen.setBackgroundColor(getResources().getColor(R.color.colorAccent));
            btnOrderListOpen.setTextColor((getResources().getColor(R.color.colorPrimary)));
            btnOrderListOpen.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, 3));
            btnOrderListAll.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, 1));
            btnOrderListInicialized.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, 1));
            btnOrderListQuoted.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, 1));
        } else {
            btnOrderListOpen.setBackgroundColor(getResources().getColor(R.color.cardview_shadow_end_color));
            btnOrderListOpen.setTextColor((getResources().getColor(R.color.colorAccent)));
        }
        if (btnOrderListInicialized.isSelected()) {
            btnOrderListInicialized.setBackgroundColor(getResources().getColor(R.color.colorAccent));
            btnOrderListInicialized.setTextColor((getResources().getColor(R.color.colorPrimary)));
            btnOrderListInicialized.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, 3));
            btnOrderListOpen.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, 1));
            btnOrderListAll.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, 1));
            btnOrderListQuoted.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, 1));
        } else {
            btnOrderListInicialized.setBackgroundColor(getResources().getColor(R.color.cardview_shadow_end_color));
            btnOrderListInicialized.setTextColor((getResources().getColor(R.color.colorAccent)));
        }
        if (btnOrderListQuoted.isSelected()) {
            btnOrderListQuoted.setBackgroundColor(getResources().getColor(R.color.colorAccent));
            btnOrderListQuoted.setTextColor((getResources().getColor(R.color.colorPrimary)));
            btnOrderListQuoted.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, 3));
            btnOrderListOpen.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, 1));
            btnOrderListAll.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, 1));
            btnOrderListInicialized.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, 1));
        } else {
            btnOrderListQuoted.setBackgroundColor(getResources().getColor(R.color.cardview_shadow_end_color));
            btnOrderListQuoted.setTextColor((getResources().getColor(R.color.colorAccent)));
        }
    }

    private void setAdapter(int type){
        if(rv_OpenOrderDetail.getAdapter() != null){
            rv_OpenOrderDetail.setAdapter(null);
        }
        switch (type){
            case typeAll:
                adapterList = new ArrayList<>();
                for(OrderRequest request : list.getList()){
                        adapterList.add(request);
                }
                break;
            case typeOpen:
                adapterList = new ArrayList<>();
                for(OrderRequest request : list.getList()){
                    if(request.getStatus() == 3){
                        adapterList.add(request);
                    }
                }
                break;
            case typeInitialized:
                adapterList = new ArrayList<>();
                for(OrderRequest request : list.getList()){
                    if(request.getStatus() == 4){
                        adapterList.add(request);
                    }
                }
                break;
            case typeQuoted:
                adapterList = new ArrayList<>();
                for(OrderRequest request : list.getList()){
                    if(request.getStatus() == 5){
                        adapterList.add(request);
                    }
                }
                break;
        }
        OrderListAllAdapter orderListAllAdapter = new OrderListAllAdapter(this, adapterList);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rv_OpenOrderDetail.setLayoutManager(layoutManager);
        rv_OpenOrderDetail.setAdapter(orderListAllAdapter);
        orderListAllAdapter.setmRecyclerViewOnClickListenerHack(new RecyclerViewOnClickListenerHack() {
            @Override
            public void onClickListener(View view, int position) {
                if(adapterList.get(position).getStatus() == OrderRequest.ORS_QUOTING &&
                        adapterList.get(position).getTercomEmployee().getId() == USER_STATIC.getTercomEmployee().getId()){
                    initGetOrderTask(adapterList.get(position));
                }else {
                    initOrderTask(adapterList.get(position));
                }
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open_order_detail);
        ButterKnife.bind(this);
        //createToolbar();
        initTask();
    }

    private void initOrderTask(OrderRequest request){
       if(quoteTask == null || quoteTask.getStatus() != AsyncTask.Status.RUNNING){
           quoteTask = new QuoteTask(request);
           quoteTask.execute();
       }

    }
    private void initGetOrderTask(OrderRequest request){
       if(getQuoteTask == null || getQuoteTask.getStatus() != AsyncTask.Status.RUNNING){
           getQuoteTask = new GetQuoteTask(request);
           getQuoteTask.execute();
       }

    }


    private void initTask(){
        if(getOrders == null || getOrders.getStatus() != AsyncTask.Status.RUNNING){
            getOrders = new GetOrdersTask();
            getOrders.execute();
        }
    }

    private void setSelected(int type){
        btnOrderListAll.setSelected(type == typeAll);
        btnOrderListInicialized.setSelected(type == typeInitialized);
        btnOrderListOpen.setSelected(type == typeOpen);
        selectedType = type;
    }

    private class QuoteTask extends AsyncTask<Void,Void,Void>{

        private ApiResponse<OrderQuote> apiResponse;
        private OrderRequest request;
        private DialogLoading dialogLoading;

        public QuoteTask(OrderRequest request) {
            this.request = request;
        }

        @Override
        protected void onPreExecute() {
            dialogLoading = new DialogLoading(OpenOrderListActivity.this);
            dialogLoading.init();
        }

        @Override
        protected Void doInBackground(Void... voids) {
              if(Looper.myLooper() == null)
                  Looper.prepare();
            OrderQuoteControl orderQuoteControl = new OrderQuoteControl(OpenOrderListActivity.this);
            apiResponse = orderQuoteControl.quote(request.getId());
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
           dialogLoading.dismissD();
           if(apiResponse.getStatusBoolean()){
               Intent intent = new Intent();
               intent.setClass(OpenOrderListActivity.this,InicializedOrderListActivity.class);
               intent.putExtra("order",new Gson().toJson(request));
               intent.putExtra("orderQuote",new Gson().toJson(apiResponse.getResult()));
               startActivity(intent);
           }
        }
    }


    private class GetQuoteTask extends AsyncTask<Void,Void,Void>{

        private ApiResponse<OrderQuote> apiResponse;
        private OrderRequest request;
        private DialogLoading dialogLoading;

        public GetQuoteTask(OrderRequest request) {
            this.request = request;
        }

        @Override
        protected void onPreExecute() {
            dialogLoading = new DialogLoading(OpenOrderListActivity.this);
            dialogLoading.init();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            if(Looper.myLooper() == null)
                Looper.prepare();
            OrderQuoteControl orderQuoteControl = new OrderQuoteControl(OpenOrderListActivity.this);
            apiResponse = orderQuoteControl.getQuote(request.getId());
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            dialogLoading.dismissD();
            if(apiResponse.getStatusBoolean()){
                Intent intent = new Intent();
                intent.setClass(OpenOrderListActivity.this,InicializedOrderListActivity.class);
                intent.putExtra("order",new Gson().toJson(request));
                intent.putExtra("orderQuote",new Gson().toJson(apiResponse.getResult()));
                startActivity(intent);
            }
        }
    }


    private class GetOrdersTask extends AsyncTask<Void,Void,Void>{
        private ApiResponse<OrderRequestList> apiResponse;
        private ApiResponse<OrderRequestList> apiResponseStarted;
        private DialogLoading dialogLoading;

        @Override
        protected void onPreExecute() {
            dialogLoading = new DialogLoading(OpenOrderListActivity.this);
            dialogLoading.init();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            if(Looper.myLooper() == null)
                Looper.prepare();
            apiResponse = new OrderRequestControl(OpenOrderListActivity.this).getAll(OrderRequestControl.FILA);
            apiResponseStarted = new OrderRequestControl(OpenOrderListActivity.this).getAll(OrderRequestControl.EMCOTACAO);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            dialogLoading.dismissD();
            if(apiResponse.getStatusBoolean()){
               list = new OrderRequestList();
               list.setList(new ArrayList<OrderRequest>());
               list.getList().addAll(apiResponse.getResult().getList());
               list.getList().addAll(apiResponseStarted.getResult().getList());
               setAdapter(typeAll);
            }else{
                DialogConfirm dialogConfirm = new DialogConfirm(OpenOrderListActivity.this);
                dialogConfirm.init(EnumDialogOptions.FAIL,apiResponse.getMessage());
            }
        }
    }

}
