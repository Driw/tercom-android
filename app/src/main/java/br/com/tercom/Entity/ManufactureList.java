package br.com.tercom.Entity;

import java.util.ArrayList;

public class ManufactureList extends GenericEntity {

    private ArrayList<Manufacture> list;

    public ArrayList<Manufacture> getList() {
        return list;
    }

    public void setList(ArrayList<Manufacture> list) {
        this.list = list;
    }
}
