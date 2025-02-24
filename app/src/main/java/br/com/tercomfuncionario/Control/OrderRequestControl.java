package br.com.tercomfuncionario.Control;

import android.app.Activity;
import android.util.Pair;

import java.util.TreeMap;

import br.com.tercomfuncionario.Entity.ApiResponse;
import br.com.tercomfuncionario.Entity.OrderQuote;
import br.com.tercomfuncionario.Entity.OrderQuoteList;
import br.com.tercomfuncionario.Entity.OrderRequest;
import br.com.tercomfuncionario.Entity.OrderRequestList;
import br.com.tercomfuncionario.Enum.EnumMethod;
import br.com.tercomfuncionario.Enum.EnumREST;
import br.com.tercomfuncionario.Util.CustomPair;

public class OrderRequestControl extends GenericControl {

    private Activity activity;
    public OrderRequestControl(Activity activity) { this.activity = activity; }

    public ApiResponse add(double budget, String expiration){
        TreeMap<String, String> map = new TreeMap<>();
        map.put("budget", String.valueOf(budget));
        map.put("expiration", expiration);
        try{
            String link = getBase(EnumREST.SITE, EnumREST.ORDERREQUEST, EnumREST.ADD);
            Pair<String, String> completePost = new Pair<>(link, getPostValues(map));
            CustomPair<String> jsonResult =  callJson(EnumMethod.POST,activity,completePost);
            ApiResponse<OrderRequest> providerApiResponse = new ApiResponse<>(OrderRequest.class);
            if(jsonResult.first){
                providerApiResponse = populateApiResponse(providerApiResponse,jsonResult.second);
            }
            return providerApiResponse;
        } catch (Exception e) {
            e.printStackTrace();
            return getErrorResponse();
        }

    }


    public static final int TODOS = 0;
    public static final int CANCELADO_CLIENTE = 1;
    public static final int CANCELADO_TERCOM = 2;
    public static final int CANCELADO = 3;
    public static final int FILA = 4;
    public static final int EMCOTACAO = 5;
    public static final int COTADOS = 6;

    public ApiResponse getAll(int mode){
        try{
            TreeMap<String, String> map = new TreeMap<>();
            map.put("mode", String.valueOf(mode));
            String link = getLink(getBase(EnumREST.SITE, EnumREST.ORDERREQUEST, EnumREST.GETALL), String.valueOf(mode));
            Pair<String, String> completePost = new Pair<>(link, getPostValues(map));
            CustomPair<String> jsonResult =  callJson(EnumMethod.POST, activity, completePost);
            ApiResponse<OrderRequestList> orderApiResponse = new ApiResponse<>(OrderRequestList.class);
            if(jsonResult.first){
                orderApiResponse = populateApiResponse(orderApiResponse,jsonResult.second);
            }
            return orderApiResponse;
        } catch (Exception e) {
            e.printStackTrace();
            return getErrorResponse();
        }

    }

    public ApiResponse getAllQuoted(){
        try{
            TreeMap<String, String> map = new TreeMap<>();
            String link = getBase(EnumREST.SITE, EnumREST.ORDERQUOTE, EnumREST.ORDERQUOTEBYTERCOMEMPLOYEE);
            Pair<String, String> completePost = new Pair<>(link, getPostValues(map));
            CustomPair<String> jsonResult =  callJson(EnumMethod.POST, activity, completePost);
            ApiResponse<OrderQuoteList> orderApiResponse = new ApiResponse<>(OrderQuoteList.class);
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
