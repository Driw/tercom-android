package br.com.tercomfuncionario.Entity;

import br.com.tercomfuncionario.Annotation.BindObject;

public class Category extends GenericEntity {

    @BindObject
    private ProductFamily family;
    @BindObject
    private ProductGroup group;
    @BindObject
    private ProductSubGroup subgroup;
    @BindObject
    private ProductSector sector;


    public ProductFamily getFamily() {
        return family;
    }

    public ProductGroup getGroup() {
        return group;
    }

    public ProductSubGroup getSubgroup() {
        return subgroup;
    }

    public ProductSector getSector() {
        return sector;
    }
}
