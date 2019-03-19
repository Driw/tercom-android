package br.com.tercomfuncionario.Entity;

import java.util.ArrayList;

import br.com.tercomfuncionario.Annotation.BindObject;

public class PackageList extends GenericEntity {
    @BindObject(type = BindObject.TYPE.LIST)
    private ArrayList<ProductPackage> list;

    public ArrayList<ProductPackage> getList() {
        return list;
    }

    public void setList(ArrayList<ProductPackage> list) {
        this.list = list;
    }
}
