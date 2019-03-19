package br.com.tercomfuncionario.Application;

import android.app.Application;

import br.com.tercomfuncionario.Entity.LoginTercom;


public class AppTercom extends Application {
    private static AppTercom context;
    public static LoginTercom USER_STATIC;
    public static final String appVersion = "0.0.56";

    public static AppTercom getContext() {
        return context;
    }



}

