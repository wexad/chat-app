package uz.pdp.ui.utils;

public interface Message {

    static void success() {
        System.out.println("\u001B[32m" + "Success! " + "\u001B[0m");
    }

    static void failure() {
        System.out.println("\u001B[31m" + "Error! " + "\u001B[0m");
    }

    static void saved() {
        System.out.println("\u001B[34m" + "Saved! " + "\u001B[0m");
    }
}
