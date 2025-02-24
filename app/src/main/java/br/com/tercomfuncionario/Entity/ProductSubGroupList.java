package br.com.tercomfuncionario.Entity;

import java.util.ArrayList;

import br.com.tercomfuncionario.Annotation.BindObject;

public  class ProductSubGroupList extends GenericEntity {

    @BindObject(type = BindObject.TYPE.LIST)
    private ArrayList<ProductSubGroup> list;

    public ArrayList<ProductSubGroup> getList() {
        return list;
    }

    public void setList(ArrayList<ProductSubGroup> list) {
        this.list = list;
    }
}
