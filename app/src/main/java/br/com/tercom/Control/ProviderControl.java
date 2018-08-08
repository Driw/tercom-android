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

    public ApiResponse<Provider> callJson(int idProvider) throws JSONException {
        CustomPair<String> jsonResult =  callJson(EnumMethod.GET,activity,getLink(getBase(EnumWebServices.SITE,EnumWebServices.PROVIDER,EnumWebServices.GET), String.valueOf(idProvider)));
        if(jsonResult.first){
            JSONObject jsonObject = new JSONObject(jsonResult.second);
            ApiResponse<Provider> providerApiResponse = new ApiResponse<>();
            providerApiResponse.setStatus(jsonObject.getInt("status"));
            providerApiResponse.setMessage(jsonObject.getString("message"));
            providerApiResponse.setTime(jsonObject.getString("time"));
            providerApiResponse.setResult(jsonResult.second);
        }
        return null;
    }

}
