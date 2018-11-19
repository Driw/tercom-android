package br.com.tercom.Entity;

public class Tags extends GenericEntity{

    private String[] elements;
    private String generic;

    public String[] getElements() {
        return elements;
    }

    public void setElements(String[] elements) {
        this.elements = elements;
    }

    public String getGeneric() {
        return generic;
    }

    public void setGeneric(String generic) {
        this.generic = generic;
    }
}
