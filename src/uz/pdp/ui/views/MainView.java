package uz.pdp.ui.views;

import uz.pdp.ui.utils.Input;
import uz.pdp.ui.utils.Message;

public class MainView {
    public static void start() {
        Integer choice;
        do {
            displayMenu();
            choice = Input.inputInt("Choice : ");

            switch (choice) {
                case 1 -> ContactView.addContact();
                case 2 -> ChatsView.start();
                case 3 -> GroupsView.start();
                case 4 -> ChannelsView.start();
                case 5 -> SettingsView.start();
                case 0 -> LoginView.logOut();

                default -> Message.failure();

            }
        } while (choice != 0);
    }

    private static void displayMenu() {
        System.out.println("""
                Menu :
                1. Add contact
                2. Chats
                3. Groups
                4. Channels
                5. Search
                6. Settings
                                
                0. Log Out
                """);
    }
}
