package org.example.commands;

import org.example.app.propreties.AdditionalMenuText;
import org.example.app.propreties.PropertiesValues;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.ResourceBundle;

public class SetLocale {
    public static void execute(String nameLocaleTag) {

        Locale.setDefault(Locale.forLanguageTag(nameLocaleTag));

        String pattern = AdditionalMenuText.getCurrentLocaleText();

        Object[] arguments = {Locale.getDefault()};

        String currentLocale = new MessageFormat(pattern).format(arguments);
        System.out.println(currentLocale);

    }
}
