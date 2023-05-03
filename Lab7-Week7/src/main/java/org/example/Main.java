package org.example;

import org.example.homework.menu.MenuMain;

public class Main {
    public static void main(String[] args) {
        MenuMain menuMain = new MenuMain(10);

        while ( !menuMain.isExit() ){

            menuMain.printMenu();
            menuMain.handleOption();

        }

    }
}