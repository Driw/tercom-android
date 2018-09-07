package br.com.tercom.Boundary.Activity;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.KeyEvent;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

import br.com.tercom.Boundary.BoundaryUtil.AbstractAppCompatActivity;
import br.com.tercom.Entity.ApiResponse;
import br.com.tercom.Entity.Product;
import br.com.tercom.Entity.ProductCategory;
import br.com.tercom.Entity.ProductFamily;
import br.com.tercom.Entity.ProductGroup;
import br.com.tercom.Entity.ProductSector;
import br.com.tercom.Entity.ProductSubGroup;
import br.com.tercom.Entity.ProductUnit;
import br.com.tercom.Interface.RecyclerViewOnClickListenerHack;
import br.com.tercom.R;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnEditorAction;

public class ProductCategoryAddActivity extends AbstractAppCompatActivity {

    private int step = 2 ;
    private UnitTask unitTask;
    private FamilyTask familyTask;
    private GroupTask groupTask;
    private SubGroupTask subGroupTask;
    private SectorTask sectorTask;
    private Product product;

    @BindView(R.id.txtType)
    TextView txtType;
    @BindView(R.id.txtSearch)
    EditText txtSearch;
    @BindView(R.id.rv_category)
    RecyclerView rv_category;
    @BindView(R.id.btn_next)
    Button btnNext;

    EditText.OnEditorActionListener ON_EDITOR_CLICK = new EditText.OnEditorActionListener() {
        @Override
        public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
            if ((keyEvent != null && (keyEvent.getKeyCode() == KeyEvent.KEYCODE_ENTER)) || (i == EditorInfo.IME_ACTION_DONE)) {
                executeStep(step);
            }
            return false;
        }
    };

    @OnClick(R.id.btn_next) void next(){
        switch (step){
            case 1:
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_category_add);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        createToolbar();
        ButterKnife.bind(this);
    }


    private void executeStep(int step) {
        switch (step){
            case 1:
        }

    }

    private void initUnitTask(){
        if(unitTask == null || unitTask.getStatus() != AsyncTask.Status.RUNNING){
            unitTask = new UnitTask();
            unitTask.execute();
        }
    }

    private void initFamilyTask(){
        if(familyTask == null || familyTask.getStatus() != AsyncTask.Status.RUNNING){
            familyTask = new FamilyTask();
            familyTask.execute();
        }
    }

    private void initGroupTask(){
        if(groupTask == null || groupTask.getStatus() != AsyncTask.Status.RUNNING){
            groupTask = new GroupTask();
            groupTask.execute();
        }
    }

    private void initSubGrouptTask(){
        if(subGroupTask == null || subGroupTask.getStatus() != AsyncTask.Status.RUNNING){
            subGroupTask = new SubGroupTask();
            subGroupTask.execute();
        }
    }

    private void initSectorTask(){
        if(sectorTask == null || sectorTask.getStatus() != AsyncTask.Status.RUNNING){
            sectorTask = new SectorTask();
            sectorTask.execute();
        }
    }

    private void createList(ArrayList<ProductCategory> categoryList, RecyclerViewOnClickListenerHack click){

    }

    private class UnitTask extends AsyncTask<Void,Void,Void>{

        private ApiResponse<ProductUnit> apiResponse;

        @Override
        protected Void doInBackground(Void... voids) {
            return null;
        }
    }
    private class FamilyTask extends AsyncTask<Void,Void,Void>{

        private ApiResponse<ProductFamily> apiResponse;

        @Override
        protected Void doInBackground(Void... voids) {
            return null;
        }
    }
    private class GroupTask extends AsyncTask<Void,Void,Void>{

        private ApiResponse<ProductGroup> apiResponse;

        @Override
        protected Void doInBackground(Void... voids) {
            return null;
        }
    }
    private class SubGroupTask extends AsyncTask<Void,Void,Void>{

        private ApiResponse<ProductSubGroup> apiResponse;

        @Override
        protected Void doInBackground(Void... voids) {
            return null;
        }
    }
    private class SectorTask extends AsyncTask<Void,Void,Void>{

        private ApiResponse<ProductSector> apiResponse;

        @Override
        protected Void doInBackground(Void... voids) {
            return null;
        }
    }

}
