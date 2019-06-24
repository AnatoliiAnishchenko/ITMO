package client.localization;

import java.util.Locale;
import java.util.ResourceBundle;

public class LocalizationManager {
    //константы для локалей
    public static final String LANGUAGE_RU = "ru";
    public static final String COUNTRY_RU = "RU";
    public static final String LANGUAGE_UK = "uk";
    public static final String COUNTRY_UA = "UA";
    public static final String LANGUAGE_NL = "nl";
    public static final String COUNTRY_NL = "NL";
    public static final String LANGUAGE_ES = "es";
    public static final String COUNTRY_PR = "PR";

    // константы имен кнопок и названий
    public static final String BUTTON_ADD = "add";
    public static final String BUTTON_REMOVE = "remove";
    public static final String BUTTON_REMOVE_LESS = "removeLess";
    public static final String BUTTON_CLEAR = "clear";
    public static final String BUTTON_LOGIN = "login";
    public static final String BUTTON_REGISTER = "register";
    public static final String BUTTON_UPDATE = "update";
    public static final String BUTTON_LOGOUT = "logOut";
    public static final String BUTTON_EXIT = "exit";
    public static final String BUTTON_FILE = "file";
    public static final String BUTTON_VIEW = "view";
    public static final String BUTTON_SCREEN = "screen";
    public static final String BUTTON_TABLE = "table";
    public static final String BUTTON_LANGUAGE = "language";

    public static final String TITLE_APPNAME = "appName";
    public static final String TITLE_USERNAME = "userName";
    public static final String TITLE_EMAIL = "email";
    public static final String TITLE_PASSWORD = "password";
    public static final String TITLE_NAME = "name";
    public static final String TITLE_TYPE = "type";
    public static final String TITLE_TIME = "time";
    public static final String TITLE_FIELD = "field";
    public static final String TITLE_GENDER = "gender";
    public static final String TITLE_POSITION = "position";
    public static final String TITLE_CONDITION = "condition";

    private static Locale locale;
    private static ResourceBundle buttons;
    private static ResourceBundle titles;


    public LocalizationManager(){
        setLocale(COUNTRY_RU);
    }

    public void setLocale(String s){
        switch(s) {
            case "PR":
                locale = new Locale(LANGUAGE_ES, COUNTRY_PR);
                break;
            case "UA":
                locale = new Locale(LANGUAGE_UK, COUNTRY_UA);
                break;
            case "NL":
                locale = new Locale(LANGUAGE_NL, COUNTRY_NL);
                break;
            default:
                locale = new Locale(LANGUAGE_RU, COUNTRY_RU);
                break;
        }
        changeLanguage();
    }

    private void changeLanguage(){
        buttons = ResourceBundle.getBundle("client.localization.asciiFiles.buttons", locale);
        titles = ResourceBundle.getBundle("client.localization.asciiFiles.titles", locale);
    }

    public String getNativeButton(String button){
        return buttons.getString(button);
    }
    public String getNativeTitle(String title){
        return titles.getString(title);
    }
}