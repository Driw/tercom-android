package br.com.tercom.Enum;

public enum EnumREST {

    //SERVICE
    MANUFACTURE("manufacture"),
    PRODUCT("product"),
    PRODUCTFAMILY("productFamily"),
    PRODUCTGROUP("productGroup"),
    PRODUCTPACKAGE("productPackage"),
    PRODUCTSECTOR("productSector"),
    PRODUCTSUBGROUP("productSubGroup"),
    PRODUCTUNIT("productUnit"),
    PROVIDER("provider"),
    PROVIDERCONTACT("providerContact"),
    PRODUCTVALUE("productPrice"),
    SITE("site"),
    PRODUCTTYPE("productType"),





    //METHODS
    ADD("add"),
    GET("get"),
    GETALL("getAll"),
    GETCONTACTS("getContacts"),
    GETGROUPS("getGroups"),
    GETPRODUCTSUBGROUP("getSubGroups"),
    GETPRODUCTSUBSECTOR("getSubSectors"),
    GETPRODUCTSECTOR("getSector"),
    LIST("list"),
    REMOVE("remove"),
    SEARCH("search"),
    SET("set"),
    SETPHONE("setPhones"),

    //FILTERS
    FAMILY("family"),
    FANTASYNAME("fantasyName"),
    GROUP("group"),
    NAME("name"),
    SUBGROUP("subgroup"),
    SECTOR("sector");


    public String path;

    private EnumREST(String path) {
        this.path = path;
    }

}
