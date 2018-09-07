package br.com.tercom.Entity;

import java.util.ArrayList;

import br.com.tercom.Annotation.BindObject;

public class ProductGroup extends ProductCategory {

    private int idProductFamily;
    @BindObject(type = BindObject.TYPE.LIST)
    private ArrayList<ProductSubGroup>  productSubGroups;

    public int getIdProductFamily() {
        return idProductFamily;
    }

    public void setIdProductFamily(int idProductFamily) {
        this.idProductFamily = idProductFamily;
    }

    public ArrayList<ProductSubGroup> getProductSubGroups() {
        return productSubGroups;
    }

    public void setProductSubGroups(ArrayList<ProductSubGroup> productSubGroups) {
        this.productSubGroups = productSubGroups;
    }
}
