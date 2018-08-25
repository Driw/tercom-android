package br.com.tercom.Boundary.Activity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Looper;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import br.com.tercom.Boundary.BoundaryUtil.AbstractAppCompatActivity;
import br.com.tercom.Control.ProviderControl;
import br.com.tercom.Entity.ApiResponse;
import br.com.tercom.Entity.GenericEntity;
import br.com.tercom.Entity.Provider;
import br.com.tercom.Entity.ProviderList;
import br.com.tercom.Enum.EnumDialogOptions;
import br.com.tercom.Enum.EnumFont;
import br.com.tercom.R;
import br.com.tercom.Util.CustomPair;
import br.com.tercom.Util.DialogConfirm;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static br.com.tercom.Util.CustomTypeFace.overrideFonts;
import static br.com.tercom.Util.TextUtil.emptyValidator;
import static br.com.tercom.Util.Util.toast;

public class AddProviderActivity extends AbstractAppCompatActivity {

    private AddProviderTask addProviderTask;

    //BUTTONS
    @BindView(R.id.btn_submit)
    Button btnSubmit;

    @BindView(R.id.txtCompanyName)
    EditText txtCompanyName;
    @BindView(R.id.txtFantasyName)
    EditText txtFantasyName;
    @BindView(R.id.txtCNPJ)
    EditText txtCNPJ;
    @BindView(R.id.txtSite)
    EditText txtSite;

    @OnClick(R.id.btn_submit) void next(){
        CustomPair<String> result = verifyData(txtCompanyName.getText().toString(),txtFantasyName.getText().toString(),txtCNPJ.getText().toString(),txtSite.getText().toString());
        if(result.first){
            initAddTask(txtCompanyName.getText().toString(),txtFantasyName.getText().toString(),txtCNPJ.getText().toString(),txtSite.getText().toString());
        }else{
            toast(AddProviderActivity.this,result.second);
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_provider);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        ButterKnife.bind(this);
        overrideFonts(this,getWindow().getDecorView().getRootView(), EnumFont.FONT_ROBOTO_REGULAR);
        CreateToolbarWithNavigation(1);
    }

    private CustomPair<String> verifyData(String companyName, String fantasyName, String cnpj, String site){

        if(!emptyValidator(companyName))
            return new CustomPair<>(false,"Razão Social inválida");

        if(!emptyValidator(fantasyName))
            return new CustomPair<>(false,"Nome Fantasia Inválido");

        if(!emptyValidator(cnpj))
            return new CustomPair<>(false,"CNPJ Inválido");

        if(!emptyValidator(site))
            return new CustomPair<>(false,"Site inválido");

        return new CustomPair<>(true,"Ok");
    }

    private void initAddTask(String companyName, String fantasyName, String cnpj, String site){
        if(addProviderTask == null || addProviderTask.getStatus() != AsyncTask.Status.RUNNING){
            addProviderTask = new AddProviderTask(companyName,fantasyName,cnpj,site);
            addProviderTask.execute();
        }
    }

    private class AddProviderTask extends AsyncTask<Void,Void,Void>{

        private ApiResponse<Provider> apiResponse;
        private String companyName;
        private String fantasyName;
        private String cnpj;
        private String site;

        public AddProviderTask(String companyName, String FantasyName, String cnpj, String site){

            this.companyName = companyName;
            fantasyName = FantasyName;
            this.cnpj = cnpj;
            this.site = site;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            if(Looper.myLooper() == null)
                Looper.prepare();
            ProviderControl providerControl = new ProviderControl(AddProviderActivity.this);
            apiResponse = providerControl.callJsonAddProvider(companyName,fantasyName,cnpj,site);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            DialogConfirm dialogConfirm = new DialogConfirm(AddProviderActivity.this);
            if(apiResponse.getStatusBoolean()){
                dialogConfirm.init(EnumDialogOptions.CONFIRM,apiResponse.getMessage(),"Adicionar Contatos");
                dialogConfirm.onClickChanges(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        createIntentAbs(AddProviderContactActivity.class);
                    }
                });
            }else{
                dialogConfirm.init(EnumDialogOptions.FAIL,apiResponse.getMessage());
            }
        }
    }



}