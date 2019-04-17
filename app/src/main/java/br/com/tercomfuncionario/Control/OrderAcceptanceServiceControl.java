package br.com.tercomfuncionario.Control;

import android.app.Activity;

import br.com.tercomfuncionario.Entity.ApiResponse;
import br.com.tercomfuncionario.Entity.OrderAcceptanceServiceList;
import br.com.tercomfuncionario.Enum.EnumMethod;
import br.com.tercomfuncionario.Enum.EnumREST;
import br.com.tercomfuncionario.Util.CustomPair;

public class OrderAcceptanceServiceControl extends GenericControl {
    private Activity activity;

    public OrderAcceptanceServiceControl(Activity activity) {
        this.activity = activity;
    }

    public ApiResponse getAll(int idOrderAcceptance) {
        try {
            String link = getLink(getBase(EnumREST.SITE, EnumREST.ORDERACCEPTANCESERVICE, EnumREST.GETALL), String.valueOf(idOrderAcceptance));
            CustomPair<String> jsonResult = callJson(EnumMethod.GET, activity, link);
            ApiResponse<OrderAcceptanceServiceList> apiResponse = new ApiResponse<>(OrderAcceptanceServiceList.class);
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
