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
import android.util.TypedValue;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;
import java.util.TimeZone;

/**
 * Created by Trabalho on 6/13/2017.
 */

public class Util {

    public static final String URL_BASE = "https://www.sampaingressos.com.br/webServices/";

    public static boolean isNetworkAvailable(Activity activity) {
        ConnectivityManager connectivityManager = (ConnectivityManager) activity.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
    public  static String GeneratePrice(float valor, int type){
        float value = 0;
        if(type == 1) value = valor;
        if(type == 2) value = valor/2;
        return String.format(Locale.US,"R$%s0",String.valueOf(value).replace(".",","));
    }

    public static String GenerateName(String nome,String sobrenome){
        return String.format(Locale.US,"%s %s", nome, sobrenome);
    }

    public static String GenerateHelloPerfil(String nome){
        return String.format(Locale.US,"Olá, %s",nome);
    }

    public static String GenerateCredits(String valor){
        return String.format(Locale.US,"R$ %s",valor);
    }
    public  static String GenerateValue(float valor){
        return String.format(Locale.US,"R$%s",String.valueOf(valor).replace(".",","));
    }
    public  static String GenerateGenericString(String text1,String text2){
        return String.format(Locale.US,"%s: %s",text1, text2);
    }


    public static Integer GetYear(){
        return  Calendar.getInstance().get(Calendar.YEAR);
    }

    public static Integer GetMonth(){
        return Calendar.getInstance().get(Calendar.MONTH);
    }

    public static String GenerateBlockCardText(String cartao){
        return String.format(Locale.US,"•••• •••• •••• %s",cartao.substring(cartao.length()-4));
    }

    public static boolean SimpleCardValidator(String cartao){
        String card = cartao.replace(".","");
        return !card.trim().isEmpty() && card.length() > 12;
    }

    public static boolean CardValidator(String cartao)
    {
        cartao = cartao.replace(".","");
//        if (cartao == null || ((cartao = cartao.replace(".", "")).length() != 16 && (cartao = cartao.replace(".", "")).length() != 13
//                && (cartao = cartao.replace(".", "")).length() != 14  && (cartao = cartao.replace(".", "")).length() != 19))
//            return false;

        int soma = 0;

        for (int i = cartao.length() - 1; i >= 0; i--)
        {
            char c = cartao.charAt(i);
            int numero = Integer.parseInt(Character.toString(c));

            if (i % 2 == 1)

                soma += numero;
            else
            {
                numero *= 2;
                soma += numero > 9 ? numero - 9 : numero;
            }
        }

        return soma % 10 == 0;
    }

    public static boolean DateValidator(String data) {
        int monthValidate = Integer.parseInt(data.substring(0, 2));
        int yearValidate = Integer.parseInt(data.substring(3));
        if (!data.isEmpty()) {
            if (monthValidate < GetMonth() && yearValidate == (GetYear() % 100)) {
                return false;
            } else {
                if (monthValidate <= 12 && yearValidate >= (GetYear() % 100)) {
                    return true;
                } else {
                    return false;
                }

            }
        } else {
            return false;
        }
    }

    public static boolean emptyValidator(String value){
        return value != null && !value.trim().isEmpty();
    }

    public static boolean CVVValidator(String cvv){
        return !cvv.isEmpty() && (cvv.length() == 3 || cvv.length() == 4);
    }

    public static boolean PhoneValidator(String phone){
        return  phone != null && !phone.isEmpty() && (phone.length() == 8 || phone.length() == 9);
    }
    public static boolean DDDValidator(String ddd){
        return  ddd != null && !ddd.isEmpty() && ddd.length() == 2 ;
    }

    public static boolean EmailValidator(String email){
        return   email.contains("@") && email.length()>=7 ;
    }

    public static boolean NameValidator(String name){
        return !name.isEmpty() && name.length() >= 2;
    }

    public static String CheckBandeira(String cartao){
        if(cartao.substring(0,1).equalsIgnoreCase("4")){
            return "Visa";
        }else if(cartao.substring(0,1).equalsIgnoreCase("5")){
            return "MasterCard";
        }else{
            return "Outro";
        }
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

    public static String createNameSeats(ArrayList<String> array){
        StringBuilder stringBuilder = new StringBuilder();
        for(int i = 0; i<array.size();i++){
            stringBuilder.append(array.get(i));
            if(i == (array.size()-1)) {
                stringBuilder.append(".");
            }else{
                stringBuilder.append(",");
            }
        }
        return stringBuilder.toString();

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

    public static String floatValueTicket(float value){
        return String.format(Locale.US,"R$ %.2f",value);
    }

    public static  String simplifyArray(String[] array){
        StringBuilder stringBuilder = new StringBuilder();
        for (String value : array) {
            stringBuilder.append(value + ";\n");
        }
        return stringBuilder.toString();
    }

    public static String[] splitComments(String comments){
        return comments.split(";");
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







}
