package uz.pdp.ui.views;

import uz.pdp.backend.enums.MessageType;
import uz.pdp.backend.enums.Type;
import uz.pdp.backend.model.contact.Contacts;
import uz.pdp.backend.model.group.GroupUsers;
import uz.pdp.backend.model.group.Groups;
import uz.pdp.backend.model.message.Messages;
import uz.pdp.backend.model.user.Users;
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
import uz.pdp.ui.utils.Message;

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
        String search = Input.inputStr("Search group : ");

        List<Groups> groupsByWord = groupService.getGroupsByWord(search);

        String groupId = chooseGroup(groupsByWord);

        if (groupId == null) {
            return;
        }

        if (!groupService.isMember()) {
            int i = Input.inputInt("Do you want to sign this group : 1 yes / 0 no");

            if (i == 1) {
                GroupUsers groupUser = new GroupUsers(Main.curUser.getId(), groupId, false);

                groupUserService.add(groupUser);

                Message.success();

                groupsMenu(groupId);
            }
        } else {
            groupsMenu(groupId);
        }
    }

    private static void createGroup() {
        String name = Input.inputStr("Enter name : ");

        if (!groupService.isUnique(name)) {
            System.out.println("There is not unique name ! Do you want try again ? 1 yes / 0 no");

            if (Input.inputInt("Choice : ") == 1) {
                createGroup();
            }
        } else {
            Type type = chooseType();
            while (type == null) {
                System.out.println("There is not unique name ! Do you want try again ? 1 yes / 0 no");
                if (Input.inputInt("Choice : ") != 1) {
                    return;
                }
                type = chooseType();

            }

            Groups group = new Groups(name, type);
            groupService.add(group);
            Message.success();
        }
    }

    private static Type chooseType() {
        System.out.println("""
                1. Private
                2. Public
                """);

        int type = Input.inputInt("Chose : ");

        Type[] types = Type.values();

        if (type >= types.length) {
            return null;
        }
        return types[type - 1];
    }

    private static void sendMessage() {
        while (true) {
            List<Groups> groupsOfUser = groupService.getGroupsOfUser(Main.curUser.getId());

            String groupId = chooseGroup(groupsOfUser);

            if (groupId == null) {
                return;
            }

            groupsMenu(groupId);
        }


    }

    private static void groupsMenu(String groupId) {
        while (true) {
            boolean admin = groupUserService.isAdmin(Main.curUser.getId(), groupId);

            displayGroupMenu(admin);

            switch (Input.inputInt("Choice : ")) {
                case 1 -> addMember(groupId);

                case 2 -> sendMessageToGroup(groupId);

                case 3 -> {
                    exitFromGroup(groupId);
                    return;
                }

                case 4 -> {
                    if (admin) {
                        makeAdmin(groupId);
                    }
                }

                case 5 -> {
                    if (admin) {
                        deleteAdmin(groupId);
                    }
                }

                case 6 -> {
                    if (admin) {
                        renameGroup(groupId);
                    }
                }

                case 7 -> {
                    if (admin) {
                        removeMember(groupId);
                    }
                }

                case 8 -> {
                    if (admin) {
                        deleteGroup(groupId);
                        return;
                    }
                }

                case 0 -> {
                    return;
                }
            }

        }
    }

    private static void deleteAdmin(String groupId) {
        List<Users> admins = groupUserService.getAdminsWithinMe(Main.curUser.getId());

        if (admins.isEmpty()) {
            Message.failure();
            System.out.println("There is only 1 admin is you! ");
            return;
        }

        showUsers(admins);

        int index = Input.inputInt("Choose (0 - cancel) : ") - 1;

        if (index != -1 && index < admins.size()) {
            groupUserService.deleteAdminStatus(admins.get(index).getId(), groupId);
            Message.success();
        }
    }

    private static void exitFromGroup(String groupId) {
        if (groupUserService.isAdmin(Main.curUser.getId(), groupId)) {
            if (groupUserService.countAdmins(groupId) == 1) {
                System.out.println("There is only 1 admin is you! Make someone admin then exit from group! ");
                return;
            }
        }
        groupUserService.deleteByMemberId(Main.curUser.getId(), groupId);

        Message.success();
    }

    private static void deleteGroup(String groupId) {
        groupService.deleteById(groupId);

        groupUserService.deleteAllMembers(groupId);
    }

    private static void removeMember(String groupId) {
        while (true) {
            List<Users> members = groupUserService.getMembers(groupId);
            showUsers(members);

            int index = Input.inputInt("Choose (0 - exit) : ") - 1;

            if (index == -1) {
                return;
            }

            if (index >= members.size()) {
                Users user = members.get(index);
                groupUserService.deleteByMemberId(user.getId(), groupId);
                Message.success();
            }
        }
    }

    private static void renameGroup(String groupId) {
        Groups group = groupService.get(groupId);

        group.setName(Input.inputStr("Enter new name : " + group.getName() + " --> "));

        Message.success();
    }

    private static void makeAdmin(String groupId) {
        while (true) {
            List<Users> members = groupUserService.getMembers(groupId);
            showUsers(members);

            int index = Input.inputInt("Choose (0 - exit) : ") - 1;

            if (index == -1 || index >= members.size()) {
                return;
            }

            Users users = members.get(index);

            GroupUsers groupUsers = groupUserService.get(users.getId());

            groupUsers.setAdmin(true);
        }
    }

    private static void showUsers(List<Users> members) {
        int i = 1;
        System.out.println("Members : ");
        for (Users member : members) {
            System.out.println(i++ + ". " + member);
        }
        System.out.println("==================");

    }

    private static void sendMessageToGroup(String groupId) {
        while (true) {
            showGroupHistory(groupId);

            String text = Input.inputStr("Type to send message (0 - exit) : ");

            if (text.equals("0")) {
                return;
            }

            messageService.add(new Messages(text, MessageType.GROUP, Main.curUser.getId(), groupId, LocalTime.now(), false));

            Message.success();

            System.out.println("Sent!");
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

    private static void displayGroupMenu(boolean admin) {
        System.out.println("""
                1. Send message
                2. Add member
                3. Exit from group
                """);

        if (admin) {
            System.out.println("""
                    4. Make admin
                    5. Delete admin
                    6. Rename group
                    7. Remove member from group
                    8. Delete group
                    """);
        }

        System.out.println("\n 0. Go back");
    }

    private static void showGroupHistory(String groupId) {
        List<Messages> messagesOfGroup = messageService.getMessagesGroupOrChannel(groupId);

        for (Messages messages : messagesOfGroup) {
            if (messages.getUserId().equals(Main.curUser.getId())) {
                if (messages.isRead()) {
                    System.out.println("\t".repeat(6) + messages);
                } else {
                    System.out.println("\t".repeat(6) + messages + " 👀");
                }
            } else {
                System.out.println(messages);
                messages.setRead(true);
            }
        }
        System.out.println("===========================");
    }

    private static String chooseGroup(List<Groups> groups) {
        showGroups(groups);

        int index = Input.inputInt("Choose (0 - exit) : ") - 1;

        if (index != -1) {
            return groups.get(index).getId();
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
