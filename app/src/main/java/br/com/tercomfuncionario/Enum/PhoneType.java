package br.com.tercomfuncionario.Enum;

public enum PhoneType {
    CELLPHONE("cellphone"),
    COMMERCIAL("commercial"),
    RESIDENTIAL("residential");

    public String type;

    private PhoneType(String type) {
        this.type = type;
    }
}
