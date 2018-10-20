package br.com.tercom.Control;

import android.app.Activity;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Pair;

import java.util.ArrayList;
import java.util.TreeMap;

import br.com.tercom.Entity.ApiResponse;
import br.com.tercom.Entity.ProductFamily;
import br.com.tercom.Entity.ProductFamilyList;
import br.com.tercom.Entity.ProductValue;
import br.com.tercom.Entity.ProductValueList;
import br.com.tercom.Enum.EnumMethod;
import br.com.tercom.Enum.EnumREST;
import br.com.tercom.Util.CustomPair;

public class ProductValueControl extends GenericControl {

    private Activity activity;

    public ProductValueControl(Activity activity) {
        this.activity = activity;
    }


    public ApiResponse add(int idProduct, int idProvider, int idProductPackage, int idProductType, int amount, float price, @Nullable String name) {

        TreeMap<String,String> map = new TreeMap<>();
        map.put("idProduct", String.valueOf(idProduct));
        map.put("idProvider", String.valueOf(idProvider));
        map.put("amount", String.valueOf(amount));
        map.put("price", String.valueOf(price));
        map.put("name", String.valueOf(idProductPackage));
        map.put("idProductType", String.valueOf(idProductType));
        map.put("idProductPackage", String.valueOf(name));

        try {
            String link = getLink(getBase(EnumREST.SITE, EnumREST.PRODUCTVALUE, EnumREST.ADD), String.valueOf(idProduct));
            Pair<String, String> completePost = new Pair<>(link, getPostValues(map));
            CustomPair<String> jsonResult =  callJson(EnumMethod.POST,activity,completePost);
            ApiResponse<ProductValue> providerApiResponse = new ApiResponse<>(ProductValue.class);
            if(jsonResult.first){
                providerApiResponse = populateApiResponse(providerApiResponse,jsonResult.second);
            }
            return providerApiResponse;
        } catch (Exception e) {
            e.printStackTrace();
            return getErrorResponse();
        }
    }


    public ApiResponse update(int idProductPrice, @Nullable Integer idProvider, @Nullable Integer idManufacture, @Nullable Integer idProductPackage, @Nullable Integer idProductType,
                              @Nullable String name, @Nullable Integer amount, @Nullable Float price) {

        TreeMap<String,String> map = new TreeMap<>();
        if(idProvider != null) map.put("idProvider", String.valueOf(idProvider));
        if(idManufacture != null) map.put("idManufacture", String.valueOf(idManufacture));
        if(idProductPackage != null) map.put("idProductPackage", String.valueOf(idProductPackage));
        if(idProductType != null) map.put("idProductType", String.valueOf(idProductType));
        if(!TextUtils.isEmpty(name)) map.put("name", name);
        if(amount != null) map.put("amount", String.valueOf(amount));
        if(price != null) map.put("price", String.valueOf(price));

        try {
            String link = getLink(getBase(EnumREST.SITE, EnumREST.PRODUCTVALUE, EnumREST.SET), String.valueOf(idProductPrice));
            Pair<String, String> completePost = new Pair<>(link, getPostValues(map));
            CustomPair<String> jsonResult =  callJson(EnumMethod.POST,activity,completePost);
            ApiResponse<ProductValue> providerApiResponse = new ApiResponse<>(ProductValue.class);
            if(jsonResult.first){
                providerApiResponse = populateApiResponse(providerApiResponse,jsonResult.second);
            }
            return providerApiResponse;
        } catch (Exception e) {
            e.printStackTrace();
            return getErrorResponse();
        }
    }

    public ApiResponse remove(int idProductPrice) {
        try {
            String link = getLink(getBase(EnumREST.SITE, EnumREST.PRODUCTVALUE, EnumREST.REMOVE), String.valueOf(idProductPrice));
            CustomPair<String> jsonResult =  callJson(EnumMethod.GET,activity,link);
            ApiResponse<ProductValue> providerApiResponse = new ApiResponse<>(ProductValue.class);
            if(jsonResult.first){
                providerApiResponse = populateApiResponse(providerApiResponse,jsonResult.second);
            }
            return providerApiResponse;
        } catch (Exception e) {
            e.printStackTrace();
            return getErrorResponse();
        }
    }


    public ApiResponse get(int idProductPrice) {
        try {
            String link = getLink(getBase(EnumREST.SITE, EnumREST.PRODUCTVALUE, EnumREST.GET), String.valueOf(idProductPrice));
            CustomPair<String> jsonResult =  callJson(EnumMethod.GET,activity,link);
            ApiResponse<ProductValue> providerApiResponse = new ApiResponse<>(ProductValue.class);
            if(jsonResult.first){
                providerApiResponse = populateApiResponse(providerApiResponse,jsonResult.second);
            }
            return providerApiResponse;
        } catch (Exception e) {
            e.printStackTrace();
            return getErrorResponse();
        }
    }

    public ApiResponse getAll(int idProduct) {
        try {
            String link = getLink(getBase(EnumREST.SITE, EnumREST.PRODUCTVALUE, EnumREST.GETALL), String.valueOf(idProduct));
            CustomPair<String> jsonResult =  callJson(EnumMethod.GET,activity,link);
            ApiResponse<ProductValueList> providerApiResponse = new ApiResponse<>(ProductValueList.class);
            if(jsonResult.first){
                providerApiResponse = populateApiResponse(providerApiResponse,jsonResult.second);
            }
            return providerApiResponse;
        } catch (Exception e) {
            e.printStackTrace();
            return getErrorResponse();
        }
    }

    //TODO("Ver com o Driw: Como fazer esse search?")
    public ApiResponse search(String value) {
        try {
            String link = getLink(getBase(EnumREST.SITE, EnumREST.PRODUCTFAMILY, EnumREST.SEARCH,EnumREST.NAME), value);
            CustomPair<String> jsonResult =  callJson(EnumMethod.GET,activity,link);
            ApiResponse<ProductValueList> providerApiResponse = new ApiResponse<>(ProductValueList.class);
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
