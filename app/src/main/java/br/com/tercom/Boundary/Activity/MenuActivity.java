package br.com.tercom.Boundary.Activity;

import android.os.Bundle;
import android.widget.TextView;

import java.util.Locale;

import br.com.tercom.Boundary.BoundaryUtil.AbstractAppCompatActivity;
import br.com.tercom.R;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static br.com.tercom.Application.AppTercom.USER_STATIC;

public class MenuActivity extends AbstractAppCompatActivity {

    @BindView(R.id.txtWelcome)
    TextView txtWelcome;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        ButterKnife.bind(this);
        createToolbarWithNavigation(1);
        txtWelcome.setText(String.format(Locale.US,"Bem Vindo(a) %s",USER_STATIC.getTercomEmployee().getName()));
    }
}
