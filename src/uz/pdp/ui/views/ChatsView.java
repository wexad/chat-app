package uz.pdp.ui.views;

import uz.pdp.backend.enums.MessageType;
import uz.pdp.backend.model.chat.Chats;
import uz.pdp.backend.model.message.Messages;
import uz.pdp.backend.model.user.Users;
import uz.pdp.backend.service.chat_service.ChatService;
import uz.pdp.backend.service.chat_service.ChatServiceImpl;
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
        List<Users> usersByWord = userService.getUsersByWord(Input.inputStr("Search : "));

        showSearchedUsers(usersByWord);

        int index = Input.inputInt("Choice(0 - exit) : ") - 1;

        if (index == -1) {
            return;
        }

        if (index < usersByWord.size()) {
            Chats chats = new Chats(Main.curUser.getId(), usersByWord.get(index).getId());
            chatService.add(chats);

            sendMessageToChat(chats);

        }
    }

    private static void showSearchedUsers(List<Users> usersByWord) {
        int i = 1;
        System.out.println("Users : ");
        for (Users users : usersByWord) {
            System.out.println(i++ + ". " + users);
        }
        System.out.println("=====================");
    }

    private static void sendMessage() {
        List<Chats> chatOfUser = chatService.getChatsOfUser(Main.curUser.getId());

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
                System.out.println("\t".repeat(4) + userService.get(chat.getSecondUserId()));
            } else {
                System.out.println("\t".repeat(4) + userService.get(chat.getFirstUserId()));
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
        }
    }

    private static void showChatHistory(Chats chat) {
        List<Messages> messagesOfChat = messageService.getMessagesOfChat(chat);


        for (Messages messages : messagesOfChat) {
            if (messages.getUserId().equals(Main.curUser.getId())) {
                System.out.println("\t".repeat(3) + messages);
            } else {
                System.out.println(messages);
                messages.setRead(true);
            }
        }
        System.out.println("===========================");
    }

    private static void showChats(List<Chats> chatsOfUser) {
        System.out.println("Chats : ");
        int i = 1;
        for (Chats chats : chatsOfUser) {
            if (chats.getFirstUserId().equals(Main.curUser.getId())) {
                System.out.println(i++ + ". " + userService.get(chats.getSecondUserId()));
            } else {
                System.out.println(i++ + ". " + userService.get(chats.getFirstUserId()));
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
