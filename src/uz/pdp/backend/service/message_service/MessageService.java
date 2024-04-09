package uz.pdp.backend.service.message_service;

import uz.pdp.backend.model.chat.Chats;
import uz.pdp.backend.model.message.Messages;
import uz.pdp.backend.service.base_service.BaseService;

import java.util.List;

public interface MessageService extends BaseService<Messages> {
    List<Messages> getMessagesOfChat(Chats chat);
}
