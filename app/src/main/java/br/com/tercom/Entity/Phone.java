package br.com.tercom.Entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "Phone")
public class Phone extends GenericEntity
{
    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "ddd")
    private  Integer ddd;

    @ColumnInfo(name = "numero")
    private String number;

    @ColumnInfo(name = "tipo")
    private String type;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDdd() {
        return ddd;
    }

    public void setDdd(int ddd) {
        this.ddd = ddd;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }


    public void setDdd(Integer ddd) {
        this.ddd = ddd;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

}