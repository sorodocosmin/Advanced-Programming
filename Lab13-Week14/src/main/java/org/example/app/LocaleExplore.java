package org.example.app;

import org.example.app.propreties.AdditionalMenuText;
import org.example.app.propreties.Menu;
import org.example.commands.DisplayLocales;
import org.example.commands.Info;
import org.example.commands.SetLocale;

import java.io.IOException;
import java.util.Scanner;

public class LocaleExplore {

    public static void main(String[] args) throws IOException {

        Scanner scanner = new Scanner(System.in);

        while(true){
            printMenu();
            //now read from the keyboard with scanner
            //and handle the user option

            String option = scanner.nextLine();

            handleUserOption(option);

        }

    }

    private static void handleUserOption(String option) {

        option = option.trim().toLowerCase();

        if(option.equals(Menu.getOption1())){

            DisplayLocales.execute();

        }else if(option.startsWith(Menu.getOption2())) {

            SetLocale.execute(option.substring(option.indexOf(Menu.getOption2()) + 1).trim());

        }else if(option.equals(Menu.getOption3())) {

            Info.execute();

        }else{

            System.out.println(AdditionalMenuText.getInvalidOptionText());

        }

    }

    private static void printMenu() {

        System.out.println();

        System.out.println(Menu.getRuleChoosingOption());
        System.out.println(Menu.getMenu());

        System.out.print(Menu.getOption1() + " ");
        System.out.println(Menu.getOption1Text());

        System.out.print(Menu.getOption2() + " ");
        System.out.println(Menu.getOption2Text());

        System.out.print(Menu.getOption3() + " ");
        System.out.println(Menu.getOption3Text());

        System.out.print(AdditionalMenuText.getPromptCommand());




    }

}
