package br.com.tercom.Boundary.Activity;

import android.os.Bundle;
import android.widget.Button;

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
        btnSubmit.setEnabled(false);

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
    }
}
