package br.com.tercom.Control;

import android.app.Activity;
import android.util.Pair;

import java.util.TreeMap;

import br.com.tercom.Entity.ApiResponse;
import br.com.tercom.Entity.ProductGroup;
import br.com.tercom.Entity.ServicePrice;
import br.com.tercom.Enum.EnumMethod;
import br.com.tercom.Enum.EnumREST;
import br.com.tercom.Util.CustomPair;

public class ServicePriceControl extends GenericControl {
    private Activity activity;

    public ServicePriceControl(Activity activitiy) { this.activity = activitiy; }

    public ApiResponse add(int idService, int idProvider, float price, String name){
        TreeMap<String,String> map = new TreeMap<>();
        map.put("idService", String.valueOf(idService));
        map.put("idProvider", String.valueOf(idProvider));
        map.put("name", name);

        try {
            String link = getBase(EnumREST.SITE, EnumREST.SERVICEPRICE, EnumREST.ADD);
            Pair<String, String> completePost = new Pair<>(link, getPostValues(map));
            CustomPair<String> jsonResult =  callJson(EnumMethod.POST,activity,completePost);
            ApiResponse<ServicePrice> servicePriceApiResponse = new ApiResponse<>(ServicePrice.class);
            if(jsonResult.first){
                servicePriceApiResponse = populateApiResponse(servicePriceApiResponse,jsonResult.second);
            }
            return servicePriceApiResponse;
        } catch (Exception e) {
            e.printStackTrace();
            return getErrorResponse();
        }
    }
}
