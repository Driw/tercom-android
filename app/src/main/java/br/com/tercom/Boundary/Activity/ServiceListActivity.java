package br.com.tercom.Boundary.Activity;

import android.os.Bundle;
import android.view.WindowManager;

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
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        ButterKnife.bind(this);
        createToolbarWithNavigation(3);
    }


}
