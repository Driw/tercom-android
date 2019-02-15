package br.com.tercom.Control;

import android.app.Activity;
import android.util.Pair;

import java.util.TreeMap;

import br.com.tercom.Entity.ApiResponse;
import br.com.tercom.Entity.OrderItemProductList;
import br.com.tercom.Enum.EnumMethod;
import br.com.tercom.Enum.EnumREST;
import br.com.tercom.Util.CustomPair;

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
            ApiResponse<OrderItemProductList> orderApiResponse = new ApiResponse<>(OrderItemProductList.class);
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
