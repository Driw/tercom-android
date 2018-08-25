package br.com.tercom.Control;

import android.app.Activity;
import android.util.Pair;

import java.util.TreeMap;

import br.com.tercom.Entity.ApiResponse;
import br.com.tercom.Entity.Provider;
import br.com.tercom.Entity.ProviderList;
import br.com.tercom.Enum.EnumMethod;
import br.com.tercom.Enum.EnumWebServices;
import br.com.tercom.Util.CustomPair;

public class ProviderControl extends GenericControl {

    private Activity activity;

    public ProviderControl(Activity activity){
        this.activity = activity;
    }

    // id teste = 1

    public ApiResponse callJson(int idProvider) {
        CustomPair<String> jsonResult =  callJson(EnumMethod.GET,activity,getLink(getBase(EnumWebServices.SITE,EnumWebServices.PROVIDER,EnumWebServices.GET), String.valueOf(idProvider)));
        ApiResponse<Provider> providerApiResponse = new ApiResponse<>(Provider.class);
        if(jsonResult.first){
            providerApiResponse = populateApiResponse(providerApiResponse,jsonResult.second);
        }
        return providerApiResponse;
    }

    public ApiResponse callJsonList(int page) {
        CustomPair<String> jsonResult =  callJson(EnumMethod.GET,activity,getLink(getBase(EnumWebServices.SITE,EnumWebServices.PROVIDER,EnumWebServices.LIST), String.valueOf(page)));
        ApiResponse<ProviderList> providerApiResponse = new ApiResponse<>(ProviderList.class);
        if(jsonResult.first){
            providerApiResponse = populateApiResponse(providerApiResponse,jsonResult.second);
        }
        return providerApiResponse;
    }

    public ApiResponse callJsonAddProvider(String companyName, String fantasyName, String cnpj, String site) {

        TreeMap<String,String> map = new TreeMap<>();
        map.put("companyName", companyName);
        map.put("fantasyName", fantasyName);
        map.put("cnpj",cnpj);
        map.put("site", site);

        try {
        String link = getBase(EnumWebServices.SITE,EnumWebServices.PROVIDER, EnumWebServices.ADD);
            Pair<String, String> completePost = new Pair<>(link, getPostValues(map));
        CustomPair<String> jsonResult =  callJson(EnumMethod.POST,activity,completePost);
        ApiResponse<Provider> providerApiResponse = new ApiResponse<>(Provider.class);
        if(jsonResult.first){
            providerApiResponse = populateApiResponse(providerApiResponse,jsonResult.second);
        }
        return providerApiResponse;
        } catch (Exception e) {
            e.printStackTrace();
            return getErrorResponse();
        }
    }

}
