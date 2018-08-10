package br.com.tercom.Boundary.Activity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Looper;
import android.widget.Button;

import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.Random;

import br.com.tercom.Annotation.BindObject;
import br.com.tercom.Boundary.BoundaryUtil.AbstractAppCompatActivity;
import br.com.tercom.Control.ProviderControl;
import br.com.tercom.Entity.ApiResponse;
import br.com.tercom.Entity.Provider;
import br.com.tercom.Entity.ProviderContact;
import br.com.tercom.Enum.EnumFont;
import br.com.tercom.R;
import butterknife.BindView;
import butterknife.ButterKnife;

import static br.com.tercom.Util.CustomTypeFace.overrideFonts;
import static br.com.tercom.Util.Util.jsonTeste;
import static br.com.tercom.Util.Util.print;
import static br.com.tercom.Util.Util.printTAG;

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

        ProviderTask task = new ProviderTask();
        task.execute();
    }

    private class ProviderTask extends AsyncTask<Void, Void, Void>
    {

        private ApiResponse<Provider> apiResponse;

        @Override
        protected Void doInBackground(Void... voids) {
            if(Looper.myLooper() == null)
                Looper.prepare();
            ProviderControl ctrl = new ProviderControl(AddProviderActivity.this);
            apiResponse = ctrl.callJsonList();
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            print(String.valueOf(apiResponse.getStatus()));
            print(apiResponse.getMessage());
            print(apiResponse.getTime());
            apiResponse.getResult().printObjectLog();
        }
    }

}
