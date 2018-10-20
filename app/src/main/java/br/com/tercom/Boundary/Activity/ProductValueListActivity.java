package br.com.tercom.Boundary.Activity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Looper;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import java.util.ArrayList;

import br.com.tercom.Adapter.ProductValueAdapter;
import br.com.tercom.Boundary.BoundaryUtil.AbstractAppCompatActivity;
import br.com.tercom.Control.ProductValueControl;
import br.com.tercom.Control.ProviderControl;
import br.com.tercom.Entity.ApiResponse;
import br.com.tercom.Entity.ProductValue;
import br.com.tercom.Entity.ProductValueList;
import br.com.tercom.Entity.Provider;
import br.com.tercom.Enum.EnumDialogOptions;
import br.com.tercom.R;
import br.com.tercom.Util.DialogConfirm;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ProductValueListActivity extends AbstractAppCompatActivity {

    @BindView(R.id.rv_productvalue)
    RecyclerView rv_productValues;

    @OnClick(R.id.fab_AddProductValue) void click()
    {
        //prox tela
    }

    private void createList(ProductValueList productValue){

        ProductValueAdapter productValueAdapter = new ProductValueAdapter(this, productValue.getList());
        GridLayoutManager gridLayoutManager =  new GridLayoutManager(this, 2);
        rv_productValues.setLayoutManager(gridLayoutManager);
        rv_productValues.setAdapter(productValueAdapter);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_value_list);
        ButterKnife.bind(this);
        createToolbar();

        ArrayList<ProductValue> productValues = new ArrayList<>();
        for(int i = 0; i < 11; i++)
        {
            ProductValue p = new ProductValue();
            p.setName("Nome: " + i);
            productValues.add(p);
        }

        GetAllProductValueTask getAll = new GetAllProductValueTask(7);
        getAll.execute();
        ProductValueAdapter recyclerAdapter = new ProductValueAdapter(this, productValues);
        LinearLayoutManager llmanager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rv_productValues.setLayoutManager(llmanager);
        rv_productValues.setAdapter(recyclerAdapter);
    }

    private void createListProductValues(ProductValueList list)
    {

    }

    private class GetAllProductValueTask extends AsyncTask<Void,Void,Void> {

        private ApiResponse<ProductValueList> apiResponse;
        private int idProduct;

        public GetAllProductValueTask(int idProduct){

            this.idProduct = idProduct;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            if(Looper.myLooper() == null)
                Looper.prepare();
            ProductValueControl pValueControl= new ProductValueControl(ProductValueListActivity.this);
            apiResponse = pValueControl.getAll(idProduct);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            if(apiResponse.getStatusBoolean()){
                createListProductValues(apiResponse.getResult());
            }
        }
    }
}
