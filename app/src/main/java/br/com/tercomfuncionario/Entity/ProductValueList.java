package br.com.tercomfuncionario.Entity;

import java.util.ArrayList;

import br.com.tercomfuncionario.Annotation.BindObject;

public class ProductValueList extends GenericEntity {
    @BindObject(type = BindObject.TYPE.LIST)
    private ArrayList<ProductValue> list;

    public ArrayList<ProductValue> getList() {
        return list;
    }

    public void setList(ArrayList<ProductValue> list) {
        this.list = list;
    }
}
