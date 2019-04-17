package br.com.tercomfuncionario.Enum;

public enum EnumREST {

    //SERVICE
    AVALIABLE("avaliable"),
    MANUFACTURE("manufacturer"),
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
    SERVICE("service"),
    SITE("site"),
    PRODUCTTYPE("productType"),
    SERVICEPRICE("servicePrice"),
    PERMISSION("permission"),
    LOGINTERCOM("loginTercom"),
    MANAGEPERMISSIONS("managePermissions"),
    ORDERREQUEST("orderRequest"),
    ORDERITEMPRODUCT("orderItemProduct"),
    ORDERITEMSERVICE("orderItemService"),
    ORDERQUOTE("orderQuote"),
    QUOTEDPRODUCTPRICES("quotedProductPrice"),
    QUOTEDSERVICEPRICE("quotedServicePrice"),
    ORDERACCEPTANCE("orderAcceptance"),
    ORDERQUOTEBYTERCOMEMPLOYEE("getByTercomEmployee"),




    //METHODS
    ADD("add"),
    GET("get"),
    GETALL("getAll"),
    GETCATEGORIES("getCategories"),
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
    GETSERVICE("getService"),
    LOGIN("login"),
    VERIFY("verify"),
    LOGOUT("logout"),
    QUOTE("quote"),
    QUOTED("quoted"),
    PRICES("prices"),

    //FILTERS
    FAMILY("family"),
    FANTASYNAME("fantasyName"),
    GROUP("group"),
    NAME("name"),
    SUBGROUP("subgroup"),
    SECTOR("sector"),
    TERCOM("tercom");


    public String path;

    private EnumREST(String path) {
        this.path = path;
    }

}
