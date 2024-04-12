package uz.pdp.ui.views;

import uz.pdp.backend.model.channel.Channels;
import uz.pdp.backend.model.message.Messages;
import uz.pdp.backend.model.user.Users;
import uz.pdp.backend.service.channel_service.ChannelService;
import uz.pdp.backend.service.channel_service.ChannelServiceImpl;
import uz.pdp.backend.service.channel_service.channel_user_service.ChannelUserService;
import uz.pdp.backend.service.channel_service.channel_user_service.ChannelUserServiceImpl;
import uz.pdp.backend.service.message_service.MessageService;
import uz.pdp.backend.service.message_service.MessageServiceImpl;
import uz.pdp.ui.Main;
import uz.pdp.ui.utils.Input;
import uz.pdp.ui.utils.Message;

import java.util.List;

public class ChannelsView {

    static ChannelService channelService = ChannelServiceImpl.getInstance();

    static MessageService messageService = MessageServiceImpl.getInstance();

    static ChannelUserService channelUserService = ChannelUserServiceImpl.getInstance();

    public static void start() {
        Integer choice;
        do {
            displayMenu();
            choice = Input.inputInt("Choice : ");

            switch (choice) {
                case 1 -> channelMenu();

                case 2 -> createChannel();

                case 3 -> searchChannel();
            }
        } while (choice != 0);
    }

    private static void searchChannel() {

    }

    private static void createChannel() {
    }

    private static void channelMenu() {
        while (true) {
            List<Channels> channelsOfUser = channelService.getChannelsOfUser(Main.curUser.getId());
            String channelId = chooseChannel(channelsOfUser);

            if (channelId == null) {
                return;
            }

            boolean admin = channelUserService.isAdmin(Main.curUser.getId(), channelId);

            displayChannelMenu(admin);

            int choice = Input.inputInt("Choose : ");

            if (choice == 0) {
                return;
            }

            if (admin) {
                switch (choice) {

                }
            } else {
                switch (choice) {
                    case 1 -> checkChannel(channelId);

                    case 2 -> {
                        exitFromChannel(channelId);
                        return;
                    }
                }
            }
        }
    }

    private static void exitFromChannel(String channelId) {
        channelUserService.deleteMember(Main.curUser.getId(), channelId);

        Message.success();
    }

    private static void checkChannel(String channelId) {
        while (true) {
            System.out.println(channelService.get(channelId));

            showChannelHistory(channelId);

            String s = Input.inputStr("Press any button to exit");

            if (s != null) {
                return;
            }
        }
    }

    private static void showChannelHistory(String channelId) {
        List<Messages> messagesChannel = messageService.getMessagesGroupOrChannel(channelId);
        for (Messages messages : messagesChannel) {
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

    private static void displayChannelMenu(boolean admin) {
        if (admin) {
            System.out.println("""
                    1. Send message
                    2. Make admin
                    3. Delete admin
                    4. Rename channel
                    5. Delete channel
                    """);
        } else {
            System.out.println("""
                    1. Check
                    2. Exit from channel
                    """);
        }
        System.out.println("\n" + "0. Go back");
    }

    private static String chooseChannel(List<Channels> channelsOfUser) {
        showChannels(channelsOfUser);

        int index = Input.inputInt("Choose (0 - exit) : ") - 1;

        if (index != -1 && index < channelsOfUser.size()) {
            return channelsOfUser.get(index).getId();
        }

        return null;
    }

    private static void showChannels(List<Channels> channelsOfUser) {
        System.out.println("Channels : ");
        int i = 1;
        for (Channels channel : channelsOfUser) {
            System.out.println(i++ + ". " + channel + " | members : " + channelUserService.countMembers());
        }
        System.out.println("=====================");
    }

    private static void displayMenu() {
        System.out.println("""
                1. Channels
                2. Create a channel
                3. Search a channel
                                
                0. Go back
                """);
    }
}
