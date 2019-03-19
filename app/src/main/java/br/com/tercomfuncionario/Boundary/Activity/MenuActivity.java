package br.com.tercomfuncionario.Boundary.Activity;

import android.os.Bundle;
import android.widget.TextView;

import br.com.tercomfuncionario.Boundary.BoundaryUtil.AbstractAppCompatActivity;
import br.com.tercomfuncionario.R;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MenuActivity extends AbstractAppCompatActivity {

    @BindView(R.id.txtWelcome)
    TextView txtWelcome;

    @OnClick(R.id.cardQuotation) void sendToQuotation (){
         createIntentAbs(OrderMainActivity.class);
    }
    @OnClick(R.id.cardProduct) void sendToProduct (){
         createIntentAbs(ProductListActivity.class);
    }
    @OnClick(R.id.cardService) void sendToService (){
        createIntentAbs(ServiceListActivity.class);
    }
    @OnClick(R.id.cardProvider) void sendToProvider (){
        createIntentAbs(ProviderListActivity.class);
    }
    @OnClick(R.id.cardManufacturer) void sendToManufacturer (){
        createIntentAbs(ManufacturerActivity.class);
    }
    @OnClick(R.id.cardPermissions) void sendToPermissions (){
        createIntentAbs(PermissionsActivity.class);
    }
    @OnClick(R.id.txtMenuOrder) void sendToOrder (){
        createIntentAbs(OpenOrderListActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        ButterKnife.bind(this);
        //createToolbarWithNavigation(1);
        //txtWelcome.setText(String.format(Locale.US,"Bem Vindo(a) %s",USER_STATIC.getTercomEmployee().getName()));
    }
}
