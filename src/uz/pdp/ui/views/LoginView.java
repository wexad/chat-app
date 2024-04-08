package uz.pdp.ui.views;

import uz.pdp.backend.service.user_service.UserService;
import uz.pdp.backend.service.user_service.UserServiceImpl;
import uz.pdp.ui.utils.Input;

public class LoginView {

    static UserService userService = UserServiceImpl.getInstance();

    public static void start() {
        while (true) {
            displayMenu();

            switch (Input.inputInt("Choice : ")) {
                case 1 -> loginUser();
                case 2 -> registerUser();


            }
        }
    }

    private static void registerUser() {
        System.out.println("Registration : ");

        String name = Input.inputStr("Name : ");
        Integer number = Input.inputInt("Number : ");
        String nickname = Input.inputStr("Nickname : ");
        String password = Input.inputStr("Password : ");

        boolean b = userService.checkUnique(number, password);
    }

    private static void loginUser() {

    }

    private static void displayMenu() {
        System.out.println("""
                1. Log In
                2. Sign Up
                                
                """);
    }
}
