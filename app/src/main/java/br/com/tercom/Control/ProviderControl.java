package br.com.tercom.Control;

import android.app.Activity;

import org.json.JSONException;
import org.json.JSONObject;

import br.com.tercom.Entity.ApiResponse;
import br.com.tercom.Entity.Provider;
import br.com.tercom.Enum.EnumMethod;
import br.com.tercom.Enum.EnumWebServices;
import br.com.tercom.Util.CustomPair;

public class ProviderControl extends GenericControl {

    private Activity activity;

    public ProviderControl(Activity activity){
        this.activity = activity;
    }

    // id teste = 1

    public ApiResponse<Provider> callJson(int idProvider) {
        CustomPair<String> jsonResult =  callJson(EnumMethod.GET,activity,getLink(getBase(EnumWebServices.SITE,EnumWebServices.PROVIDER,EnumWebServices.GET), String.valueOf(idProvider)));
        ApiResponse<Provider> providerApiResponse = new ApiResponse<>(Provider.class);
        if(jsonResult.first){
            providerApiResponse = populateApiResponse(providerApiResponse,jsonResult.second);
        }
        return providerApiResponse;
    }

}
