package br.com.tercomfuncionario.Entity;

import java.util.ArrayList;

import br.com.tercomfuncionario.Annotation.BindObject;

public class OrderQuoteList extends GenericEntity {

    @BindObject(type = BindObject.TYPE.LIST)
    ArrayList<OrderQuote> list;

    public ArrayList<OrderQuote> getList() {
        return list;
    }

    public void setList(ArrayList<OrderQuote> list) {
        this.list = list;
    }
}
