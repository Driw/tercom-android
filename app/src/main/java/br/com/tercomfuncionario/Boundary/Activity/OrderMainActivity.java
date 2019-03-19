package br.com.tercomfuncionario.Boundary.Activity;

import android.os.Bundle;

import br.com.tercomfuncionario.Boundary.BoundaryUtil.AbstractAppCompatActivity;
import br.com.tercomfuncionario.R;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class OrderMainActivity extends AbstractAppCompatActivity {

    @OnClick(R.id.btnOpenOrderList) void openOrderList(){
        createIntentAbs(OpenOrderListActivity.class);
    }
    @OnClick(R.id.btnInicilizedOrderList) void inicialiedOrderList(){
        createIntentAbs(InicializedOrderListActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_main_menu);
        ButterKnife.bind(this);
        //createToolbar();
    }

}
