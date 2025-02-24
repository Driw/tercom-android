package br.com.tercomfuncionario.Entity;

import java.util.ArrayList;

import br.com.tercomfuncionario.Annotation.BindObject;

public class OrderItemServiceList extends GenericEntity {

    @BindObject(type = BindObject.TYPE.LIST)
    ArrayList<OrderItemService> list;

    public ArrayList<OrderItemService> getList() {
        return list;
    }

    public void setList(ArrayList<OrderItemService> list) {
        this.list = list;
    }
}
