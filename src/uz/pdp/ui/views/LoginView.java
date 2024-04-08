package uz.pdp.ui.views;

import uz.pdp.backend.service.user_service.UserService;
import uz.pdp.backend.service.user_service.UserServiceImpl;
import uz.pdp.ui.Main;
import uz.pdp.ui.utils.Input;
import uz.pdp.ui.utils.Message;

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

        while (!userService.checkUnique(number, nickname)) {
            Message.failure();
            System.out.println("It is not unique number or nickname! Try ? 1 yes / 0 no");

            if (Input.inputStr("Choice : ").equals("1")) {
                number = Input.inputInt("Number : ");
                nickname = Input.inputStr("Nickname : ");
            } else {
                return;
            }
        }

        String password = Input.inputStr("Password : ");

        Main.curUser = userService.getAndAdd(name, number, nickname, password);

        System.out.println("Welcome! " + Main.curUser);
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
