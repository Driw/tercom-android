package br.com.tercom.Entity;

import java.util.ArrayList;

import br.com.tercom.Annotation.BindObject;

public class  ProductSubGroup extends ProductCategory{

    private int idProductGroup;
    @BindObject(type = BindObject.TYPE.LIST)
    private ArrayList<ProductSector>  productSectores;

    public int getIdProductGroup() {
        return idProductGroup;
    }

    public void setIdProductGroup(int idProductGroup) {
        this.idProductGroup = idProductGroup;
    }

    public ArrayList<ProductSector> getProductSectores() {
        return productSectores;
    }

    public void setProductSectores(ArrayList<ProductSector> productSectores) {
        this.productSectores = productSectores;
    }
}
