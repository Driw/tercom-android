package br.com.tercom.Entity;

import java.util.ArrayList;

import br.com.tercom.Annotation.BindObject;

public class ProductFamily extends ProductCategory {

    @BindObject(type = BindObject.TYPE.LIST)
    private ArrayList<ProductGroup> productGroups;

    public ArrayList<ProductGroup> getProductGroups() {
        return productGroups;
    }

    public void setProductGroups(ArrayList<ProductGroup> productGroups) {
        this.productGroups = productGroups;
    }
}
