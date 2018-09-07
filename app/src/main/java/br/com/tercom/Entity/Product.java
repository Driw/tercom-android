package br.com.tercom.Entity;

public class Product extends GenericEntity {
    private String name;
    private String description;
    private String utility;
    private int idProductUnit;
    private int idProductFamily;
    private int idProductGroup;
    private int idProductSubGroup;
    private int idProductSector;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUtility() {
        return utility;
    }

    public void setUtility(String utility) {
        this.utility = utility;
    }

    public int getIdProductUnit() {
        return idProductUnit;
    }

    public void setIdProductUnit(int idProductUnit) {
        this.idProductUnit = idProductUnit;
    }

    public int getIdProductFamily() {
        return idProductFamily;
    }

    public void setIdProductFamily(int idProductFamily) {
        this.idProductFamily = idProductFamily;
    }

    public int getIdProductGroup() {
        return idProductGroup;
    }

    public void setIdProductGroup(int idProductGroup) {
        this.idProductGroup = idProductGroup;
    }

    public int getIdProductSubGroup() {
        return idProductSubGroup;
    }

    public void setIdProductSubGroup(int idProductSubGroup) {
        this.idProductSubGroup = idProductSubGroup;
    }

    public int getIdProductSector() {
        return idProductSector;
    }

    public void setIdProductSector(int idProductSector) {
        this.idProductSector = idProductSector;
    }
}
