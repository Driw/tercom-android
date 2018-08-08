package br.com.tercom.Enum;

public enum EnumWebServices {

    //CLASS
    PROVIDER("provider"),
    SITE("site"),




    //METHODS
    GET("get");


    public String path;

    private EnumWebServices(String path) {
        this.path = path;
    }

}
