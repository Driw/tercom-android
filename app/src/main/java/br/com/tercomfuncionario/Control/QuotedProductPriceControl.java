package br.com.tercomfuncionario.Control;

import android.app.Activity;

import br.com.tercomfuncionario.Entity.ApiResponse;
import br.com.tercomfuncionario.Entity.ProductValueList;
import br.com.tercomfuncionario.Enum.EnumMethod;
import br.com.tercomfuncionario.Enum.EnumREST;
import br.com.tercomfuncionario.Util.CustomPair;

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
