package uz.pdp.ui.views;

import uz.pdp.backend.model.user.Users;
import uz.pdp.backend.service.channel_service.channel_user_service.ChannelUserService;
import uz.pdp.backend.service.channel_service.channel_user_service.ChannelUserServiceImpl;
import uz.pdp.backend.service.chat_service.ChatService;
import uz.pdp.backend.service.chat_service.ChatServiceImpl;
import uz.pdp.backend.service.group_service.group_user_service.GroupUserService;
import uz.pdp.backend.service.group_service.group_user_service.GroupUserServiceImpl;
import uz.pdp.backend.service.user_service.UserService;
import uz.pdp.backend.service.user_service.UserServiceImpl;
import uz.pdp.ui.Main;
import uz.pdp.ui.utils.Input;

public class SettingsView {

    static UserService userService = UserServiceImpl.getInstance();
    static GroupUserService groupUserService = GroupUserServiceImpl.getInstance();
    static ChannelUserService channelUserService = ChannelUserServiceImpl.getInstance();
    static ChatService chatService = ChatServiceImpl.getInstance();

    public static void start() {
        while (true) {
            displaySettingsMenu();

            switch (Input.inputInt("Choice : ")) {
                case 1 -> changeName();

                case 2 -> changeNickname();

                case 3 -> changePassword();

                case 4 -> {
                    deleteAccount();
                    LoginView.logOut();
                    return;
                }

                case 0 -> {
                    return;
                }
            }
        }
    }

    private static void deleteAccount() {
        String choice = Input.inputStr("Are you sure delete account ?! 1 yes / 0 no ");

        if (choice.equals("1")) {
            String password = Input.inputStr("Enter password : ");

            if (Main.curUser.getPassword().equals(password)) {
                userService.deleteByUserId(Main.curUser.getId());

                chatService.deleteByUserId(Main.curUser.getId());

                groupUserService.deleteByUserId(Main.curUser.getId());

                channelUserService.deleteByUserId(Main.curUser.getId());

                System.out.println("Bye Bye :(");

            } else {
                System.out.println("Wrong password! Try again ? ");
            }
        }
    }

    private static void changePassword() {
        Users curUser = Main.curUser;
        String password = Input.inputStr("Enter new password : " + curUser.getPassword() + " --> : (0 - cancel) ");
        if (password.equals("0")) {
            return;
        }

        curUser.setPassword(password);

    }

    private static void changeNickname() {
        Users curUser = Main.curUser;
        String nickname = Input.inputStr("Enter new nickname : " + curUser.getNickname() + " --> : (0 - cancel) ");
        if (nickname.equals("0")) {
            return;
        }

        curUser.setNickname(nickname);

    }

    private static void changeName() {
        Users curUser = Main.curUser;
        String name = Input.inputStr("Enter new name : " + curUser.getName() + " --> : (0 - cancel) ");
        if (name.equals("0")) {
            return;
        }

        curUser.setName(name);
    }

    private static void displaySettingsMenu() {
        System.out.println("""
                1. Change name
                2. Change nickname
                3. Change password
                4. Delete account
                                
                0. Go back
                """);
    }
}
