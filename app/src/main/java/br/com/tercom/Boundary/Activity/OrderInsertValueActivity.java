package br.com.tercom.Boundary.Activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import br.com.tercom.Boundary.BoundaryUtil.AbstractAppCompatActivity;
import br.com.tercom.R;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class OrderInsertValueActivity extends AbstractAppCompatActivity {

    @BindView(R.id.rv_OrderInsertValue)
    RecyclerView rv_OrderInsertValue;
    @BindView(R.id.txtOrderItemAddinfo)
    TextView txtOrderItemAddinfo;

    @OnClick(R.id.btnOrderInsertPrices) void insertValuesInOrder() {

    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_insert_values);
        ButterKnife.bind(this);
        //createToolbar();
    }
}
