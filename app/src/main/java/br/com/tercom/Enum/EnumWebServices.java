package br.com.tercom.Enum;

public enum EnumWebServices {

    FORNECEDORES("");

    public String path;

    private EnumWebServices(String path) {
        this.path = path;
    }

}
