package br.com.tercomfuncionario.Control;

import android.app.Activity;
import android.util.Pair;

import java.util.TreeMap;

import br.com.tercomfuncionario.Entity.ApiResponse;
import br.com.tercomfuncionario.Entity.PackageList;
import br.com.tercomfuncionario.Entity.ProductCategory;
import br.com.tercomfuncionario.Enum.EnumMethod;
import br.com.tercomfuncionario.Enum.EnumREST;
import br.com.tercomfuncionario.Util.CustomPair;

public class ProductPackageControl extends GenericControl {


    private Activity activity;

    public ProductPackageControl(Activity activity) {
        this.activity = activity;
    }


    public ApiResponse add(String name) {

        TreeMap<String,String> map = new TreeMap<>();
        map.put("name", name);

        try {
            String link = getBase(EnumREST.SITE, EnumREST.PRODUCTPACKAGE, EnumREST.ADD);
            Pair<String, String> completePost = new Pair<>(link, getPostValues(map));
            CustomPair<String> jsonResult =  callJson(EnumMethod.POST,activity,completePost);
            ApiResponse<ProductCategory> providerApiResponse = new ApiResponse<>(ProductCategory.class);
            if(jsonResult.first){
                providerApiResponse = populateApiResponse(providerApiResponse,jsonResult.second);
            }
            return providerApiResponse;
        } catch (Exception e) {
            e.printStackTrace();
            return getErrorResponse();
        }
    }


    public ApiResponse update(int idProductPackage, String name) {

        TreeMap<String,String> map = new TreeMap<>();
        map.put("name", name);

        try {
            String link = getLink(getBase(EnumREST.SITE, EnumREST.PRODUCTPACKAGE, EnumREST.SET), String.valueOf(idProductPackage));
            Pair<String, String> completePost = new Pair<>(link, getPostValues(map));
            CustomPair<String> jsonResult =  callJson(EnumMethod.POST,activity,completePost);
            ApiResponse<ProductCategory> providerApiResponse = new ApiResponse<>(ProductCategory.class);
            if(jsonResult.first){
                providerApiResponse = populateApiResponse(providerApiResponse,jsonResult.second);
            }
            return providerApiResponse;
        } catch (Exception e) {
            e.printStackTrace();
            return getErrorResponse();
        }
    }

    public ApiResponse remove(int idProductPackage) {
        try {
            String link = getLink(getBase(EnumREST.SITE, EnumREST.PRODUCTPACKAGE, EnumREST.REMOVE), String.valueOf(idProductPackage));
            CustomPair<String> jsonResult =  callJson(EnumMethod.GET,activity,link);
            ApiResponse<ProductCategory> providerApiResponse = new ApiResponse<>(ProductCategory.class);
            if(jsonResult.first){
                providerApiResponse = populateApiResponse(providerApiResponse,jsonResult.second);
            }
            return providerApiResponse;
        } catch (Exception e) {
            e.printStackTrace();
            return getErrorResponse();
        }
    }


    public ApiResponse get(int idProductPackage) {
        try {
            String link = getLink(getBase(EnumREST.SITE, EnumREST.PRODUCTPACKAGE, EnumREST.GET), String.valueOf(idProductPackage));
            CustomPair<String> jsonResult =  callJson(EnumMethod.GET,activity,link);
            ApiResponse<ProductCategory> providerApiResponse = new ApiResponse<>(ProductCategory.class);
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
            String link = getLink(getBase(EnumREST.SITE, EnumREST.PRODUCTPACKAGE, EnumREST.SEARCH,EnumREST.NAME), value);
            CustomPair<String> jsonResult =  callJson(EnumMethod.GET,activity,link);
            ApiResponse<PackageList> providerApiResponse = new ApiResponse<>(PackageList.class);
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
