package br.com.tercomfuncionario.Util;

import android.app.Activity;
import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.Window;

import br.com.tercomfuncionario.R;

public class DialogLoading {


    private static Activity activity;
    private static Dialog loadingDialog;


    public DialogLoading(Activity activity){
        this.activity = activity;
    }


    public void init(){
        try {

            loadingDialog = new Dialog(activity);
            loadingDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            loadingDialog.setCancelable(false);
            loadingDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            loadingDialog.setContentView(R.layout.dialog_loading);
            loadingDialog.show();

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void dismissD(){
        loadingDialog.dismiss();
    }

    public Dialog getConfirmDialog() {
        return loadingDialog;
    }

}
