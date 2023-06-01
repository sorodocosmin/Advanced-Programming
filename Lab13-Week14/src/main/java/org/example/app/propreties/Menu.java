package org.example.app.propreties;



import java.util.Locale;
import java.util.ResourceBundle;

public class Menu extends PropertiesValues {

    public static String getRuleChoosingOption(){

        ResourceBundle messages = ResourceBundle.getBundle(BASE_NAME, Locale.getDefault());
        return messages.getString(PropertiesKeys.MENU_RULE);

    }

    public static String getMenu(){

            ResourceBundle messages = ResourceBundle.getBundle(BASE_NAME, Locale.getDefault());
            return messages.getString(PropertiesKeys.MENU);

    }

    public static String getOption1(){

            ResourceBundle messages = ResourceBundle.getBundle(BASE_NAME, Locale.getDefault());
            return messages.getString(PropertiesKeys.MENU_OPTION_1);

    }

    public static String getOption2(){

        ResourceBundle messages = ResourceBundle.getBundle(BASE_NAME, Locale.getDefault());
        return messages.getString(PropertiesKeys.MENU_OPTION_2);

    }

    public static String getOption3(){

        ResourceBundle messages = ResourceBundle.getBundle(BASE_NAME, Locale.getDefault());
        return messages.getString(PropertiesKeys.MENU_OPTION_3);

    }

    public static String getOption1Text(){

        ResourceBundle messages = ResourceBundle.getBundle(BASE_NAME, Locale.getDefault());
        return messages.getString(PropertiesKeys.MENU_OPTION_1_TEXT);

    }

    public static String getOption2Text(){

        ResourceBundle messages = ResourceBundle.getBundle(BASE_NAME, Locale.getDefault());
        return messages.getString(PropertiesKeys.MENU_OPTION_2_TEXT);

    }

    public static String getOption3Text(){

        ResourceBundle messages = ResourceBundle.getBundle(BASE_NAME, Locale.getDefault());
        return messages.getString(PropertiesKeys.MENU_OPTION_3_TEXT);

    }

}
