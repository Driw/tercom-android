package br.com.tercom.Boundary.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import java.util.HashMap;

import br.com.tercom.Application.AppTercom;
import br.com.tercom.R;
import br.com.tercom.Util.Component.CustomEditText;
import br.com.tercom.Util.GenericEntityFiller;

public class LoginActivity extends AppCompatActivity {
    private HashMap<String, Object> entities;
    private final View.OnClickListener btnLogin_Click = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            CustomEditText email = findViewById(R.id.editEmail);
            CustomEditText senha = findViewById(R.id.editSenha);
            try {
                entities = GenericEntityFiller.fillEntity((ViewGroup) findViewById(R.id.ConstraintViewGroup));
            }
            catch (Exception e)
            {
                Toast.makeText(AppTercom.getContext(), "ERRO conversão dinâmica", Toast.LENGTH_SHORT).show();
                e.printStackTrace();
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(br.com.tercom.R.layout.activity_login);

        Button btn_Login = (Button)findViewById(R.id.btnLogin);
        btn_Login.setOnClickListener(btnLogin_Click);
    }
}
