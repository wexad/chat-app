package uz.pdp.ui.views;

import uz.pdp.backend.enums.MessageType;
import uz.pdp.backend.model.chat.Chats;
import uz.pdp.backend.model.contact.Contacts;
import uz.pdp.backend.model.message.Messages;
import uz.pdp.backend.model.user.Users;
import uz.pdp.backend.service.chat_service.ChatService;
import uz.pdp.backend.service.chat_service.ChatServiceImpl;
import uz.pdp.backend.service.contact_service.ContactService;
import uz.pdp.backend.service.contact_service.ContactServiceImpl;
import uz.pdp.backend.service.file_service.FileService;
import uz.pdp.backend.service.file_service.FileServiceImpl;
import uz.pdp.backend.service.message_service.MessageService;
import uz.pdp.backend.service.message_service.MessageServiceImpl;
import uz.pdp.backend.service.user_service.UserService;
import uz.pdp.backend.service.user_service.UserServiceImpl;
import uz.pdp.ui.Main;
import uz.pdp.ui.utils.Input;
import uz.pdp.ui.utils.Message;

import java.time.LocalTime;
import java.util.List;

public class ChatsView {

    static ChatService chatService = ChatServiceImpl.getInstance();
    static UserService userService = UserServiceImpl.getInstance();
    static MessageService messageService = MessageServiceImpl.getInstance();
    static ContactService contactService = ContactServiceImpl.getInstance();
//    static FileService fileService = FileServiceImpl.getInstance();

    public static void start() {
        Integer choice;

        do {
            displayMenu();
            choice = Input.inputInt("Choice : ");

            switch (choice) {
                case 1 -> sendMessage();

                case 2 -> searchUser();
            }
        } while (choice != 0);
    }

    private static void searchUser() {
        List<Users> usersByWord = userService.getUsersByWord(Input.inputStr("Search : "), Main.curUser.getId());

        if (usersByWord.isEmpty()) {
            Message.noData();
            return;
        }
        showSearchedUsers(usersByWord);

        int index = Input.inputInt("Choice(0 - exit) : ") - 1;

        if (index == -1) {
            return;
        }

        if (index < usersByWord.size()) {
            if (!chatService.isExist(usersByWord.get(index).getId())) {
                chatService.add(new Chats(Main.curUser.getId(), usersByWord.get(index).getId()));
//                fileService.saveChats();

            }
            sendMessageToChat(chatService.getChatOfUser(Main.curUser.getId()));
        }
    }

    private static void showSearchedUsers(List<Users> usersByWord) {
        int i = 1;
        System.out.println("Users : ");
        for (Users user : usersByWord) {
            Contacts contact = contactService.getContact(Main.curUser.getId(), user.getId());
            if (contact != null) {
                System.out.println(i++ + ". " + contact.getName());
            } else {
                System.out.println(i++ + ". " + user);
            }
        }
        System.out.println("=====================");
    }

    private static void sendMessage() {
        List<Chats> chatOfUser = chatService.getChatsOfUser(Main.curUser.getId());
        if (chatOfUser.isEmpty()) {
            Message.noData();
            return;
        }
        showChats(chatOfUser);

        int index = Input.inputInt("Choose (0 - exit) : ") - 1;

        if (index == -1) {
            return;
        }

        if (index < chatOfUser.size()) {
            sendMessageToChat(chatOfUser.get(index));
        }
    }

    private static void sendMessageToChat(Chats chat) {
        while (true) {
            if (chat.getFirstUserId().equals(Main.curUser.getId())) {
                System.out.println("\t".repeat(3) + userService.get(chat.getSecondUserId()));
            } else {
                System.out.println("\t".repeat(3) + userService.get(chat.getFirstUserId()));
            }
            showChatHistory(chat);

            String text = Input.inputStr("Type to send message (0 - exit) : ");

            if (text.equals("0")) {
                return;
            }

            Messages messages = new Messages(text, MessageType.PRIVATE, Main.curUser.getId(), chat.getId(), LocalTime.now(), false);

            messageService.add(messages);

            Message.success();
            System.out.println("Sent!");

//            fileService.saveMessages();
        }
    }

    private static void showChatHistory(Chats chat) {
        List<Messages> messagesOfChat = messageService.getMessagesOfChat(chat);


        for (Messages messages : messagesOfChat) {
            if (messages.getUserId().equals(Main.curUser.getId())) {
                if (messages.isRead()) {
                    System.out.println("\t".repeat(6) + messages);
                } else {
                    System.out.println("\t".repeat(6) + messages + " 👀");
                }
            } else {
                System.out.println(messages);
                messages.setRead(true);
//                fileService.saveMessages();
            }
        }
        System.out.println("===========================");
    }

    public static void showChats(List<Chats> chatsOfUser) {
        System.out.println("Chats : ");
        int i = 1;
        for (Chats chat : chatsOfUser) {
            Users secondUser;
            if (chat.getFirstUserId().equals(Main.curUser.getId())) {
                secondUser = userService.get(chat.getSecondUserId());
            } else {
                secondUser = userService.get(chat.getFirstUserId());
            }
            Contacts contact = contactService.getContact(Main.curUser.getId(), secondUser.getId());
            if (contact != null) {
                System.out.println(i++ + contact.getName() + " | last activity : " + secondUser.getLastActivity() + " | " + messageService.countNotReadMessages(secondUser.getId(), Main.curUser.getId()));
            } else {
                System.out.println(i++ + secondUser.toString() + " | last activity : " + secondUser.getLastActivity() + " | " + messageService.countNotReadMessages(secondUser.getId(), Main.curUser.getId()));
            }
        }
        System.out.println("==========================");
    }

    private static void displayMenu() {
        System.out.println("""
                Menu :
                1. Send message to any chat
                2. Search user
                                
                0. Go back
                """);
    }
}
