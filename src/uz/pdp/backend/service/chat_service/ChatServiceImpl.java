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

}
