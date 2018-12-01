package br.com.tercom.Boundary.Activity;

import android.os.AsyncTask;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import br.com.tercom.Boundary.BoundaryUtil.AbstractAppCompatActivity;
import br.com.tercom.Control.ServicePriceControl;
import br.com.tercom.Entity.ApiResponse;
import br.com.tercom.Entity.Provider;
import br.com.tercom.Entity.ServicePrice;
import br.com.tercom.Enum.EnumDialogOptions;
import br.com.tercom.R;
import br.com.tercom.Util.CustomPair;
import br.com.tercom.Util.DialogConfirm;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static br.com.tercom.Util.PriceMask.unmaskPrice;
import static br.com.tercom.Util.TextUtil.emptyValidator;
import static br.com.tercom.Util.Util.toast;

public class ServicePriceActivity extends AbstractAppCompatActivity {

    private Provider seletectedProvider;
    private AddPriceTask addPriceTask;
    private int idService;

    @BindView(R.id.editName)
    EditText editName;
    @BindView(R.id.editPrice)
    EditText editPrice;
    @BindView(R.id.editObs)
    EditText editObs;
    @BindView(R.id.btnAdd)
    Button btnAdd;
    @BindView(R.id.btnProvider)
    Button btnProvider;

    @OnClick(R.id.btnAdd) void add(){
        CustomPair<String> result = verifyData(editName.getText().toString(),editPrice.getText().toString(),editObs.getText().toString(),seletectedProvider.getId());
        if(result.first){
            initAddTask(editName.getText().toString(), (float) unmaskPrice(editPrice.getText().toString()),editObs.getText().toString(),seletectedProvider.getId());
        }else{
            toast(ServicePriceActivity.this,result.second);
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_price);
        ButterKnife.bind(this);
        createToolbar();
    }


    private CustomPair<String> verifyData(String name, String price, String additionalDescription, int id) {

        if(!emptyValidator(name))
            return new CustomPair<>(false,"Nome inválido");

        if(!emptyValidator(price))
            return new CustomPair<>(false,"Preço Inválido");

        if(id == 0)
            return new CustomPair<>(false,"Fornecedor inválido");

        return new CustomPair<>(true,"Ok");
    }

    private void initAddTask(String name, float price, String additionalDescription, int id){
        if(addPriceTask == null || addPriceTask.getStatus() != AsyncTask.Status.RUNNING){
            addPriceTask =  new AddPriceTask(idService,name,additionalDescription,price,id);
            addPriceTask.execute();
        }
    }



    private class AddPriceTask extends AsyncTask<Void,Void,Void>{

        private ApiResponse<ServicePrice> apiResponse;
        int idService;
        private String name;
        private String observations;
        private float price;
        private int idProvider;


        public AddPriceTask(int idService, String name, String observations, float price, int idProvider) {
            this.name = name;
            this.observations = observations;
            this.price = price;
            this.idProvider = idProvider;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            if (Looper.myLooper() == null)
                Looper.prepare();
            ServicePriceControl servicePriceControl = new ServicePriceControl(ServicePriceActivity.this);
            apiResponse = servicePriceControl.add(idService,idProvider,price,name,observations);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            DialogConfirm dialogConfirm = new DialogConfirm(ServicePriceActivity.this);
            if(apiResponse.getStatusBoolean()){
                dialogConfirm.init(EnumDialogOptions.CONFIRM,apiResponse.getMessage());
                dialogConfirm.onClickChanges(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        finish();
                    }
                });
            }else{
                dialogConfirm.init(EnumDialogOptions.FAIL,apiResponse.getMessage());
            }
        }
    }



}
