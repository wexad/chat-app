package uz.pdp.backend.service.file_service;

import uz.pdp.backend.model.channel.ChannelUsers;
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

import java.io.*;
import java.util.List;

public class FileServiceImpl implements FileService {
    static FileService fileService;

    static UserService userService = UserServiceImpl.getInstance();
    static ContactService contactService = ContactServiceImpl.getInstance();
    static ChatService chatService = ChatServiceImpl.getInstance();
    static MessageService messageService = MessageServiceImpl.getInstance();
    static GroupUserService groupUserService = GroupUserServiceImpl.getInstance();
    static GroupService groupService = GroupServiceImpl.getInstance();
    static ChannelService channelService = ChannelServiceImpl.getInstance();
    static ChannelUserService channelUserService = ChannelUserServiceImpl.getInstance();
    static PostService postService = PostServiceImpl.getInstance();

    public static FileService getInstance() {
        if (fileService == null) {
            fileService = new FileServiceImpl();
        }
        return fileService;
    }


    @Override
    public void saveUsers() {
        try (
                OutputStream outputStream = new FileOutputStream("src/uz/pdp/db/users/users_db.txt");
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
        ) {
            objectOutputStream.writeObject(userService.getList());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void saveContacts() {
        try (
                OutputStream outputStream = new FileOutputStream("src/uz/pdp/db/contacts/contacts_db.txt");
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
        ) {
            objectOutputStream.writeObject(contactService.getList());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void saveChats() {
        try (
                OutputStream outputStream = new FileOutputStream("src/uz/pdp/db/chats/chats_db.txt");
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
        ) {
            objectOutputStream.writeObject(chatService.getList());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void saveMessages() {
        try (
                OutputStream outputStream = new FileOutputStream("src/uz/pdp/db/messages/messages_db.txt");
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
        ) {
            objectOutputStream.writeObject(messageService.getList());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void saveGroupUsers() {
        try (
                OutputStream outputStream = new FileOutputStream("src/uz/pdp/db/groups/group_users/group_users_db.txt");
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
        ) {
            objectOutputStream.writeObject(groupUserService.getList());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void saveGroups() {
        try (
                OutputStream outputStream = new FileOutputStream("src/uz/pdp/db/groups/groups_db.txt");
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
        ) {
            objectOutputStream.writeObject(groupService.getList());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void saveChannels() {
        try (
                OutputStream outputStream = new FileOutputStream("src/uz/pdp/db/channels/channels_db.txt");
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
        ) {
            objectOutputStream.writeObject(channelService.getList());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void saveChannelUsers() {
        try (
                OutputStream outputStream = new FileOutputStream("src/uz/pdp/db/channels/channel_users/channel_users_db.txt");
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
        ) {
            objectOutputStream.writeObject(channelUserService.getList());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void savePosts() {
        try (
                OutputStream outputStream = new FileOutputStream("src/uz/pdp/db/posts/posts_db.txt");
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
        ) {
            objectOutputStream.writeObject(postService.getList());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void loadChannelUsers() {
        try (
                InputStream inputStream = new FileInputStream("src/uz/pdp/db/channels/channel_users/channel_users_db.txt");
                ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
        ) {
            List<ChannelUsers> list = channelUserService.getList();

            list.addAll((List<ChannelUsers>) objectInputStream.readObject());
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("(messages_db.txt is empty)");
        }
    }
}
