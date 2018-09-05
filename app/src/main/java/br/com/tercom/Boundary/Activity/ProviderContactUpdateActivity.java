package br.com.tercom.Boundary.Activity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Looper;
import android.support.v7.widget.RecyclerView;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;

import br.com.tercom.Boundary.BoundaryUtil.AbstractAppCompatActivity;
import br.com.tercom.Control.ProviderControl;
import br.com.tercom.Entity.ApiResponse;
import br.com.tercom.Entity.Phone;
import br.com.tercom.Entity.ProviderContact;
import br.com.tercom.Enum.EnumDialogOptions;
import br.com.tercom.Enum.PhoneType;
import br.com.tercom.R;
import br.com.tercom.Util.CustomPair;
import br.com.tercom.Util.DialogConfirm;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static br.com.tercom.Util.GsonUtil.getItem;
import static br.com.tercom.Util.TextUtil.dddValidator;
import static br.com.tercom.Util.TextUtil.emailValidator;
import static br.com.tercom.Util.TextUtil.emptyValidator;
import static br.com.tercom.Util.TextUtil.nameValidator;
import static br.com.tercom.Util.TextUtil.phoneValidator;

public class ProviderContactUpdateActivity extends AbstractAppCompatActivity {

    private SendContactTask sendContactTask;
    private ProviderControl providerControl;
    private SetPhoneTask setPhoneTask;
    private int idProvider;
    private ProviderContact contact;
    private boolean isEnable;

    @BindView(R.id.txtContactName)
    EditText txtContactName;

    @BindView(R.id.txtPosition)
    EditText txtPosition;

    @BindView(R.id.txtEmail)
    EditText txtEmail;

    @BindView(R.id.txtDDDPhone)
    EditText txtDDDPhone;

    @BindView(R.id.txtPhone)
    EditText txtPhone;

    @BindView(R.id.txtDDDCel)
    EditText txtDDDCel;

    @BindView(R.id.txtCel)
    EditText txtCel;


    @OnClick(R.id.btn_submit) void sendContact(){

        if(isEnable){

        CustomPair<String> result  = verifyData(txtContactName.getText().toString(),txtPosition.getText().toString(), txtEmail.getText().toString(), txtDDDPhone.getText().toString(),
                txtPhone.getText().toString(), txtDDDCel.getText().toString(), txtCel.getText().toString());
        if(result.first){
            Phone commercial = new Phone();
            commercial.setDdd(Integer.parseInt(txtDDDPhone.getText().toString()));
            commercial.setNumber(txtPhone.getText().toString());
            commercial.setType(PhoneType.COMMERCIAL.type);
            Phone otherPhone = new Phone();
            otherPhone.setDdd(Integer.parseInt(txtDDDCel.getText().toString()));
            otherPhone.setNumber(txtCel.getText().toString());
            otherPhone.setType(PhoneType.CELLPHONE.type);
            ProviderContact providerContact = new ProviderContact();
            providerContact.setEmail(txtEmail.getText().toString());
            providerContact.setName(txtContactName.getText().toString());
            providerContact.setPosition(txtPosition.getText().toString());
            providerContact.setCommercial(commercial);
            providerContact.setOtherphone(otherPhone);
            initContactTask(providerContact);
            setEnable(false);
        }

        }else{
            setEnable(true);
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_provider_contact);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        ButterKnife.bind(this);
        setEnable(false);
        providerControl = new ProviderControl(this);
        try{
            idProvider = getIntent().getExtras().getInt("idProvider");
            contact = getItem(getIntent().getExtras().getString("details"),ProviderContact.class);
            populate();
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    private void populate() {
        txtContactName.setText(contact.getName(), TextView.BufferType.EDITABLE);
        txtPosition.setText(contact.getPosition(), TextView.BufferType.EDITABLE);
        txtEmail.setText(contact.getEmail(), TextView.BufferType.EDITABLE);
        txtDDDPhone.setText(String.valueOf(contact.getCommercial().getDdd()), TextView.BufferType.EDITABLE);
        txtPhone.setText(String.valueOf(contact.getCommercial().getNumber()), TextView.BufferType.EDITABLE);
        txtDDDCel.setText(String.valueOf(contact.getOtherphone().getDdd()), TextView.BufferType.EDITABLE);
        txtCel.setText(String.valueOf(contact.getOtherphone().getNumber()), TextView.BufferType.EDITABLE);

    }


    private void setEnable(boolean enable){
        isEnable = enable;
        txtContactName.setEnabled(enable);
        txtPosition.setEnabled(enable);
        txtEmail.setEnabled(enable);
        txtDDDPhone.setEnabled(enable);
        txtPhone.setEnabled(enable);
        txtDDDCel.setEnabled(enable);
        txtCel.setEnabled(enable);
    }


    private CustomPair<String> verifyData(String contactName, String position, String email, String dddPhone, String phone, String dddCel, String cel){

        if(!nameValidator(contactName))
            return new CustomPair<>(false,"Nome inválida");

        if(!emptyValidator(position))
            return new CustomPair<>(false,"Cargo Inválido");

        if(!emailValidator(email))
            return new CustomPair<>(false,"E-mail Inválido");

        if(!dddValidator(dddPhone))
            return new CustomPair<>(false,"DDD do telefone inválido");

        if(!phoneValidator(phone))
            return new CustomPair<>(false,"Telefone inválido");

        if(!dddValidator(dddCel))
            return new CustomPair<>(false,"DDD do celular inválido");

        if(!phoneValidator(cel))
            return new CustomPair<>(false,"Celular inválido");

        return new CustomPair<>(true,"Ok");
    }

    private void initContactTask(ProviderContact providerContact){
        if(sendContactTask == null || sendContactTask.getStatus() != AsyncTask.Status.RUNNING){
            sendContactTask = new SendContactTask(providerContact);
            sendContactTask.execute();
        }
    }
    private void initPhoneTask(ProviderContact providerContact){
        if(setPhoneTask == null || setPhoneTask.getStatus() != AsyncTask.Status.RUNNING){
            setPhoneTask = new SetPhoneTask(providerContact);
            setPhoneTask.execute();
        }
    }

    private class SendContactTask extends AsyncTask<Void,Void,Void> {

        private ApiResponse<ProviderContact> apiResponse;
        private ProviderContact providerContact;

        public SendContactTask(ProviderContact providerContact){

            this.providerContact = providerContact;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            if(Looper.myLooper() == null)
                Looper.prepare();
            apiResponse = providerControl.updateProviderContact(idProvider,providerContact.getName(),providerContact.getEmail(),providerContact.getPosition(),contact.getId());
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            if(apiResponse.getStatusBoolean()){
                apiResponse.getResult().printObjectLog();
                providerContact.setId(apiResponse.getResult().getId());
                initPhoneTask(providerContact);
            }else{

            }
        }
    }

    private class SetPhoneTask extends AsyncTask<Void,Void,Void> {

        private ApiResponse<ProviderContact> apiResponse;
        private ProviderContact providerContact;

        public SetPhoneTask(ProviderContact providerContact){

            this.providerContact = providerContact;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            if(Looper.myLooper() == null)
                Looper.prepare();
            apiResponse = providerControl.setProviderPhone(idProvider,providerContact.getId(),providerContact.getCommercial().getCompletePair()
                    ,providerContact.getOtherphone().getCompletePair());
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            DialogConfirm dialogConfirm = new DialogConfirm(ProviderContactUpdateActivity.this);
            if(apiResponse.getStatusBoolean()){
                dialogConfirm.init(EnumDialogOptions.CONFIRM,apiResponse.getMessage());
            }else{
                dialogConfirm.init(EnumDialogOptions.FAIL,apiResponse.getMessage());

            }
        }
    }
}
