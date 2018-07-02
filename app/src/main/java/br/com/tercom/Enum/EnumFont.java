package br.com.tercom.Enum;


public enum EnumFont {

    FONT_BEACHWOOD("fonts/Beachwood.otf"),
    FONT_BEACHWOORD_SANS("fonts/Beachwood_Sans.otf"),
    FONT_MONTSERRAT_BLACK("fonts/Montserrat-Black.otf"),
    FONT_MONTSERRAT_BOLD("fonts/Montserrat-Bold.otf"),
    FONT_MONTSERRAT_EXTRA_BOLD("fonts/Montserrat-ExtraBold.otf"),
    FONT_MONTSERRAT_HAIRLINE("fonts/Montserrat-Hairline.otf"),
    FONT_MONTSERRAT_LIGHT("fonts/Montserrat-Light.otf"),
    FONT_MONTSERRAT_REGULAR("fonts/Montserrat-Regular.otf"),
    FONT_MONTSERRAT_SEMIBOLD("fonts/Montserrat-SemiBold.otf"),
    FONT_MONTSERRAT_ULTRALIGHT("fonts/Montserrat-UltraLight.otf");


    public final String path;

    private EnumFont(String path){
        this.path = path;
    }


}
