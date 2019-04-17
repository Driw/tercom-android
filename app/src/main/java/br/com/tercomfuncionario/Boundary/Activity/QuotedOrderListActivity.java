package br.com.tercomfuncionario.Boundary.Activity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.google.gson.Gson;

import java.util.ArrayList;

import br.com.tercomfuncionario.Adapter.QuotedOrderListAdapter;
import br.com.tercomfuncionario.Boundary.BoundaryUtil.AbstractAppCompatActivity;
import br.com.tercomfuncionario.Control.OrderAcceptanceProductControl;
import br.com.tercomfuncionario.Control.OrderAcceptanceServiceControl;
import br.com.tercomfuncionario.Control.OrderQuoteControl;
import br.com.tercomfuncionario.Entity.ApiResponse;
import br.com.tercomfuncionario.Entity.OrderAcceptanceProductList;
import br.com.tercomfuncionario.Entity.OrderAcceptanceServiceList;
import br.com.tercomfuncionario.Entity.OrderQuote;
import br.com.tercomfuncionario.Interface.iQuotedOrderItem;
import br.com.tercomfuncionario.R;
import br.com.tercomfuncionario.Util.DialogLoading;
import butterknife.BindView;
import butterknife.ButterKnife;

public class QuotedOrderListActivity extends AbstractAppCompatActivity {

    private ArrayList<iQuotedOrderItem> quotedOrderItems;
    private getAllQuotedOrderItemsTask getAllQuotedOrderItemsTask;
    private OrderQuote orderQuote;

    @BindView(R.id.rv_QuotedOrdersList)
    RecyclerView rv_QuotedOrdersList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quoted_orders);
        ButterKnife.bind(this);
        createToolbar();
    }

    private void initGetAllQuotedOrderItemTask() {
        if (getAllQuotedOrderItemsTask == null && getAllQuotedOrderItemsTask.getStatus() != AsyncTask.Status.RUNNING){
            getAllQuotedOrderItemsTask = new getAllQuotedOrderItemsTask(getIntent().getExtras().getInt("id"));
            getAllQuotedOrderItemsTask.execute();
        }
    }

    private void createQuotedOrderList() {
        QuotedOrderListAdapter categoryAdapter = new QuotedOrderListAdapter(this, quotedOrderItems);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rv_QuotedOrdersList.setLayoutManager(layoutManager);
        rv_QuotedOrdersList.setAdapter(categoryAdapter);
    }

    public class getAllQuotedOrderItemsTask extends AsyncTask<Void, Void, Void> {

        private ApiResponse<OrderAcceptanceProductList> apiResponseProduct;
        private ApiResponse<OrderAcceptanceServiceList> apiResponseService;
        private int id;

        public getAllQuotedOrderItemsTask(int id) {
            quotedOrderItems = new ArrayList<>();
            this.id = id;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            if (Looper.myLooper() == null) {
                Looper.prepare();
            }
            apiResponseProduct = new OrderAcceptanceProductControl(QuotedOrderListActivity.this).getAll(id);
            apiResponseService = new OrderAcceptanceServiceControl(QuotedOrderListActivity.this).getAll(id);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            if (apiResponseProduct.getStatusBoolean()) {
                quotedOrderItems.addAll(apiResponseProduct.getResult().getList());
            }
            if (apiResponseService.getStatusBoolean()){
                quotedOrderItems.addAll(apiResponseService.getResult().getList());
            }
            if (quotedOrderItems.size() > 0){
                createQuotedOrderList();
            }
        }
    }

}
