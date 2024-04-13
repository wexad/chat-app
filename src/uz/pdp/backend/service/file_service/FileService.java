package uz.pdp.backend.service.file_service;

import uz.pdp.backend.model.channel.ChannelUsers;

import java.util.Collection;
import java.util.List;

public interface FileService {
    void saveUsers();

    void saveContacts();

    void saveChats();

    void saveMessages();

    void saveGroupUsers();

    void saveGroups();

    void saveChannels();

    void saveChannelUsers();

    void savePosts();

    void loadChannelUsers();

    void loadUsers();

    void loadGroupUsers();

    void loadGroups();

    void loadMessages();

    void loadPosts();

    void loadContacts();

    void loadChats();

    void loadChannels();

}
