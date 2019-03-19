package br.com.tercomfuncionario.Entity;

import java.util.ArrayList;

public class Tags extends GenericEntity{

    private ArrayList<String> list;
    private String generic;

    public ArrayList<String> getList() {
        return list;
    }

    public void setList(ArrayList<String> list) {
        this.list = list;
    }

    public String getGeneric() {
        return generic;
    }

    public void setGeneric(String generic) {
        this.generic = generic;
    }
}
