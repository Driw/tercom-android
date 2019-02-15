package br.com.tercom.Control;

import android.app.Activity;
import android.util.Pair;

import java.util.TreeMap;

import br.com.tercom.Entity.ApiResponse;
import br.com.tercom.Entity.OrderItemProductList;
import br.com.tercom.Entity.ProductValueList;
import br.com.tercom.Enum.EnumMethod;
import br.com.tercom.Enum.EnumREST;
import br.com.tercom.Util.CustomPair;

public class QuotedProductPriceControl extends GenericControl {
    Activity activity;

    public QuotedProductPriceControl(Activity activity) {
        this.activity = activity;
    }

    public ApiResponse getProductPrices(int idOrderRequest, int idOrderItemProduct) {
        try{
            String link = getLink(getBase(EnumREST.SITE, EnumREST.QUOTEDPRODUCTPRICES, EnumREST.PRICES), String.valueOf(idOrderRequest) + "/" + String.valueOf(idOrderItemProduct));
            CustomPair<String> jsonResult =  callJson(EnumMethod.GET,activity,link);
            ApiResponse<ProductValueList> orderApiResponse = new ApiResponse<>(ProductValueList.class);
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
