package uz.pdp.ui.views;

import uz.pdp.backend.dto.LoginDTO;
import uz.pdp.backend.model.user.Users;
import uz.pdp.backend.service.user_service.UserService;
import uz.pdp.backend.service.user_service.UserServiceImpl;
import uz.pdp.ui.Main;
import uz.pdp.ui.utils.Input;
import uz.pdp.ui.utils.Message;

import java.time.LocalTime;
import java.util.Optional;

public class LoginView {

    static UserService userService = UserServiceImpl.getInstance();

    public static void registerUser() {
        System.out.println("Registration : ");

        String name = Input.inputStr("Name : ");
        String number = Input.inputStr("Number : ");
        String nickname = Input.inputStr("Nickname : ");

        while (!userService.checkUnique(number, nickname)) {
            Message.failure();
            System.out.println("It is not unique number or nickname! Try again ? 1 yes / 0 no");

            if (Input.inputStr("Choice : ").equals("1")) {
                number = Input.inputStr("Number : ");
                nickname = Input.inputStr("Nickname : ");
            } else {
                return;
            }
        }

        String password = Input.inputStr("Password : ");

        Main.curUser = userService.getAndAdd(name, number, nickname, password);

        Message.success();
        System.out.println("Welcome! " + Main.curUser.getName());
    }

    public static void loginUser() {
        System.out.println("Log In : ");

        String nickOrNum = Input.inputStr("Nickname or number : ");
        String password = Input.inputStr("Password : ");

        Optional<Users> userOpt = userService.findUser(new LoginDTO(nickOrNum, password));

        userOpt.ifPresentOrElse((user) -> {
            Main.curUser = user;
            Message.success();
            System.out.println("Welcome! " + Main.curUser.getName());
        }, () -> {
            Message.failure();
            System.out.println("Wrong number | nickname or password ! Try again ? 1 yes / 0 no");

            if (Input.inputStr("Choice : ").equals("1")) {
                loginUser();
            }
        });

    }

    public static void displayMenu() {
        System.out.println("""
                1. Log In
                2. Sign Up
                                
                0. Exit
                """);
    }

    public static void logOut() {
        Main.curUser.setLastActivity(LocalTime.now());
        Main.curUser = null;
    }
}
