package uz.pdp.ui.utils;

import java.util.Scanner;

public interface Input {
    Scanner ScanStr = new Scanner(System.in);

    static Integer inputInt(String msg) {
        Scanner scanInt = new Scanner(System.in);
        System.out.print(msg);
        if (scanInt.hasNextInt()) {
            return scanInt.nextInt();
        } else {
            return inputInt(msg);
        }
    }

    static String inputStr(String msg) {
        System.out.print(msg);
        return ScanStr.nextLine();
    }
}
