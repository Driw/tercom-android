package br.com.tercom.Entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "ProviderContact")
public class ProviderContact 
{
    @PrimaryKey(autoGenerate = true)
    private int id;
    
    @ColumnInfo(name = "nome")
    private String nome ;
    
    @ColumnInfo(name = "cargo")
    private String cargo;
    
    @ColumnInfo(name = "email")
    private String email;


    //TODO(Fix for Phone use)
//    @ColumnInfo(name = "telefone")
//    private Phone telefone;
//
//    @ColumnInfo(name = "celular")
//    private Phone celular;
}
