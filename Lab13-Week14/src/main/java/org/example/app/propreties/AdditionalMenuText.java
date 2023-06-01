package org.example.app.propreties;

import java.util.Locale;
import java.util.ResourceBundle;

public class AdditionalMenuText extends PropertiesValues{

    public static String getPromptCommand() {

        ResourceBundle messages = ResourceBundle.getBundle(BASE_NAME, Locale.getDefault());
        return messages.getString(PropertiesKeys.MENU_PROMPT_COMMAND);
    }

    public static String getAvailableLocalesText(){

            ResourceBundle messages = ResourceBundle.getBundle(BASE_NAME, Locale.getDefault());
            return messages.getString(PropertiesKeys.AVAILABLE_LOCALES);

    }

    public static String getInvalidOptionText() {

        ResourceBundle messages = ResourceBundle.getBundle(BASE_NAME, Locale.getDefault());
        return messages.getString(PropertiesKeys.INVALID_OPTION_TEXT);

    }

    public static String getCurrentLocaleText() {

        ResourceBundle messages = ResourceBundle.getBundle(BASE_NAME, Locale.getDefault());
        return messages.getString(PropertiesKeys.CURRENT_LOCALE_TEXT);

    }

    public static String getLocaleInfo() {

            ResourceBundle messages = ResourceBundle.getBundle(BASE_NAME, Locale.getDefault());
            return messages.getString(PropertiesKeys.LOCALE_INFO);
    }
}
