package br.com.tercom.Boundary.Activity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import br.com.tercom.Boundary.BoundaryUtil.AbstractAppCompatActivity;
import br.com.tercom.R;
import butterknife.BindView;
import butterknife.ButterKnife;

public class ProductValueDetails extends AbstractAppCompatActivity {
    @BindView(R.id.txtName)
    EditText txtName;
    @BindView(R.id.txtAmount)
    EditText txtAmount;
    @BindView(R.id.txtValue)
    EditText txtValue;
    @BindView(R.id.txtProvider)
    TextView txtProvider;
    @BindView(R.id.txtManufacturer)
    TextView txtManufacturer;
    @BindView(R.id.txtPackage)
    TextView txtPackage;
    @BindView(R.id.txtType)
    TextView txtType;
    @BindView(R.id.btn_delete)
    Button btn_delete;
    @BindView(R.id.btn_function)
    Button btn_function;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_value_generic);
        ButterKnife.bind(this);
    }
}
