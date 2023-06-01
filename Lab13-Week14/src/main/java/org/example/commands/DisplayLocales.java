package org.example.commands;

import org.example.app.propreties.AdditionalMenuText;
import org.example.app.propreties.PropertiesValues;

import javax.sound.sampled.FloatControl;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

public class DisplayLocales {
    public static void execute() {

        System.out.println(AdditionalMenuText.getAvailableLocalesText());

        Locale defaultLocale = Locale.getDefault();

        System.out.println(defaultLocale + "(" + defaultLocale.getDisplayName() + ")");

        for (Locale locale : Locale.getAvailableLocales()) {

            ResourceBundle bundle = ResourceBundle.getBundle(PropertiesValues.getBaseName(), locale);
            if(bundle.getLocale().equals(locale) &&
                    !locale.equals(defaultLocale) &&
                        !locale.toString().isBlank()){//print only the ones that we also have .propreties file
                System.out.println(locale + "(" + locale.getDisplayName() + ")");
            }

        }

    }
}
