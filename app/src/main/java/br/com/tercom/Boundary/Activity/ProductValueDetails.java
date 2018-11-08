package br.com.tercom.Boundary.Activity;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Looper;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Locale;

import br.com.tercom.Adapter.ProductValueItensAdapter;
import br.com.tercom.Boundary.BoundaryUtil.AbstractAppCompatActivity;
import br.com.tercom.Control.ManufactureControl;
import br.com.tercom.Entity.ApiResponse;
import br.com.tercom.Entity.ManufactureList;
import br.com.tercom.Entity.ProductValueSend;
import br.com.tercom.Interface.IProductValueItem;
import br.com.tercom.Interface.RecyclerViewOnClickListenerHack;
import br.com.tercom.R;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static br.com.tercom.Util.Util.toast;

public class ProductValueDetails extends AbstractAppCompatActivity {
    /**
     * Todas as classes de referencia devem implementar a interface IProductvalueItem para ser usada no adapter.
     * Tem os exemplos de cada coisa que deve ser feita: clique, dialog (genérico para qualquer tipo, só passar a referencia), método de search, task e inicialização da task.
     * Devem ser feitos isso para cada um dos tipos e depois um para o atualizar nessa classe e adicionar na outra.
     */

    private static final int REFERENCE_PROVIDER = 1;
    private static final int REFERENCE_MANUFACTURER = 2;
    private static final int REFERENCE_PACKAGE = 3;
    private static final int REFERENCE_TYPE = 4;

    private ManufactureTask manufactureTask;
    private ProductValueSend productValue;


    @BindView(R.id.txtName)
    EditText txtName;
    @BindView(R.id.txtAmount)
    EditText txtAmount;
    @BindView(R.id.txtValue)
    EditText txtValue;
    @BindView(R.id.txtProvider)
    TextView txtProvider;
    @BindView(R.id.txtManufacturer)
    TextView txtManufacturer;
    @BindView(R.id.txtPackage)
    TextView txtPackage;
    @BindView(R.id.txtType)
    TextView txtType;
    @BindView(R.id.btn_delete)
    Button btn_delete;
    @BindView(R.id.btn_function)
    Button btn_function;
    private RecyclerView rvSearch;
    private EditText editSearch;


    @OnClick(R.id.txtManufacturer) void onClick(){
        initDialog(REFERENCE_MANUFACTURER);
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_value_generic);
        ButterKnife.bind(this);
        productValue = new ProductValueSend();

    }


    private void initDialog(final int typeReference) {
        Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.setContentView(R.layout.dialog_search_info_price);
        rvSearch = dialog.findViewById(R.id.rv_search);
        editSearch = dialog.findViewById(R.id.editSearch);
        editSearch.setImeOptions(EditorInfo.IME_ACTION_SEARCH);
        editSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if(i == EditorInfo.IME_ACTION_SEARCH){
                    if(!TextUtils.isEmpty(editSearch.getText().toString()))
                        search(typeReference, editSearch.getText().toString());

                    return true;
                }
                return false;
            }
        });
        dialog.show();

    }

    /**
     * para cada uma das tasks deve ter um desse para inicializa-la
     * @param  value receberá o valor que o usuário colocar no editSearch
     */

    private void initManufacturerTask(String value){
        if(manufactureTask == null || manufactureTask.getStatus() != AsyncTask.Status.RUNNING){
            manufactureTask = new ManufactureTask(value);
            manufactureTask.execute();
        }

    }

    private void setReference(int reference, IProductValueItem value){
        switch (reference){
            case REFERENCE_PROVIDER:
                txtProvider.setText(getStringFormated("Fornecedor",value.getName()));
                break;
            case REFERENCE_MANUFACTURER:
                txtManufacturer.setText(getStringFormated("Fabricante",value.getName()));
                productValue.setIdManufacture(value.getId());
                break;
            case REFERENCE_PACKAGE:
                txtPackage.setText(getStringFormated("Embalagem",value.getName()));
                break;
            case REFERENCE_TYPE:
                txtType.setText(getStringFormated("Tipo",value.getName()));
                break;
        }

    }

    private String getStringFormated(String type, String value){
        return String.format(Locale.getDefault(),"%s: %s");
    }



    /**
     * Baseado na referencia, ele direciona para a task necessária;
     * @param reference tipo da chamada, passará uma das constantes dessa classe.
     */
    private void search(int reference, String value) {
                // TODO(aqui devem ir as chamadas de async task reference a cada uma das chamadas feitas no dialog, assim ela retornará a lista que irá no adapter.)
        switch (reference){
            case REFERENCE_PROVIDER:
                break;
            case REFERENCE_MANUFACTURER:
                initManufacturerTask(value);
                break;
            case REFERENCE_PACKAGE:
                break;
            case REFERENCE_TYPE:
                break;
        }
    }

    /**
     * Cria a lista no dialog que quando clicada, irá adicionar o valor no product value e na view.
     * @param itens itens que preencherão a recyclerView
     * @param reference qual objeto deverá ser setado.
     */
    private void createList(final int reference, final ArrayList<? extends IProductValueItem> itens){
        rvSearch.setAdapter(null);
        ProductValueItensAdapter productValueItensAdapter = new ProductValueItensAdapter(ProductValueDetails.this,itens);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false);
        productValueItensAdapter.setmRecyclerViewOnClickListenerHack(new RecyclerViewOnClickListenerHack() {
            @Override
            public void onClickListener(View view, int position) {
                setReference(reference, itens.get(position));

            }
        });
        rvSearch.setLayoutManager(layoutManager);
        rvSearch.setAdapter(productValueItensAdapter);

    }

    /**
     * Task que fará download da lista do item desejado e setará no dialog através do método createList()
     */
    private class ManufactureTask extends AsyncTask<Void,Void,Void>{
        private ApiResponse<ManufactureList> apiResponse;
        private String value;

        public ManufactureTask(String value) {
            this.value = value;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            if(Looper.myLooper() == null)
                Looper.prepare();
            ManufactureControl manufactureControl = new ManufactureControl(ProductValueDetails.this);
            apiResponse = manufactureControl.search(value);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            if(apiResponse.getStatusBoolean()){
                createList(REFERENCE_MANUFACTURER,apiResponse.getResult().getList());
            }else{
                toast(ProductValueDetails.this,"Nenhum item encontrado.");
            }
        }
    }

}
