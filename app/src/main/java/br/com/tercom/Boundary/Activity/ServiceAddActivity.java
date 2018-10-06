package br.com.tercom.Boundary.Activity;

import android.inputmethodservice.Keyboard;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Locale;

import br.com.tercom.Adapter.ManufacturerAdapter;
import br.com.tercom.Adapter.TagAdapter;
import br.com.tercom.Boundary.BoundaryUtil.AbstractAppCompatActivity;
import br.com.tercom.Interface.RecyclerViewOnClickListenerHack;
import br.com.tercom.R;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static br.com.tercom.Util.Util.toast;

public class ServiceAddActivity extends AbstractAppCompatActivity {

    private ArrayList<String> tags;
    private TagAdapter tagAdapter;

    @BindView(R.id.rv_tag)
    RecyclerView rvTag;
    @BindView(R.id.editTag)
    EditText editTag;

    @OnClick(R.id.btnAddTag) void addTag(){
            if(!editTag.getText().toString().trim().isEmpty()){
                tags.add(editTag.getText().toString());
                tagAdapter.notifyDataSetChanged();
                editTag.setText("", TextView.BufferType.EDITABLE);
                toast(ServiceAddActivity.this,String.format(Locale.US,"%s adicionado com sucesso.",editTag.getText().toString()));
            }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_service);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        ButterKnife.bind(this);
        createToolbar();
        tags = new ArrayList<>();
        initList();
    }

    private void initList() {
        tagAdapter = new TagAdapter(this,tags);
        LinearLayoutManager llmanager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rvTag.setLayoutManager(llmanager);
        rvTag.setAdapter(tagAdapter);
    }
}
