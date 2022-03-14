package Task_6.myBookStore.ui.controller;

import Task_6.myBookStore.ui.builder.Builder;

import java.util.Scanner;

public class MenuController {
    private static MenuController instance;
    private Builder builder;
    private Navigator navigator;

    private final Scanner sc = new Scanner(System.in);

    private MenuController() {
    }

    public static MenuController getInstance() {
        if (instance == null) {
            instance = new MenuController();
        }
        return instance;
    }

    public void run() {
        builder = new Builder();
        builder.buildMenu();
        navigator = new Navigator(builder.getRootMenu());
        int index = 0;
        while (index != -1) {
            navigator.printMenu();
            index = getInput(navigator.getCurrentMenu().getMenuItem().size());
            navigator.navigate(index);
        }
    }

    private int getInput(int maxChoice) {
        int menuChois = sc.nextInt();
        if (menuChois < 1 || menuChois > maxChoice) {
            System.out.println("Не верный выбор");
            return -1;
        }
        return menuChois;
    }

    public void close() {
        sc.close();
    }
}
