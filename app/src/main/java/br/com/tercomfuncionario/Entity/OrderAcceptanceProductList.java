package br.com.tercomfuncionario.Entity;

import java.util.ArrayList;

import br.com.tercomfuncionario.Annotation.BindObject;

public class OrderAcceptanceProductList extends GenericEntity {
    @BindObject(type = BindObject.TYPE.LIST)
    private ArrayList<OrderAcceptanceProduct> list;

    public ArrayList<OrderAcceptanceProduct> getList() {
        return list;
    }

    public void setList(ArrayList<OrderAcceptanceProduct> list) {
        this.list = list;
    }
}
