package br.com.tercom.Enum;

public enum EnumWebServices {

    //SERVICE
    PROVIDER("provider"),
    PROVIDERCONTACT("providerContact"),
    SITE("site"),




    //METHODS
    ADD("add"),
    GET("get"),
    GETCONTACTS("getContacts"),
    LIST("list"),
    SEARCH("search"),
    SET("set"),
    SETPHONE("setPhones");


    public String path;

    private EnumWebServices(String path) {
        this.path = path;
    }

}
