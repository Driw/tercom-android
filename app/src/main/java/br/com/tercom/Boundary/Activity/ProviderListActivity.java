package br.com.tercom.Boundary.Activity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Looper;


import br.com.tercom.Boundary.BoundaryUtil.AbstractAppCompatActivity;
import br.com.tercom.Control.ProviderControl;
import br.com.tercom.Entity.ApiResponse;
import br.com.tercom.Entity.Provider;
import br.com.tercom.Entity.ProviderList;
import br.com.tercom.R;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static br.com.tercom.Util.Util.print;

public class ProviderListActivity extends AbstractAppCompatActivity {

    private ProviderTask providerTask;

    @OnClick(R.id.fabAddProvider) void sendToAddProvider(){
        createIntentAbs(AddProviderActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_provider_list_boundary);
        ButterKnife.bind(this);
//        initProviderTask(1);
    }


    private void initProviderTask(int page){
        if(providerTask == null || providerTask.getStatus() == AsyncTask.Status.RUNNING){
            providerTask = new ProviderTask(page);
            providerTask.execute();
        }
    }

    private class ProviderTask extends AsyncTask<Void, Void, Void>
    {

        private ApiResponse<ProviderList> apiResponse;
        private int page;

        public ProviderTask(int page){

            this.page = page;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            if(Looper.myLooper() == null)
                Looper.prepare();
            ProviderControl providerControl = new ProviderControl(ProviderListActivity.this);
            apiResponse = providerControl.callJsonList(page);
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
