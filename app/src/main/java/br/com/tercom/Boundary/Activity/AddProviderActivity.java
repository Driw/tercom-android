package br.com.tercom.Boundary.Activity;

import android.os.Bundle;
import android.widget.Button;

import br.com.tercom.Boundary.BoundaryUtil.AbstractAppCompatActivity;
import br.com.tercom.Enum.EnumFont;
import br.com.tercom.R;
import butterknife.BindView;
import butterknife.ButterKnife;

import static br.com.tercom.Util.CustomTypeFace.overrideFonts;

public class AddProviderActivity extends AbstractAppCompatActivity {

    @BindView(R.id.btn_submit)
    Button btnSubmit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_provider);
        ButterKnife.bind(this);
        overrideFonts(this,getWindow().getDecorView().getRootView(), EnumFont.FONT_ROBOTO_REGULAR);
        btnSubmit.setEnabled(false);
    }
}
