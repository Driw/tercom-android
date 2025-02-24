package br.com.tercomfuncionario.Entity;

import java.util.ArrayList;

import br.com.tercomfuncionario.Annotation.BindObject;

public  class ProductUnitList extends GenericEntity {

    @BindObject(type = BindObject.TYPE.LIST)
    private ArrayList<ProductUnit> list;

    public ArrayList<ProductUnit> getList() {
        return list;
    }

    public void setList(ArrayList<ProductUnit> list) {
        this.list = list;
    }
}
