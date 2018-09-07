package br.com.tercom.Boundary.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import br.com.tercom.Boundary.BoundaryUtil.AbstractAppCompatActivity;
import br.com.tercom.R;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ServiceListActivity extends AbstractAppCompatActivity {

    @OnClick(R.id.btn_add_product) void add(){
        createIntentAbs(ServiceAddActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_list);
        ButterKnife.bind(this);
        CreateToolbarWithNavigation(3);
    }
}
