package uz.pdp.backend.service.chat_service;

import uz.pdp.backend.model.chat.Chats;
import uz.pdp.backend.service.base_service.BaseService;

import java.util.List;

public interface ChatService extends BaseService<Chats> {
    List<Chats> getChatsOfUser(String userId);
    Chats getChatOfUser(String userId);
    boolean isExist(String userId);
}
