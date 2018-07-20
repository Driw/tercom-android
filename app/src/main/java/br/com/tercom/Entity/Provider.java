package br.com.tercom.Entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "Provider")
public class Provider
{
    @PrimaryKey(autoGenerate = true)
    private int id;
    
    @ColumnInfo(name = "cnpj")
    private String cnpj;
    
    @ColumnInfo(name = "nomeFantasia")
    private String nomeFantasia;
    
    @ColumnInfo(name = "site")
    private String site;
    
    @ColumnInfo(name = "nomeRepresentante")
    private String nomeRepresentante;

    //TODO(Fix for Phone use)
//    @ColumnInfo(name = "telefone")
//    private Phone telefone;
//
//    @ColumnInfo(name = "celular")
//    private Phone celular;
}
