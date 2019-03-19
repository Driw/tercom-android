package br.com.tercomfuncionario.Control;

import android.app.Activity;
import android.util.Pair;

import java.util.TreeMap;

import br.com.tercomfuncionario.Entity.ApiResponse;
import br.com.tercomfuncionario.Entity.ProductSector;
import br.com.tercomfuncionario.Entity.ProductSectorList;
import br.com.tercomfuncionario.Enum.EnumMethod;
import br.com.tercomfuncionario.Enum.EnumREST;
import br.com.tercomfuncionario.Util.CustomPair;

public class ProductSectorControl extends GenericControl {

    private Activity activity;

    public ProductSectorControl(Activity activity) {
        this.activity = activity;
    }


    public ApiResponse add(int idProductSubGroup,String name) {

        TreeMap<String,String> map = new TreeMap<>();
        map.put("name", name);
        map.put("idProductSubGroup", String.valueOf(idProductSubGroup));

        try {
            String link = getBase(EnumREST.SITE, EnumREST.PRODUCTSECTOR, EnumREST.ADD);
            Pair<String, String> completePost = new Pair<>(link, getPostValues(map));
            CustomPair<String> jsonResult =  callJson(EnumMethod.POST,activity,completePost);
            ApiResponse<ProductSector> providerApiResponse = new ApiResponse<>(ProductSector.class);
            if(jsonResult.first){
                providerApiResponse = populateApiResponse(providerApiResponse,jsonResult.second);
            }
            return providerApiResponse;
        } catch (Exception e) {
            e.printStackTrace();
            return getErrorResponse();
        }
    }


    public ApiResponse update(int idProductSector, int idProductSubGroup, String name) {

        TreeMap<String,String> map = new TreeMap<>();
        map.put("name", name);
        map.put("idProductSubGroup", String.valueOf(idProductSubGroup));

        try {
            String link = getLink(getBase(EnumREST.SITE, EnumREST.PRODUCTSECTOR, EnumREST.SET), String.valueOf(idProductSector));
            Pair<String, String> completePost = new Pair<>(link, getPostValues(map));
            CustomPair<String> jsonResult =  callJson(EnumMethod.POST,activity,completePost);
            ApiResponse<ProductSector> providerApiResponse = new ApiResponse<>(ProductSector.class);
            if(jsonResult.first){
                providerApiResponse = populateApiResponse(providerApiResponse,jsonResult.second);
            }
            return providerApiResponse;
        } catch (Exception e) {
            e.printStackTrace();
            return getErrorResponse();
        }
    }

    public ApiResponse remove(int idProductSector) {
        try {
            String link = getLink(getBase(EnumREST.SITE, EnumREST.PRODUCTSECTOR, EnumREST.REMOVE), String.valueOf(idProductSector));
            CustomPair<String> jsonResult =  callJson(EnumMethod.GET,activity,link);
            ApiResponse<ProductSector> providerApiResponse = new ApiResponse<>(ProductSector.class);
            if(jsonResult.first){
                providerApiResponse = populateApiResponse(providerApiResponse,jsonResult.second);
            }
            return providerApiResponse;
        } catch (Exception e) {
            e.printStackTrace();
            return getErrorResponse();
        }
    }


    public ApiResponse get(int idProductSector) {
        try {
            String link = getLink(getBase(EnumREST.SITE, EnumREST.PRODUCTSECTOR, EnumREST.GET), String.valueOf(idProductSector));
            CustomPair<String> jsonResult =  callJson(EnumMethod.GET,activity,link);
            ApiResponse<ProductSector> providerApiResponse = new ApiResponse<>(ProductSector.class);
            if(jsonResult.first){
                providerApiResponse = populateApiResponse(providerApiResponse,jsonResult.second);
            }
            return providerApiResponse;
        } catch (Exception e) {
            e.printStackTrace();
            return getErrorResponse();
        }
    }

    public ApiResponse getSubSectors(int idProductSector) {
        try {
            String link = getLink(getBase(EnumREST.SITE, EnumREST.PRODUCTSECTOR, EnumREST.GETGROUPS), String.valueOf(idProductSector));
            CustomPair<String> jsonResult =  callJson(EnumMethod.GET,activity,link);
            ApiResponse<ProductSectorList> providerApiResponse = new ApiResponse<>(ProductSectorList.class);
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
            String link = getLink(getBase(EnumREST.SITE, EnumREST.PRODUCTSECTOR, EnumREST.SEARCH,EnumREST.NAME), value);
            CustomPair<String> jsonResult =  callJson(EnumMethod.GET,activity,link);
            ApiResponse<ProductSectorList> providerApiResponse = new ApiResponse<>(ProductSectorList.class);
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
