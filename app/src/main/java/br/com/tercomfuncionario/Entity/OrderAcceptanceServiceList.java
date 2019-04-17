package br.com.tercomfuncionario.Entity;

import java.util.ArrayList;

import br.com.tercomfuncionario.Annotation.BindObject;

public class OrderAcceptanceServiceList extends GenericEntity {
    @BindObject(type = BindObject.TYPE.LIST)
    private ArrayList<OrderAcceptanceService> list;

    public ArrayList<OrderAcceptanceService> getList() {
        return list;
    }

    public void setList(ArrayList<OrderAcceptanceService> list) {
        this.list = list;
    }
}
