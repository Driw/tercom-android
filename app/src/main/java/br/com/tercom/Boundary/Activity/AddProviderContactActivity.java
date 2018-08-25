package br.com.tercom.Boundary.Activity;

import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.WindowManager;
import android.widget.EditText;

import br.com.tercom.Boundary.BoundaryUtil.AbstractAppCompatActivity;
import br.com.tercom.R;
import butterknife.BindView;

public class AddProviderContactActivity extends AbstractAppCompatActivity {

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

    //RecyclerView
    @BindView(R.id.lv_Contact)
    RecyclerView lv_Contact;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_provider_contact);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
    }
}
