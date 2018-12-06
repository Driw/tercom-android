package br.com.tercom.Enum;

public enum BaseUrl {
    URL_DEV("http://tercom.diverproject.org/api"),
    URL_REAL(""),
    URL_IMPROVISADA("http://94bc63d7.ngrok.io/");


    public final String path;


    private BaseUrl(String path){
        this.path = path;
    }
}
