package br.com.tercomfuncionario.Entity;

import java.util.ArrayList;

import br.com.tercomfuncionario.Annotation.BindObject;

public class ProviderList extends GenericEntity {

    @BindObject(type = BindObject.TYPE.LIST)
    private ArrayList<Provider> list;

    public ArrayList<Provider> getList() {
        return list;
    }

    public void setProviders(ArrayList<Provider> providers) {
        this.list = providers;
    }
}
