package org.example.commands;

import org.example.app.propreties.AdditionalMenuText;

import javax.swing.text.DateFormatter;
import java.text.DateFormat;
import java.text.DateFormatSymbols;
import java.text.MessageFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Currency;
import java.util.Locale;

public class Info {

    public static void execute() {

        String pattern = AdditionalMenuText.getLocaleInfo();

        Locale locale = Locale.getDefault();

        Object[] arguments = {Locale.getDefault()};

        String currentLocale = new MessageFormat(pattern).format(arguments);
        System.out.println(currentLocale);

        System.out.println("Country: " + locale.getDisplayCountry(locale));
        System.out.println("Language: " + locale.getDisplayLanguage(locale));

        Currency currency = Currency.getInstance(locale);
        System.out.println("Currency: " + currency.getDisplayName(locale) + " - " + currency.getCurrencyCode());

        DateFormatSymbols dateFormatSymbols = DateFormatSymbols.getInstance(locale);
        System.out.println("Week Days: " + String.join(", ", dateFormatSymbols.getWeekdays()));
        System.out.println("Months: " + String.join(", ", dateFormatSymbols.getMonths()));

        LocalDate today = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL)
                .withLocale(locale);
        System.out.println("Today: " + today.format(formatter));

    }

}
