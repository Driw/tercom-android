package br.com.tercom.Control;

import android.app.Activity;
import android.util.Pair;

import com.google.gson.Gson;


import org.json.JSONArray;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Locale;

import br.com.tercom.Enum.BaseUrl;
import br.com.tercom.Enum.EnumMethod;
import br.com.tercom.Util.HttpUtil;


/**
 * Created by Trabalho on 2/5/2018.
 */

public abstract class GenericControl {

    private final static int WEBSERVICE_FAIL = 0;
    private final static  int WEBSERVICE_OK = 1;


    private final  String URL_BASE = BaseUrl.URL_DEV.path;

    //TODO(Necessário adaptar ao modelo novo do projeto e necessário documentar usando a própria documentação do android.)


//
//    protected  String[] getValuesBase() {
//        return new String[] {USER_STATIC.getEmail(),USER_STATIC.getSenha()};
//    }


    /*
        Usado para GET e POST. Define a base da url e o tipo do webservice que será usado.
     */

    protected  String getBase(String type)
    {

        return String.format(Locale.US,"%s%s",URL_BASE, type);
    }

    /*
        Usado somente para GET, cria a url completa que será usada, sendo que é formada pela base, os parâmetros do usuário e por fim algum tipo, se for necessário.
     */

    protected  String getLink(String base, String params){
        return String.format(Locale.US,"%s%s",base,params);
    }


     /*
        Usado em GET e POST, ele gera os parametros de usuário, baseado no formato (valor_valor) e retorna uma string completa com todos valores do array.
     */

    protected  String generateParams(String[] values,String separator) throws UnsupportedEncodingException {

        StringBuilder result = new StringBuilder();

        for (int i= 0;i<values.length;i++) {

            //result.append(URLEncoder.encode(values[i], "UTF-8"));
            result.append(values[i]);

            if(i != values.length-1)
                result.append(separator);

        }

        return result.toString();
    }

//    protected  String generateArrayCompraItem(ArrayList<CompraItem> values) throws UnsupportedEncodingException {
//
//        StringBuilder result = new StringBuilder();
//
//        for (int i= 0;i<values.size();i++) {
//            if (values.get(i).getQTD()>0) {
//                result.append(String.format(Locale.US, "%s[%s]=%d", values.get(i).getType().type, URLEncoder.encode(values.get(i).getNome(), "UTF-8"), values.get(i).getQTD()));
//                if (i != values.size() - 1)
//                    result.append("&");
//            }
//        }
//
//        return result.toString();
//    }
//

    protected  String generateArrayCadeiras(ArrayList<String> values) throws UnsupportedEncodingException {

        StringBuilder result = new StringBuilder();

        for (int i= 0;i<values.size();i++) {
                result.append(String.format(Locale.US, "cadeira[%s]=1",values.get(i)));
                if (i != values.size() - 1)
                    result.append("&");
            }

        return result.toString();
    }

    /*
        Usado em GET e POST, ele gera os parametros de usuário, formato (key=valor) que é definido previamente e enviado no array de valores.Retorna uma string completa com todos valores do array.
     */

    protected  String generateParamsWithKey(String[] values) throws UnsupportedEncodingException {

        StringBuilder result = new StringBuilder();

        for (int i= 0;i<values.length;i++) {
            result.append(values[i]);
            if(i != values.length-1) result.append("&");
        }

        return result.toString();
    }

    protected  String setPage(int page){
        return String.format(Locale.US,"pagina=%d",page);
    }


    /*
        * Cria uma conexão com o webservice através de GET para receber um json, parâmetros usados:
        * Activity: usado para a verificações de internet dentro do método de conexão;
        * Link: a url completa usada;
        * Chave: O callJsonArray virá em um formato com o status e um array, a chave será usada para pegar esse JsonArray;
        * Retorno: retornará um pair com o status (boolean), caso seja true, retornará o array, caso false retornará a mensagem que será tratada na activity previamente selecionada.
     */

            protected Pair callJsonArray(EnumMethod method, Activity activity, Object link){
                try{
                    String jsonCalled = "";
                    if(method == EnumMethod.GET) {
                        jsonCalled = new HttpUtil().httpConnectionGET((String)link, activity);
                    }
                    if(method == EnumMethod.POST) {
                        jsonCalled = new HttpUtil().httpConnectionPOST((Pair)link, activity);
                    }
                    if( !jsonCalled.trim().isEmpty()) {
                        if (jsonCalled.startsWith("[")) {
                            JSONArray jsonArray = new JSONArray(jsonCalled);
                            if(jsonArray.length()!= 0) {
                                return new Pair<>(true,jsonArray.toString());
                            }else{
                                return new Pair<>(false,"Não há resultados");
                            }
                        } else {
                            JSONObject jsonObject = new JSONObject(jsonCalled);
                            if (jsonObject.getInt("status") == WEBSERVICE_FAIL) {
                                return new Pair<>(false, jsonObject.get("msg"));
                            }
                        }
                    } throw  new Exception("Erro ao fazer o download");
        }catch (Exception e){
            e.printStackTrace();
            return new Pair<>(false,"Erro! Não foi possível fazer o download dos dados.");
        }
    }

    /*
    * Cria uma conexão com o webservice através de GET para receber um json, parâmetros usados:
    * Activity: usado para a verificações de internet dentro do método de conexão;
    * Link: é a url que será usada para fazer o GET.
    * Retorno: retornará um JSONObject;
    */

    protected  Pair callJSONObjetct(Activity activity, String link){
        try{
            JSONObject jsonObject = new JSONObject(new HttpUtil().httpConnectionGET(link,activity));
            if(jsonObject.getInt("status") == WEBSERVICE_OK) {
                return new Pair<>(true,jsonObject.toString());
            }else{
                return new Pair<>(false,jsonObject.get("msg"));
            }
        }catch (Exception e){
            e.printStackTrace();
            return new Pair<>(false,"Erro! Não foi possível fazer o download dos dados.");
        }
    }

    /*
    * Cria uma conexão com o webservice através de GET para receber um json, parâmetros usados:
    * Activity: usado para a verificações de internet dentro do método de conexão;
    * Link: é a url que será usada para fazer o GET.
    * Retorno: retornará um JSONObject;
    */

    protected  Pair callJSONObjetctWithoutStatus(Activity activity, String link){

        try{
            JSONObject jsonObject = new JSONObject(new HttpUtil().httpConnectionGET(link,activity));
            if(jsonObject.keys().hasNext()) {
                return new Pair<>(true,jsonObject.toString());
            }else{
                return new Pair<>(false,jsonObject.get("msg"));
            }
        }catch (Exception e){
            e.printStackTrace();
            return new Pair<>(false,"Erro! Não foi possível fazer o download dos dados.");
        }
    }

    /*
        Este método recebe uma string de json e o transforma em um objeto da entidade selecionada.
        Retorno: Um Object que sofrerá um cast ao voltar para o controle onde foi chamado. (O cast deverá ser igual ao da classe selecionada)
     */

    protected <T>  T getItem(String json, Class<T> selectedClass){
        return new Gson().fromJson(json,selectedClass);
    }


    /*
        Este método recebe a chave e seu resultado, montado uma das variáveis que será enviada nas conexões.
        Retorno: Uma String montada com os parâmetros.
     */

    protected   String createField(String key, String result){
        return String.format(Locale.US,"%s=%s",key,result);
    }


}
