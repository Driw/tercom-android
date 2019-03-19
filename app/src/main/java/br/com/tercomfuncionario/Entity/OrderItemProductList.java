package br.com.tercomfuncionario.Entity;

import java.util.ArrayList;

import br.com.tercomfuncionario.Annotation.BindObject;

public class OrderItemProductList extends GenericEntity {

    @BindObject(type = BindObject.TYPE.LIST)
    ArrayList<OrderItemProduct> list;

    public ArrayList<OrderItemProduct> getList() {
        return list;
    }

    public void setList(ArrayList<OrderItemProduct> list) {
        this.list = list;
    }
}
