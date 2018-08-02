package br.com.tercom.Entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

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

    @BindObject(value =  "commercial",type = BindObject.TYPE.OBJECT)
    private Phone commercial;

    @BindObject(value = "otherphone")
    private Phone otherphone;

    private boolean inactive;



    //TODO(Fix for Phone use)
//    @ColumnInfo(name = "telefone")
//    private Phone telefone;
//
//    @ColumnInfo(name = "celular")
//    private Phone celular;
}
