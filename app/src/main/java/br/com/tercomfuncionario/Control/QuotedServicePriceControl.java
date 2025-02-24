package br.com.tercomfuncionario.Control;

import android.app.Activity;

import br.com.tercomfuncionario.Entity.ApiResponse;
import br.com.tercomfuncionario.Entity.ProductValue;
import br.com.tercomfuncionario.Entity.ServicePrice;
import br.com.tercomfuncionario.Entity.ServicePriceList;
import br.com.tercomfuncionario.Enum.EnumMethod;
import br.com.tercomfuncionario.Enum.EnumREST;
import br.com.tercomfuncionario.Util.CustomPair;

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

    public ApiResponse addServicePrice(int idQuote, int idQuotedService, int serviceId) {
        try{
            String link = getLink(getBase(EnumREST.SITE, EnumREST.QUOTEDSERVICEPRICE, EnumREST.ADD), String.valueOf(idQuote) + "/" + String.valueOf(idQuotedService)
                    + "/" + String.valueOf(serviceId));
            CustomPair<String> jsonResult =  callJson(EnumMethod.GET,activity,link);
            ApiResponse<ServicePrice> orderApiResponse = new ApiResponse<>(ServicePrice.class);
            if(jsonResult.first){
                orderApiResponse = populateApiResponse(orderApiResponse,jsonResult.second);
            }
            return orderApiResponse;
        } catch (Exception e) {
            e.printStackTrace();
            return getErrorResponse();
        }
    }


    public ApiResponse removeServicePrice(int idQuote, int idQuotedService) {
        try{
            String link = getLink(getBase(EnumREST.SITE, EnumREST.QUOTEDSERVICEPRICE, EnumREST.REMOVE), String.valueOf(idQuote) + "/" + String.valueOf(idQuotedService));
            CustomPair<String> jsonResult =  callJson(EnumMethod.GET,activity,link);
            ApiResponse<ServicePrice> orderApiResponse = new ApiResponse<>(ServicePrice.class);
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
