package br.com.tercomfuncionario.Control;

import android.app.Activity;
import android.util.Pair;

import java.util.TreeMap;

import br.com.tercomfuncionario.Entity.ApiResponse;
import br.com.tercomfuncionario.Entity.OrderQuote;
import br.com.tercomfuncionario.Enum.EnumMethod;
import br.com.tercomfuncionario.Enum.EnumREST;
import br.com.tercomfuncionario.Util.CustomPair;

public class OrderQuoteControl extends GenericControl {

    private Activity activity;

    public OrderQuoteControl(Activity activity) { this.activity = activity; }

    public ApiResponse quote(int idOrderRequest){
        try{
            TreeMap<String, String> map = new TreeMap<>();
            map.put("idOrderRequest", String.valueOf(idOrderRequest));
            String link = getLink(getBase(EnumREST.SITE, EnumREST.ORDERQUOTE, EnumREST.QUOTE), String.valueOf(idOrderRequest));
            Pair<String, String> completePost = new Pair<>(link, getPostValues(map));
            CustomPair<String> jsonResult =  callJson(EnumMethod.POST,activity,completePost);
            ApiResponse<OrderQuote> orderApiResponse = new ApiResponse<>(OrderQuote.class);
            if(jsonResult.first){
                orderApiResponse = populateApiResponse(orderApiResponse,jsonResult.second);
            }
            return orderApiResponse;
        } catch (Exception e) {
            e.printStackTrace();
            return getErrorResponse();
        }
    }

    public ApiResponse quoted(int idOrderQuote){
        try{
            TreeMap<String, String> map = new TreeMap<>();
            map.put("idOrderRequest", String.valueOf(idOrderQuote));
            String link = getLink(getBase(EnumREST.SITE, EnumREST.ORDERQUOTE, EnumREST.QUOTED), String.valueOf(idOrderQuote));
            Pair<String, String> completePost = new Pair<>(link, getPostValues(map));
            CustomPair<String> jsonResult =  callJson(EnumMethod.POST,activity,completePost);
            ApiResponse<OrderQuote> orderApiResponse = new ApiResponse<>(OrderQuote.class);
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
