package uz.pdp.ui;

import uz.pdp.backend.model.user.Users;
import uz.pdp.backend.service.channel_service.ChannelService;
import uz.pdp.backend.service.channel_service.ChannelServiceImpl;
import uz.pdp.backend.service.channel_service.channel_user_service.ChannelUserService;
import uz.pdp.backend.service.channel_service.channel_user_service.ChannelUserServiceImpl;
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
import uz.pdp.backend.service.post_service.PostService;
import uz.pdp.backend.service.post_service.PostServiceImpl;
import uz.pdp.backend.service.user_service.UserService;
import uz.pdp.backend.service.user_service.UserServiceImpl;
import uz.pdp.ui.utils.Input;
import uz.pdp.ui.utils.Message;
import uz.pdp.ui.views.LoginView;
import uz.pdp.ui.views.MainView;

import static uz.pdp.ui.views.LoginView.loginUser;
import static uz.pdp.ui.views.LoginView.registerUser;

public class Main {

    static UserService userService = UserServiceImpl.getInstance();
    static MessageService messageService = MessageServiceImpl.getInstance();
    static ContactService contactService = ContactServiceImpl.getInstance();
    static ChatService chatService = ChatServiceImpl.getInstance();
    static GroupService groupService = GroupServiceImpl.getInstance();
    static GroupUserService groupUserService = GroupUserServiceImpl.getInstance();
    static ChannelService channelService = ChannelServiceImpl.getInstance();
    static ChannelUserService channelUserService = ChannelUserServiceImpl.getInstance();
    static PostService postService = PostServiceImpl.getInstance();

    public static Users curUser;

    public static void main(String[] args) {
        Integer choice;
        do {
            LoginView.displayMenu();
            choice = Input.inputInt("Your choice : ");
            switch (choice) {
                case 1 -> loginUser();
                case 2 -> registerUser();
                case 0 -> Message.goodbye();
                default -> Message.failure();
            }

            MainView.start();
        } while ( choice != 0);
    }
}
