package br.com.tercom.Control;

import android.app.Activity;
import android.util.Pair;

import java.util.ArrayList;
import java.util.TreeMap;

import br.com.tercom.Entity.ApiResponse;
import br.com.tercom.Entity.Provider;
import br.com.tercom.Entity.ProviderContact;
import br.com.tercom.Entity.ProviderContactList;
import br.com.tercom.Entity.ProviderList;
import br.com.tercom.Enum.EnumMethod;
import br.com.tercom.Enum.EnumWebServices;
import br.com.tercom.Util.CustomPair;

public class ProviderControl extends GenericControl {

    private Activity activity;

    public ProviderControl(Activity activity){
        this.activity = activity;
    }

    // id teste = 1

    public ApiResponse callJson(int idProvider) {
        CustomPair<String> jsonResult =  callJson(EnumMethod.GET,activity,getLink(getBase(EnumWebServices.SITE,EnumWebServices.PROVIDER,EnumWebServices.GET), String.valueOf(idProvider)));
        ApiResponse<Provider> providerApiResponse = new ApiResponse<>(Provider.class);
        if(jsonResult.first){
            providerApiResponse = populateApiResponse(providerApiResponse,jsonResult.second);
        }
        return providerApiResponse;
    }

    public ApiResponse callJsonList(int page) {
        CustomPair<String> jsonResult =  callJson(EnumMethod.GET,activity,getLink(getBase(EnumWebServices.SITE,EnumWebServices.PROVIDER,EnumWebServices.LIST), String.valueOf(page)));
        ApiResponse<ProviderList> providerApiResponse = new ApiResponse<>(ProviderList.class);
        if(jsonResult.first){
            providerApiResponse = populateApiResponse(providerApiResponse,jsonResult.second);
        }
        return providerApiResponse;
    }

    public ApiResponse addProvider(String companyName, String fantasyName, String cnpj, String site,String spokesman) {

        TreeMap<String,String> map = new TreeMap<>();
        map.put("companyName", companyName);
        map.put("fantasyName", fantasyName);
        map.put("spokesman", spokesman);
        map.put("cnpj",cnpj);
        map.put("site", site);

        try {
        String link = getBase(EnumWebServices.SITE,EnumWebServices.PROVIDER, EnumWebServices.ADD);
            Pair<String, String> completePost = new Pair<>(link, getPostValues(map));
        CustomPair<String> jsonResult =  callJson(EnumMethod.POST,activity,completePost);
        ApiResponse<Provider> providerApiResponse = new ApiResponse<>(Provider.class);
        if(jsonResult.first){
            providerApiResponse = populateApiResponse(providerApiResponse,jsonResult.second);
        }
        return providerApiResponse;
        } catch (Exception e) {
            e.printStackTrace();
            return getErrorResponse();
        }
    }

    public ApiResponse updateProvider(String companyName, String fantasyName, String cnpj, String site,String spokesman, int idProvider) {

        TreeMap<String,String> map = new TreeMap<>();
        map.put("companyName", companyName);
        map.put("fantasyName", fantasyName);
        map.put("spokesman", spokesman);
        map.put("cnpj",cnpj);
        map.put("site", site);

        try {
        String link = getLink(getBase(EnumWebServices.SITE,EnumWebServices.PROVIDER, EnumWebServices.SET), String.valueOf(idProvider));
            Pair<String, String> completePost = new Pair<>(link, getPostValues(map));
        CustomPair<String> jsonResult =  callJson(EnumMethod.POST,activity,completePost);
        ApiResponse<Provider> providerApiResponse = new ApiResponse<>(Provider.class);
        if(jsonResult.first){
            providerApiResponse = populateApiResponse(providerApiResponse,jsonResult.second);
        }
        return providerApiResponse;
        } catch (Exception e) {
            e.printStackTrace();
            return getErrorResponse();
        }
    }

    public ApiResponse addProviderContact(int idProvider, String name, String email, String position) {

        TreeMap<String,String> map = new TreeMap<>();
        map.put("name", name);
        map.put("email", email);
        map.put("position",position);

        try {
        String link = getLink(getBase(EnumWebServices.SITE,EnumWebServices.PROVIDERCONTACT, EnumWebServices.ADD), String.valueOf(idProvider));
            Pair<String, String> completePost = new Pair<>(link, getPostValues(map));
        CustomPair<String> jsonResult =  callJson(EnumMethod.POST,activity,completePost);
        ApiResponse<ProviderContact> providerApiResponse = new ApiResponse<>(ProviderContact.class);
        if(jsonResult.first){
            providerApiResponse = populateApiResponse(providerApiResponse,jsonResult.second);
        }
        return providerApiResponse;
        } catch (Exception e) {
            e.printStackTrace();
            return getErrorResponse();
        }
    }

    public ApiResponse getContacts(int id) {
//
//        TreeMap<String,String> map = new TreeMap<>();
//        map.put("id", String.valueOf(id));


        try {
        String link = getLink(getBase(EnumWebServices.SITE,EnumWebServices.PROVIDERCONTACT, EnumWebServices.GETCONTACTS), String.valueOf(id));
//            Pair<String, String> completePost = new Pair<>(link, getPostValues(map));
        CustomPair<String> jsonResult =  callJson(EnumMethod.GET,activity,link);
        ApiResponse<ProviderContactList> providerApiResponse = new ApiResponse<>(ProviderContactList.class);
        if(jsonResult.first){
            providerApiResponse = populateApiResponse(providerApiResponse,jsonResult.second);
        }
        return providerApiResponse;
        } catch (Exception e) {
            e.printStackTrace();
            return getErrorResponse();
        }
    }

    public ApiResponse updateProviderContact(int idProvider, String name, String email, String position, int id) {

        TreeMap<String,String> map = new TreeMap<>();
        map.put("name", name);
        map.put("email", email);
        map.put("position",position);
        map.put("id", String.valueOf(id));

        try {
        String link = getLink(getBase(EnumWebServices.SITE,EnumWebServices.PROVIDERCONTACT, EnumWebServices.SET), String.valueOf(idProvider));
            Pair<String, String> completePost = new Pair<>(link, getPostValues(map));
        CustomPair<String> jsonResult =  callJson(EnumMethod.POST,activity,completePost);
        ApiResponse<ProviderContact> providerApiResponse = new ApiResponse<>(ProviderContact.class);
        if(jsonResult.first){
            providerApiResponse = populateApiResponse(providerApiResponse,jsonResult.second);
        }
        return providerApiResponse;
        } catch (Exception e) {
            e.printStackTrace();
            return getErrorResponse();
        }
    }

    public ApiResponse setProviderPhone(int idProvider, int idContact, ArrayList<Pair<String,String>> commercialValues, ArrayList<Pair<String, String>> otherPhoneValues) {

        TreeMap<String,String> map = new TreeMap<>();
        map.put("id", String.valueOf(idContact));
        map.putAll(getArrayParams("commercial",commercialValues));
        map.putAll(getArrayParams("otherphone",otherPhoneValues));

        try {
        String link = getLink(getBase(EnumWebServices.SITE,EnumWebServices.PROVIDERCONTACT, EnumWebServices.SETPHONE), String.valueOf(idProvider));
            Pair<String, String> completePost = new Pair<>(link, getPostValues(map));
        CustomPair<String> jsonResult =  callJson(EnumMethod.POST,activity,completePost);
        ApiResponse<ProviderContact> providerApiResponse = new ApiResponse<>(ProviderContact.class);
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
