package br.com.tercomfuncionario.Control;

import android.app.Activity;

import br.com.tercomfuncionario.Entity.ApiResponse;
import br.com.tercomfuncionario.Entity.OrderAcceptanceProductList;
import br.com.tercomfuncionario.Enum.EnumMethod;
import br.com.tercomfuncionario.Enum.EnumREST;
import br.com.tercomfuncionario.Util.CustomPair;

public class OrderAcceptanceProductControl extends GenericControl {
    private Activity activity;

    public OrderAcceptanceProductControl(Activity activity) {
        this.activity = activity;
    }

    public ApiResponse getAll(int idOrderAcceptance) {
        try {
            String link = getLink(getBase(EnumREST.SITE, EnumREST.ORDERACCEPTANCEPRODUCT, EnumREST.GETALL), String.valueOf(idOrderAcceptance));
            CustomPair<String> jsonResult = callJson(EnumMethod.GET, activity, link);
            ApiResponse<OrderAcceptanceProductList> apiResponse = new ApiResponse<>(OrderAcceptanceProductList.class);
            if(jsonResult.first) {
                apiResponse = populateApiResponse(apiResponse, jsonResult.second);
            }
            return apiResponse;
        } catch (Exception e) {
            e.printStackTrace();
            return getErrorResponse();
        }
    }
}
