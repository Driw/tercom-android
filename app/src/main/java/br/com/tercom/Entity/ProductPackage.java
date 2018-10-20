package br.com.tercom.Entity;

public class ProductPackage extends GenericEntity {
    private int id;
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        if(name == null)
            return "pacotes";
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
