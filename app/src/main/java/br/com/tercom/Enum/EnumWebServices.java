package br.com.tercom.Enum;

public enum EnumWebServices {

    //SERVICE
    PROVIDER("provider"),
    SITE("site"),




    //METHODS
    ADD("add"),
    GET("get"),
    LIST("list"),
    SEARCH("search");


    public String path;

    private EnumWebServices(String path) {
        this.path = path;
    }

}
