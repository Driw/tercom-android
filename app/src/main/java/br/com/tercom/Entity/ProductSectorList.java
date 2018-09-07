package br.com.tercom.Entity;

import java.util.ArrayList;

import br.com.tercom.Annotation.BindObject;

public class ProductSectorList extends GenericEntity{

    @BindObject(type = BindObject.TYPE.LIST)
    private ArrayList<ProductSector>  productSectores;

    public ArrayList<ProductSector> getProductSectores() {
        return productSectores;
    }

    public void setProductSectores(ArrayList<ProductSector> productSectores) {
        this.productSectores = productSectores;
    }
}
