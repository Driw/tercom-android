package br.com.tercom.Util;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.database.Cursor;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.text.format.DateFormat;
import android.util.Log;
import android.util.Pair;
import android.util.TypedValue;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;
import java.util.TimeZone;
import java.util.TreeMap;

/**
 * Created by Trabalho on 6/13/2017.
 */

public class Util {


    public static boolean isNetworkAvailable(Activity activity) {
        ConnectivityManager connectivityManager = (ConnectivityManager) activity.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    public static Integer GetYear(){
        return  Calendar.getInstance().get(Calendar.YEAR);
    }

    public static Integer GetMonth(){
        return Calendar.getInstance().get(Calendar.MONTH);
    }

    public static void toastFinish(Activity activity, String msg){
        if(!isNetworkAvailable(activity)) {
            Toast.makeText(activity, "Você não está conectado à internet.", Toast.LENGTH_SHORT).show();
            activity.finish();
        }else{
            Toast.makeText(activity, msg, Toast.LENGTH_SHORT).show();
            activity.finish();
        }
    }
    public static void toast(Activity activity, String msg){
        if(!isNetworkAvailable(activity)) {
            Toast.makeText(activity, "Você não está conectado à internet.", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(activity, msg, Toast.LENGTH_SHORT).show();
        }
    }

    public static void print(String msg){
        Log.d("PRINT",msg);
    }

    public static void printTAG(String tag,String msg){
        Log.d(tag,msg);
    }

    public static int convertDpToPixels(float dp, Context context){
        Resources resources = context.getResources();
        return (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP,
                dp,
                resources.getDisplayMetrics()
        );
    }


    public static void OpenDialDialog(String[] dialArray, final Activity activity){
        AlertDialog.Builder builderSingle = new AlertDialog.Builder(activity);
        builderSingle.setTitle("Contatos online no momento:");
        builderSingle.setCancelable(false);
        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(activity, android.R.layout.select_dialog_singlechoice);

        for(String fone: dialArray){
            arrayAdapter.add(fone);
        }

        builderSingle.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        builderSingle.setAdapter(arrayAdapter, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse(String.format(Locale.US,"tel: %s",arrayAdapter.getItem(which))));

                activity.startActivity(intent);
            }
        });
        builderSingle.show();
    }

    public static String concatenaNumero(String[] telefones){
        if(telefones.length==1){
            return telefones[1];
        }else {
            StringBuilder stringBuilder = new StringBuilder();
            for (int i = 0; i < telefones.length; i++) {
                stringBuilder.append(telefones[i]);
                if (i != (telefones.length - 1))
                    stringBuilder.append("/ ");
            }
            return stringBuilder.toString();
        }
    }



    public static String getTimeStampFormated(){
        Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("GMT-3"));
        cal.setTimeInMillis(System.currentTimeMillis());
        return DateFormat.format("yyyyMMddHHmm", cal).toString();
    }

    public static <T>  ArrayList<T> toArrayList(T[] values){
        ArrayList<T> list = new ArrayList<>();
        for(T item: values){
            list.add(item);
        }
        return list;
    }

    public static TreeMap<String,String> toTreeMap(ArrayList<Pair<String,String>> values){
        TreeMap<String, String> map = new TreeMap<>();
        for (Pair<String,String> value: values){
            map.put(value.first,value.second);
        }
        return map;
    }



    public static String toUpperCaseFirst(String value){
        return String.format(Locale.US,"%s%s",value.substring(0,1).toUpperCase(),value.substring(1));
    }
    
    public static String jsonTeste = "{" +
            "\"class\": \"tercom\\\\entities\\\\Provider\"," +
            "\"attributes\": {" +
            "\"id\": 1," +
            "\"cnpj\": \"43299544000109\"," +
            "\"companyName\": \"Samuel e Benedito Assessoria Jurídica ME (CN)\"," +
            "\"fantasyName\": \"Samuel e Benedito Assessoria Jurídica ME\"," +
            "\"spokesman\": \"Elza Caroline Moreira\"," +
            "\"site\": \"www.samuelebeneditoassessoriajuridicame.com.br\"," +
            "\"commercial\": {" +
            "\"class\": \"tercom\\\\entities\\\\Phone\"," +
            "\"attributes\": {" +
            "\"id\": 1," +
            "\"ddd\": null," +
            "\"number\": null," +
            "\"type\": \"celular\"" +
            "}" +
            "}," +
            "\"otherphone\": {" +
            "\"class\": \"tercom\\\\entities\\\\Phone\"," +
            "\"attributes\": {" +
            "\"id\": 2," +
            "\"ddd\": null," +
            "\"number\": null," +
            "\"type\": \"celular\"" +
            "}" +
            "}," +
            "\"inactive\": null," +
            "\"contacts\": {" +
            "\"class\": \"tercom\\\\entities\\\\lists\\\\ProviderContacts\"," +
            "\"attributes\": {" +
            "\"generic\": \"tercom\\\\entities\\\\ProviderContact\"," +
            "\"elements\": [" +
            "{" +
            "\"class\": \"tercom\\\\entities\\\\ProviderContact\"," +
            "\"attributes\": {" +
            "\"id\": 1," +
            "\"name\": \"Luís Ian Teixeira\"," +
            "\"position\": \"Analista\"," +
            "\"email\": \"luis-ian@sfranconsultoria.com.br\"," +
            "\"commercial\": {" +
            "\"class\": \"tercom\\\\entities\\\\Phone\"," +
            "\"attributes\": {" +
            "\"id\": 0," +
            "\"ddd\": null," +
            "\"number\": null," +
            "\"type\": \"celular\"" +
            "}" +
            "}," +
            "\"otherphone\": {" +
            "\"class\": \"tercom\\\\entities\\\\Phone\"," +
            "\"attributes\": {" +
            "\"id\": 0," +
            "\"ddd\": null," +
            "\"number\": null," +
            "\"type\": \"celular\"" +
            "}" +
            "}" +
            "}" +
            "}," +
            "{" +
            "\"class\": \"tercom\\\\entities\\\\ProviderContact\"," +
            "\"attributes\": {" +
            "\"id\": 2," +
            "\"name\": \"Marcelo Henrique Nunes\"," +
            "\"position\": \"Analista\"," +
            "\"email\": \"marcelo_henrique@ozzape.com\"," +
            "\"commercial\": {" +
            "\"class\": \"tercom\\\\entities\\\\Phone\"," +
            "\"attributes\": {" +
            "\"id\": 0," +
            "\"ddd\": null," +
            "\"number\": null," +
            "\"type\": \"celular\"" +
            "}" +
            "}," +
            "\"otherphone\": {" +
            "\"class\": \"tercom\\\\entities\\\\Phone\"," +
            "\"attributes\": {" +
            "\"id\": 0," +
            "\"ddd\": null," +
            "\"number\": null," +
            "\"type\": \"celular\"" +
            "}" +
            "}" +
            "}" +
            "}" +
            "]" +
            "}" +
            "}" +
            "}" +
            "}";




}
