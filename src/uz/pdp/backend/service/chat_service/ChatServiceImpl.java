package uz.pdp.backend.service.chat_service;

import uz.pdp.backend.model.chat.Chats;

import java.util.ArrayList;
import java.util.List;

public class ChatServiceImpl implements ChatService {

    private static ChatService chatService;

    private List<Chats> chats;

    public ChatServiceImpl() {
        this.chats = new ArrayList<>();
    }

    public static ChatService getInstance() {
        if (chatService == null) {
            chatService = new ChatServiceImpl();
        }

        return chatService;
    }

    @Override
    public boolean add(Chats o) {
        return chats.add(o);
    }

    @Override
    public boolean delete(Chats chat) {
        return chats.remove(chat);
    }


    @Override
    public Chats get(String chatId) {
        for (Chats chat : chats) {
            if (chat.getId().equals(chatId)) {
                return chat;
            }
        }
        return null;
    }

    @Override
    public List<Chats> getList() {
        return chats;
    }
}
