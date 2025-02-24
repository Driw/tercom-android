package br.com.tercomfuncionario.Entity;

import java.util.ArrayList;

import br.com.tercomfuncionario.Annotation.BindObject;

public class PriceList extends GenericEntity {

    @BindObject(type = BindObject.TYPE.LIST)
    private ArrayList<Price> list;

    public ArrayList<Price> getList() {
        return list;
    }

    public void setList(ArrayList<Price> list) {
        this.list = list;
    }
}
