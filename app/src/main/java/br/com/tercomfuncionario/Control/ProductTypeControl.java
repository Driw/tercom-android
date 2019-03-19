package br.com.tercomfuncionario.Control;

import android.app.Activity;

import br.com.tercomfuncionario.Entity.ApiResponse;
import br.com.tercomfuncionario.Entity.ProductTypeList;
import br.com.tercomfuncionario.Enum.EnumMethod;
import br.com.tercomfuncionario.Enum.EnumREST;
import br.com.tercomfuncionario.Util.CustomPair;

public class ProductTypeControl extends GenericControl {
    private Activity activity;

    public ProductTypeControl(Activity activity) { this.activity = activity; }

    public ApiResponse<ProductTypeList> search(String value)
    {
        try {
            String link = getLink(getBase(EnumREST.SITE, EnumREST.PRODUCTTYPE, EnumREST.SEARCH,EnumREST.NAME), value);
            CustomPair<String> jsonResult =  callJson(EnumMethod.GET,activity,link);
            ApiResponse<ProductTypeList> typeApiResponse = new ApiResponse<>(ProductTypeList.class);
            if(jsonResult.first){
                typeApiResponse = populateApiResponse(typeApiResponse,jsonResult.second);
            }
            return typeApiResponse;
        } catch (Exception e) {
            e.printStackTrace();
            return getErrorResponse();
        }
    }
}
