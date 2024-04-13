package uz.pdp.backend.service.file_service;

import uz.pdp.backend.model.channel.ChannelUsers;
import uz.pdp.backend.model.channel.Channels;
import uz.pdp.backend.model.chat.Chats;
import uz.pdp.backend.model.contact.Contacts;
import uz.pdp.backend.model.group.GroupUsers;
import uz.pdp.backend.model.group.Groups;
import uz.pdp.backend.model.message.Messages;
import uz.pdp.backend.model.post.Posts;
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
            System.out.println("(channel_users_db.txt is empty)");
        }
    }

    @Override
    public void loadUsers() {
        try (
                InputStream inputStream = new FileInputStream("src/uz/pdp/db/users/users_db.txt");
                ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
        ) {
            List<Users> list = userService.getList();
            list.addAll((List<Users>) objectInputStream.readObject());
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("(users_db.txt is empty)");
        }
    }

    @Override
    public void loadGroupUsers() {
        try (
                InputStream inputStream = new FileInputStream("src/uz/pdp/db/groups/group_users/group_users_db.txt");
                ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
        ) {
            List<GroupUsers> list = groupUserService.getList();
            list.addAll((List<GroupUsers>) objectInputStream.readObject());
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("(group_users_db.txt is empty)");
        }
    }

    @Override
    public void loadGroups() {
        try (
                InputStream inputStream = new FileInputStream("src/uz/pdp/db/groups/groups_db.txt");
                ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
        ) {
            List<Groups> list = groupService.getList();
            list.addAll((List<Groups>) objectInputStream.readObject());
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("(groups_db.txt is empty)");
        }
    }

    @Override
    public void loadMessages() {
        try (
                InputStream inputStream = new FileInputStream("src/uz/pdp/db/messages/messages_db.txt");
                ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
        ) {
            List<Messages> list = messageService.getList();
            list.addAll((List<Messages>) objectInputStream.readObject());
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("(messages_db.txt is empty)");
        }
    }

    @Override
    public void loadPosts() {
        try (
                InputStream inputStream = new FileInputStream("src/uz/pdp/db/posts/posts_db.txt");
                ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
        ) {
            List<Posts> list = postService.getList();
            list.addAll((List<Posts>) objectInputStream.readObject());
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("(posts_db.txt is empty)");
        }
    }

    @Override
    public void loadContacts() {
        try (
                InputStream inputStream = new FileInputStream("src/uz/pdp/db/contacts/contacts_db.txt");
                ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
        ) {
            List<Contacts> list = contactService.getList();
            list.addAll((List<Contacts>) objectInputStream.readObject());
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("(contacts_db.txt is empty)");
        }
    }

    @Override
    public void loadChats() {
        try (
                InputStream inputStream = new FileInputStream("src/uz/pdp/db/chats/chats_db.txt");
                ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
        ) {
            List<Chats> list = chatService.getList();
            list.addAll((List<Chats>) objectInputStream.readObject());
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("(chats_db.txt is empty)");
        }
    }

    @Override
    public void loadChannels() {
        try (
                InputStream inputStream = new FileInputStream("src/uz/pdp/db/channels/channels_db.txt");
                ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
        ) {
            List<Channels> list = channelService.getList();
            list.addAll((List<Channels>) objectInputStream.readObject());
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("(channels_.txt is empty)");
        }
    }
}
