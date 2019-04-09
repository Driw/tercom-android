package br.com.tercomfuncionario.Control;

import android.app.Activity;

import br.com.tercomfuncionario.Entity.ApiResponse;
import br.com.tercomfuncionario.Entity.ProductValue;
import br.com.tercomfuncionario.Entity.ProductValueList;
import br.com.tercomfuncionario.Entity.ServicePrice;
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
    public ApiResponse addProductPrices(int idQuote, int idQuoteProduct, int productId) {
        try{
            String link = getLink(getBase(EnumREST.SITE, EnumREST.QUOTEDPRODUCTPRICES, EnumREST.ADD), String.valueOf(idQuote) + "/" + String.valueOf(idQuoteProduct)
                    + "/" + String.valueOf(productId));
            CustomPair<String> jsonResult =  callJson(EnumMethod.GET,activity,link);
            ApiResponse<ProductValue> orderApiResponse = new ApiResponse<>(ProductValue.class);
            if(jsonResult.first){
                orderApiResponse = populateApiResponse(orderApiResponse,jsonResult.second);
            }
            return orderApiResponse;
        } catch (Exception e) {
            e.printStackTrace();
            return getErrorResponse();
        }
    }


    public ApiResponse removeProductPrice(int idQuote, int idQuoteProduct) {
        try{
            String link = getLink(getBase(EnumREST.SITE, EnumREST.QUOTEDPRODUCTPRICES, EnumREST.REMOVE), String.valueOf(idQuote) + "/" + String.valueOf(idQuoteProduct));
            CustomPair<String> jsonResult =  callJson(EnumMethod.GET,activity,link);
            ApiResponse<ProductValue> orderApiResponse = new ApiResponse<>(ProductValue.class);
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
