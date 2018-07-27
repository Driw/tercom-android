package br.com.tercom.Boundary.Activity;

import android.os.Bundle;
import android.widget.Button;

import com.google.gson.Gson;

import org.json.JSONObject;

import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Field;
import java.util.Iterator;

import br.com.tercom.Annotation.BindObject;
import br.com.tercom.Boundary.BoundaryUtil.AbstractAppCompatActivity;
import br.com.tercom.Entity.ProviderContact;
import br.com.tercom.Enum.EnumFont;
import br.com.tercom.R;
import butterknife.BindView;
import butterknife.ButterKnife;

import static br.com.tercom.Util.CustomTypeFace.overrideFonts;
import static br.com.tercom.Util.Util.print;

public class AddProviderActivity extends AbstractAppCompatActivity {

    @BindView(R.id.btn_submit)
    Button btnSubmit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_provider);
        ButterKnife.bind(this);
        overrideFonts(this,getWindow().getDecorView().getRootView(), EnumFont.FONT_ROBOTO_REGULAR);
        CreateToolbarWithNavigation(1);
        btnSubmit.setEnabled(false);

        ProviderContact npc = new ProviderContact();
        npc.setCargo("cargo");
        npc.setEmail("email");
        npc.setId(1);
        npc.setNome("nome");
        String jsonTest = new Gson().toJson(npc);

        Class<ProviderContact> pc = ProviderContact.class;
        //TODO(EXEMPLO DE ANNOTATION!!!)
        for (Field f : pc.getDeclaredFields())
        {
            if(f.isAnnotationPresent(BindObject.class))
            {
                BindObject bo = f.getAnnotation(BindObject.class);
                print("Annotation boladona tô entendendo nada " + bo.value());
            }
        }

        ProviderContact newPc = new ProviderContact();
        newPc.toObject(jsonTest);
        String teste = "";
    }
}
