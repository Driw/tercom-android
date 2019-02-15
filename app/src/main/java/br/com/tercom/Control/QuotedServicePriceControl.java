package br.com.tercom.Control;

import android.app.Activity;

import br.com.tercom.Entity.ApiResponse;
import br.com.tercom.Entity.ProductValueList;
import br.com.tercom.Entity.ServicePriceList;
import br.com.tercom.Enum.EnumMethod;
import br.com.tercom.Enum.EnumREST;
import br.com.tercom.Util.CustomPair;

public class QuotedServicePriceControl extends GenericControl {
    Activity activity;

    public QuotedServicePriceControl(Activity activity) {
        this.activity = activity;
    }

    public ApiResponse getServicePrice(int idOrderRequest, int idOrderItemProduct) {
        try{
            String link = getLink(getBase(EnumREST.SITE, EnumREST.QUOTEDSERVICEPRICE, EnumREST.PRICES), String.valueOf(idOrderRequest) + "/" + String.valueOf(idOrderItemProduct));
            CustomPair<String> jsonResult =  callJson(EnumMethod.GET,activity,link);
            ApiResponse<ServicePriceList> orderApiResponse = new ApiResponse<>(ServicePriceList.class);
            if(jsonResult.first){
                orderApiResponse = populateApiResponse(orderApiResponse,jsonResult.second);
            }
            return orderApiResponse;
        } catch (Exception e) {
            e.printStackTrace();
            return getErrorResponse();
        }
    }

}
