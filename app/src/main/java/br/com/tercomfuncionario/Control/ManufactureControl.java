package br.com.tercomfuncionario.Control;

import android.app.Activity;
import android.util.Pair;

import java.util.TreeMap;

import br.com.tercomfuncionario.Entity.ApiResponse;
import br.com.tercomfuncionario.Entity.Manufacture;
import br.com.tercomfuncionario.Entity.ManufactureList;
import br.com.tercomfuncionario.Enum.EnumMethod;
import br.com.tercomfuncionario.Enum.EnumREST;
import br.com.tercomfuncionario.Util.CustomPair;

public class ManufactureControl extends GenericControl {

    private Activity activity;

    public ManufactureControl(Activity activity) {
        this.activity = activity;
    }


    public ApiResponse add(String fantasyName) {

        TreeMap<String,String> map = new TreeMap<>();
        map.put("fantasyName", fantasyName);

        try {
            String link = getBase(EnumREST.SITE, EnumREST.MANUFACTURE, EnumREST.ADD);
            Pair<String, String> completePost = new Pair<>(link, getPostValues(map));
            CustomPair<String> jsonResult =  callJson(EnumMethod.POST,activity,completePost);
            ApiResponse<Manufacture> providerApiResponse = new ApiResponse<>(Manufacture.class);
            if(jsonResult.first){
                providerApiResponse = populateApiResponse(providerApiResponse,jsonResult.second);
            }
            return providerApiResponse;
        } catch (Exception e) {
            e.printStackTrace();
            return getErrorResponse();
        }
    }


    public ApiResponse update(int idManufacture, String fantasyName) {

        TreeMap<String,String> map = new TreeMap<>();
        map.put("fantasyName", fantasyName);

        try {
            String link = getLink(getBase(EnumREST.SITE, EnumREST.MANUFACTURE, EnumREST.SET), String.valueOf(idManufacture));
            Pair<String, String> completePost = new Pair<>(link, getPostValues(map));
            CustomPair<String> jsonResult =  callJson(EnumMethod.POST,activity,completePost);
            ApiResponse<Manufacture> providerApiResponse = new ApiResponse<>(Manufacture.class);
            if(jsonResult.first){
                providerApiResponse = populateApiResponse(providerApiResponse,jsonResult.second);
            }
            return providerApiResponse;
        } catch (Exception e) {
            e.printStackTrace();
            return getErrorResponse();
        }
    }

    public ApiResponse remove(int idManufacture) {
        try {
            String link = getLink(getBase(EnumREST.SITE, EnumREST.MANUFACTURE, EnumREST.REMOVE), String.valueOf(idManufacture));
            CustomPair<String> jsonResult =  callJson(EnumMethod.GET,activity,link);
            ApiResponse<Manufacture> providerApiResponse = new ApiResponse<>(Manufacture.class);
            if(jsonResult.first){
                providerApiResponse = populateApiResponse(providerApiResponse,jsonResult.second);
            }
            return providerApiResponse;
        } catch (Exception e) {
            e.printStackTrace();
            return getErrorResponse();
        }
    }


    public ApiResponse get(int idManufacture) {
        try {
            String link = getLink(getBase(EnumREST.SITE, EnumREST.MANUFACTURE, EnumREST.GET), String.valueOf(idManufacture));
            CustomPair<String> jsonResult =  callJson(EnumMethod.GET,activity,link);
            ApiResponse<Manufacture> providerApiResponse = new ApiResponse<>(Manufacture.class);
            if(jsonResult.first){
                providerApiResponse = populateApiResponse(providerApiResponse,jsonResult.second);
            }
            return providerApiResponse;
        } catch (Exception e) {
            e.printStackTrace();
            return getErrorResponse();
        }
    }


    public ApiResponse search(String value) {
        try {
            String link = getLink(getBase(EnumREST.SITE, EnumREST.MANUFACTURE, EnumREST.SEARCH,EnumREST.FANTASYNAME), value);
            CustomPair<String> jsonResult =  callJson(EnumMethod.GET,activity,link);
            ApiResponse<ManufactureList> providerApiResponse = new ApiResponse<>(ManufactureList.class);
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





