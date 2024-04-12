package uz.pdp.ui.views;

import uz.pdp.backend.enums.Type;
import uz.pdp.backend.model.channel.ChannelUsers;
import uz.pdp.backend.model.channel.Channels;
import uz.pdp.backend.model.post.Posts;
import uz.pdp.backend.model.user.Users;
import uz.pdp.backend.service.channel_service.ChannelService;
import uz.pdp.backend.service.channel_service.ChannelServiceImpl;
import uz.pdp.backend.service.channel_service.channel_user_service.ChannelUserService;
import uz.pdp.backend.service.channel_service.channel_user_service.ChannelUserServiceImpl;
import uz.pdp.backend.service.post_service.PostService;
import uz.pdp.backend.service.post_service.PostServiceImpl;
import uz.pdp.backend.service.user_service.UserService;
import uz.pdp.backend.service.user_service.UserServiceImpl;
import uz.pdp.ui.Main;
import uz.pdp.ui.utils.Input;
import uz.pdp.ui.utils.Message;

import java.time.LocalTime;
import java.util.List;

public class ChannelsView {

    static ChannelService channelService = ChannelServiceImpl.getInstance();

    static UserService userService = UserServiceImpl.getInstance();

    static ChannelUserService channelUserService = ChannelUserServiceImpl.getInstance();

    static PostService postService = PostServiceImpl.getInstance();


    public static void start() {
        Integer choice;
        do {
            displayMenu();
            choice = Input.inputInt("Choice : ");

            List<Channels> channelsOfUser = channelService.getChannelsOfUser(Main.curUser.getId());
            switch (choice) {
                case 1 -> channelMenu(channelsOfUser);

                case 2 -> createChannel();

                case 3 -> searchChannel();
            }
        } while (choice != 0);
    }

    private static void searchChannel() {
        String search = Input.inputStr("Search : ");

        List<Channels> channelsByWord = channelService.getChannelsByWord(search);

        channelMenu(channelsByWord);
    }

    private static void createChannel() {
        String name = Input.inputStr("Enter name : ");

        if (!channelService.isUniqueName(name)) {
            System.out.println("There is not unique name ! Do you want try again ? 1 yes / 0 no");

            if (Input.inputInt("Choice : ") == 1) {
                createChannel();
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

            String description = Input.inputStr("Enter description : ");

            Channels channel = new Channels(name, description, type);
            channelService.add(channel);
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

    private static void channelMenu(List<Channels> channels) {
        while (true) {
            String channelId = chooseChannel(channels);

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
                    case 1 -> sendMessage(channelId);

                    case 2 -> makeAdmin(channelId);

                    case 3 -> deleteAdmin(channelId);

                    case 4 -> renameChannel(channelId);

                    case 5 -> deleteChannel(channelId);
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

    private static void deleteChannel(String channelId) {
        channelUserService.deleteAllMembers(channelId);

        channelService.deleteById(channelId);

        Message.success();
    }

    private static void renameChannel(String channelId) {
        Channels channels = channelService.get(channelId);

        String name = Input.inputStr("Enter new name " + channels.getName() + " --> (0 - cancel) : ");

        if (name.equals("0")) {
            return;
        }

        channels.setName(name);

        Message.success();
    }

    private static void deleteAdmin(String channelId) {
        List<Users> admins = channelUserService.getAdmins(channelId);

        showUsers(admins);

        int index = Input.inputInt("Choose (0 - cancel) : ") - 1;

        if (index != -1 && index < admins.size()) {
            ChannelUsers bySubscriberId = channelUserService.getByMemberId(admins.get(index).getId());

            bySubscriberId.setAdmin(false);
        }
    }

    private static void makeAdmin(String channelId) {
        List<Users> subscribers = channelUserService.getSubscribers(channelId);

        showUsers(subscribers);

        int index = Input.inputInt("Choose (0 - cancel) : ") - 1;

        if (index != -1 && index < subscribers.size()) {
            ChannelUsers bySubscriberId = channelUserService.getByMemberId(subscribers.get(index).getId());

            bySubscriberId.setAdmin(true);
        }
    }

    private static void showUsers(List<Users> subscribers) {
        System.out.println("Subscribers : ");
        int i = 1;
        for (Users subscriber : subscribers) {
            System.out.println(i++ + ". " + subscriber);
        }
        System.out.println("=========================");
    }

    private static void sendMessage(String channelId) {
        while (true) {

            showPosts(channelId);

            String text = Input.inputStr("Type to send message (0 - exit) : ");

            if (text.equals("0")) {
                return;
            }

            Posts post = new Posts(text, Main.curUser.getId(), channelId, 0, LocalTime.now());

            postService.add(post);

            Message.success();

            System.out.println("Sent! ");
        }
    }

    private static void exitFromChannel(String channelId) {
        channelUserService.deleteMember(Main.curUser.getId(), channelId);

        Message.success();
    }

    private static void checkChannel(String channelId) {
        while (true) {
            System.out.println(channelService.get(channelId));

            showPosts(channelId);

            String s = Input.inputStr("Press any button to exit");

            if (s != null) {
                return;
            }
        }
    }

    private static void showPosts(String channelId) {
        List<Posts> posts = postService.getPosts(channelId);

        for (Posts post : posts) {
            if (post.getUserId().equals(Main.curUser.getId())) {
                System.out.println("\t".repeat(6) + post);
            } else {
                System.out.println(post + " from : " + userService.get(post.getUserId()) + " views : " + post.getCountOfViews());
                post.setCountOfViews(post.getCountOfViews() + 1);
            }
        }
        System.out.println("===================");
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
