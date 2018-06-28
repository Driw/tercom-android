package br.com.tercom.Application;

import android.app.Application;
import android.arch.persistence.room.Room;

import br.com.tercom.DataBase.AppDataBase;


public class AppTercom extends Application {
    private static AppTercom context;
    private static AppDataBase dataBase;

    static AppDataBase getDataBase()
    {
        if(dataBase != null)
        {
            dataBase = Room.databaseBuilder(context, AppDataBase.class, "DataBaseName")
            .allowMainThreadQueries()
            .build();
        }
        return dataBase;
    }

    public AppTercom()
    {
        context = this;
    }

    public static AppTercom getContext() {
        return context;
    }

}
