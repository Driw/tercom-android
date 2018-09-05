package br.com.tercom.Boundary.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import br.com.tercom.Boundary.BoundaryUtil.AbstractAppCompatActivity;
import br.com.tercom.Entity.User;
import br.com.tercom.R;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static br.com.tercom.Application.AppTercom.USER_STATIC;

public class LoginActivity extends AbstractAppCompatActivity {

    @BindView(R.id.txtEmail)
    EditText txtEmail;

    @BindView(R.id.txtPass)
    EditText txtPass;

    @OnClick(R.id.btn_login) void fakeLogin(){
        USER_STATIC = new User();
        USER_STATIC.setEmail(txtEmail.getText().toString());
        USER_STATIC.setSenha(txtPass.getText().toString());
        createIntentAbs(ProviderListActivity.class);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_boundary);
        ButterKnife.bind(this);
        populate();
    }

    private void populate() {
        txtEmail.setText("felip.amalf@gmail.com", TextView.BufferType.EDITABLE);
        txtPass.setText("developer", TextView.BufferType.EDITABLE);
    }
}
