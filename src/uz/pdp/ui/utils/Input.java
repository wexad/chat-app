package uz.pdp.ui.utils;

import java.util.Scanner;

public interface Input {
    Scanner SCANNER = new Scanner(System.in);

    static Integer inputInt(String msg) {
        Scanner scanner = new Scanner(System.in);
        System.out.print(msg);
        if (scanner.hasNextInt()) {
            return scanner.nextInt();
        } else {
            return inputInt(msg);
        }
    }

    static String inputStr(String msg) {
        System.out.print(msg);
        return SCANNER.nextLine();
    }
}
