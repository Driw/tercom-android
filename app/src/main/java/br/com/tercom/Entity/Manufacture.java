package br.com.tercom.Entity;

import java.util.ArrayList;

public class Manufacture extends GenericEntity {

    private int id;
    private String fantasyName;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFantasyName() {
        return fantasyName;
    }

    public void setFantasyName(String fantasyName) {
        this.fantasyName = fantasyName;
    }
}
