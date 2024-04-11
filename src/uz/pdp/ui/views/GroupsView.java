package uz.pdp.ui.views;

import uz.pdp.backend.enums.MessageType;
import uz.pdp.backend.model.contact.Contacts;
import uz.pdp.backend.model.group.GroupUsers;
import uz.pdp.backend.model.group.Groups;
import uz.pdp.backend.model.message.Messages;
import uz.pdp.backend.service.chat_service.ChatService;
import uz.pdp.backend.service.chat_service.ChatServiceImpl;
import uz.pdp.backend.service.contact_service.ContactService;
import uz.pdp.backend.service.contact_service.ContactServiceImpl;
import uz.pdp.backend.service.group_service.GroupService;
import uz.pdp.backend.service.group_service.GroupServiceImpl;
import uz.pdp.backend.service.group_service.group_user_service.GroupUserService;
import uz.pdp.backend.service.group_service.group_user_service.GroupUserServiceImpl;
import uz.pdp.backend.service.message_service.MessageService;
import uz.pdp.backend.service.message_service.MessageServiceImpl;
import uz.pdp.backend.service.user_service.UserService;
import uz.pdp.backend.service.user_service.UserServiceImpl;
import uz.pdp.ui.Main;
import uz.pdp.ui.utils.Input;

import java.time.LocalTime;
import java.util.List;

public class GroupsView {
    static GroupService groupService = GroupServiceImpl.getInstance();

    static MessageService messageService = MessageServiceImpl.getInstance();

    static UserService userService = UserServiceImpl.getInstance();

    static GroupUserService groupUserService = GroupUserServiceImpl.getInstance();

    static ContactService contactService = ContactServiceImpl.getInstance();

    public static void start() {
        Integer choice;

        do {
            displayMenu();
            choice = Input.inputInt("Choice : ");

            switch (choice) {
                case 1 -> sendMessage();

                case 2 -> createGroup();

                case 3 -> searchGroup();
            }
        } while (choice != 0);
    }

    private static void searchGroup() {

    }

    private static void createGroup() {

    }

    private static void sendMessage() {
        while (true) {
            String groupId = chooseGroup();

            if (groupId == null) {
                return;
            }

            groupsMenu(groupId);
        }


    }

    private static void groupsMenu(String groupId) {
        while (true) {
            if (groupUserService.isAdmin(Main.curUser.getId(), groupId)) {
                displayAdminMenu();
            } else {
                displayMemberMenu();
            }

            switch (Input.inputInt("Choice : ")) {
                case 1 -> addMember(groupId);

                case 2 -> sendMessageToGroup(groupId);
            }

        }
    }

    private static void sendMessageToGroup(String groupId) {
        while (true) {
            showGroupHistory(groupId);

            String text = Input.inputStr("Type to send message (0 - exit) : ");

            if (text.equals("0")) {
                return;
            }

            messageService.add(new Messages(text, MessageType.GROUP, Main.curUser.getId(), groupId, LocalTime.now(), false));
        }
    }

    private static void addMember(String groupId) {
        while (true) {
            List<Contacts> contacts = contactService.getContacts(Main.curUser.getId());

            showContacts(contacts);

            int index = Input.inputInt("Choose (0 - exit): ") - 1;

            if (index == -1 || index >= contacts.size()) {
                return;
            }

            Contacts contact = contacts.get(index);
            groupUserService.add(new GroupUsers(contact.getContactId(), groupId, false));
        }
    }

    private static void showContacts(List<Contacts> contacts) {
        int i = 1;
        System.out.println("Contacts : ");
        for (Contacts contact : contacts) {
            System.out.println(i++ + ". " + userService.get(contact.getContactId()));
        }
        System.out.println("=======================");
    }

    private static void displayMemberMenu() {
        System.out.println("""
                1. Send message
                2. Add member
                                
                0. Go back
                """);
    }

    private static void displayAdminMenu() {
        System.out.println("""
                1. Send message
                2. Add member
                3. Make admin
                4. Rename group
                5. Remove member from group
                6. Delete group
                                
                0. Go back
                """);
    }

    private static void showGroupHistory(String groupId) {
        List<Messages> messagesOfGroup = messageService.getMessagesGroupOrChannel(groupId);

        for (Messages message : messagesOfGroup) {
            if (message.getUserId().equals(Main.curUser.getId())) {
                System.out.println("\t".repeat(3) + message);
            } else {
                System.out.println("from : " + userService.get(message.getUserId()));
                System.out.println(message);
            }
        }
        System.out.println("==================");
    }

    private static String chooseGroup() {
        List<Groups> groupsOfUser = groupService.getGroupsOfUser(Main.curUser.getId());

        showGroups(groupsOfUser);

        int index = Input.inputInt("Choose (0 - exit) : ") - 1;

        if (index != -1) {
            return groupsOfUser.get(index).getId();
        }

        return null;
    }

    private static void showGroups(List<Groups> groups) {
        int i = 1;
        System.out.println("Groups : ");
        for (Groups group : groups) {
            System.out.println(i++ + ". " + group + " | members : " + groupUserService.getCountOfMembers(group.getId()));
        }
        System.out.println("====================");
    }

    private static void displayMenu() {
        System.out.println("""
                1. Groups
                2. Create a group
                2. Search a group
                                
                0. Go back
                """);
    }
}
