package br.com.tercom.Boundary.Activity;

import android.os.Bundle;


import br.com.tercom.Boundary.BoundaryUtil.AbstractAppCompatActivity;
import br.com.tercom.R;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ProviderListActivity extends AbstractAppCompatActivity {

    @OnClick(R.id.fabAddProvider) void sendToAddProvider(){
        createIntentAbs(AddProviderActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_provider_list_boundary);
        ButterKnife.bind(this);
    }
}
