package br.com.tercom.Boundary.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

import br.com.tercom.Boundary.BoundaryUtil.AbstractAppCompatActivity;
import br.com.tercom.Entity.Product;
import br.com.tercom.R;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static br.com.tercom.Util.GsonUtil.getItem;

public class ProductDetailsActivity extends AbstractAppCompatActivity {

    private Product product;
    private boolean enable;

    @BindView(R.id.txtName)
    EditText txtName;

    @BindView(R.id.txtDescription)
    EditText txtDescription;

    @BindView(R.id.txtUtility)
    EditText txtUtility;

    @BindView(R.id.txtFamily)
    EditText txtFamily;

    @BindView(R.id.txtGroup)
    EditText txtGroup;

    @BindView(R.id.txtSubGroup)
    EditText txtSubGroup;

    @BindView(R.id.txtSector)
    EditText txtSector;

    @OnClick(R.id.btn_prices) void seePrices(){
        Intent intent = new Intent(ProductDetailsActivity.this, ProductValueListActivity.class);
        intent.putExtra("idProduct", product.getId());
        startActivity(intent);
    }

    @OnClick(R.id.btn_update) void update(){
        setEnable(true);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);
        ButterKnife.bind(this);
        createToolbar();
        product = getItem(getIntent().getExtras().getString("product"),Product.class);
        populate();
        setEnable(false);
    }

    private void populate() {
        txtName.setText(product.getName());
        txtDescription.setText(product.getDescription());
        txtUtility.setText(product.getUtility());
        txtFamily.setText(product.getCategory().getFamily().getName());
        txtGroup.setText(product.getCategory().getGroup().getName());
        txtSubGroup.setText(product.getCategory().getSubgroup().getName());
        txtSector.setText(product.getCategory().getSector().getName());

    }

    private void setEnable(boolean enable) {
        txtName.setEnabled(enable);
        txtDescription.setEnabled(enable);
        txtUtility.setEnabled(enable);
        txtFamily.setEnabled(enable);
        txtGroup.setEnabled(enable);
        txtSubGroup.setEnabled(enable);
        txtSector.setEnabled(enable);

    }
}
