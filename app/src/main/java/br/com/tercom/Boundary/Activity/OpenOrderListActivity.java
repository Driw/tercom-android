package br.com.tercom.Boundary.Activity;

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

import br.com.tercom.Adapter.OrderListAllAdapter;
import br.com.tercom.Boundary.BoundaryUtil.AbstractAppCompatActivity;
import br.com.tercom.Control.OrderQuoteControl;
import br.com.tercom.Control.OrderRequestControl;
import br.com.tercom.Entity.ApiResponse;
import br.com.tercom.Entity.OrderItemProductList;
import br.com.tercom.Entity.OrderRequest;
import br.com.tercom.Entity.OrderRequestList;
import br.com.tercom.Enum.EnumDialogOptions;
import br.com.tercom.Interface.RecyclerViewOnClickListenerHack;
import br.com.tercom.R;
import br.com.tercom.Util.DialogConfirm;
import br.com.tercom.Util.DialogLoading;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class OpenOrderListActivity extends AbstractAppCompatActivity {

    private OrderRequestList list;
    private ArrayList<OrderRequest> adapterList;
    private int selectedType;
    private static final int typeAll = 1;
    private static final int typeOpen = 2;
    private static final int typeInitialized = 3;
    private GetOrdersTask getOrders;
    private QuoteTask quoteTask;

    @BindView(R.id.rv_OpenOrderDetail)
    RecyclerView rv_OpenOrderDetail;
    @BindView(R.id.btnOrderListAll)
    Button btnOrderListAll;
    @BindView(R.id.btnOrderListOpen)
    Button btnOrderListOpen;
    @BindView(R.id.btnOrderListInicialized)
    Button btnOrderListInicialized;

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

    private void btnBarmanager(){
        if (btnOrderListAll.isSelected()){
            btnOrderListAll.setBackgroundColor(getResources().getColor(R.color.colorAccent));
            btnOrderListAll.setTextColor((getResources().getColor(R.color.colorPrimary)));
            btnOrderListAll.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, 3));
            btnOrderListOpen.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, 1));
            btnOrderListInicialized.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, 1));
        } else {
            btnOrderListAll.setBackgroundColor(getResources().getColor(R.color.cardview_shadow_end_color));
            btnOrderListAll.setTextColor((getResources().getColor(R.color.colorAccent)));
        }
        if (btnOrderListOpen.isSelected()){
            btnOrderListOpen.setBackgroundColor(getResources().getColor(R.color.colorAccent));
            btnOrderListOpen.setTextColor((getResources().getColor(R.color.colorPrimary)));
            btnOrderListOpen.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, 3));
            btnOrderListAll.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, 1));
            btnOrderListInicialized.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, 1));
        } else {
            btnOrderListOpen.setBackgroundColor(getResources().getColor(R.color.cardview_shadow_end_color));
            btnOrderListOpen.setTextColor((getResources().getColor(R.color.colorAccent)));
        }
        if (btnOrderListInicialized.isSelected()){
            btnOrderListInicialized.setBackgroundColor(getResources().getColor(R.color.colorAccent));
            btnOrderListInicialized.setTextColor((getResources().getColor(R.color.colorPrimary)));
            btnOrderListInicialized.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, 3));
            btnOrderListOpen.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, 1));
            btnOrderListAll.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, 1));
        } else {
            btnOrderListInicialized.setBackgroundColor(getResources().getColor(R.color.cardview_shadow_end_color));
            btnOrderListInicialized.setTextColor((getResources().getColor(R.color.colorAccent)));
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
        }
        OrderListAllAdapter orderListAllAdapter = new OrderListAllAdapter(this, adapterList);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rv_OpenOrderDetail.setLayoutManager(layoutManager);
        rv_OpenOrderDetail.setAdapter(orderListAllAdapter);
        orderListAllAdapter.setmRecyclerViewOnClickListenerHack(new RecyclerViewOnClickListenerHack() {
            @Override
            public void onClickListener(View view, int position) {
                initOrderTask(adapterList.get(position));
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

        private ApiResponse<OrderItemProductList> apiResponse;
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
               startActivity(intent);
           }
        }
    }

    private class GetOrdersTask extends AsyncTask<Void,Void,Void>{
        private ApiResponse<OrderRequestList> apiResponse;
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
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            dialogLoading.dismissD();
            if(apiResponse.getStatusBoolean()){
               list = apiResponse.getResult();
               setAdapter(typeAll);
            }else{
                DialogConfirm dialogConfirm = new DialogConfirm(OpenOrderListActivity.this);
                dialogConfirm.init(EnumDialogOptions.FAIL,apiResponse.getMessage());
            }
        }
    }

}
