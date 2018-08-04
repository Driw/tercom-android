package br.com.tercom.Entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import java.util.ArrayList;

import br.com.tercom.Annotation.BindObject;

@Entity(tableName = "Provider")
public class Provider extends GenericEntity
{

    @PrimaryKey(autoGenerate = true)
    private int id;
    
    @ColumnInfo(name = "cnpj")
    private String cnpj;

    @ColumnInfo(name = "companyName")
    private String companyName;

    @ColumnInfo(name = "fantasyName")
    private String fantasyName;

    @ColumnInfo(name = "site")
    private String site;
    
    @ColumnInfo(name = "spokesman")
    private String spokesman;

    @BindObject()
    private Phone commercial;

    @BindObject()
    private Phone otherphone;

    private boolean inactive;

    @BindObject(type = BindObject.TYPE.LIST)
    private ArrayList<ProviderContact> contacts;



    //TODO(Fix for Phone use)
//    @ColumnInfo(name = "telefone")
//    private Phone telefone;
//
//    @ColumnInfo(name = "celular")
//    private Phone celular;
}
