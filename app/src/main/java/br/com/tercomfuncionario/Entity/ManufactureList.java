package br.com.tercomfuncionario.Entity;

import java.util.ArrayList;

import br.com.tercomfuncionario.Annotation.BindObject;

public class ManufactureList extends GenericEntity {

    @BindObject(type = BindObject.TYPE.LIST)
    private ArrayList<Manufacture> list;

    public ArrayList<Manufacture> getList() {
        return list;
    }

    public void setList(ArrayList<Manufacture> list) {
        this.list = list;
    }
}
