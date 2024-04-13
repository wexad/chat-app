package uz.pdp.backend.service.message_service;

import uz.pdp.backend.enums.MessageType;
import uz.pdp.backend.model.chat.Chats;
import uz.pdp.backend.model.message.Messages;

import java.util.ArrayList;
import java.util.List;

public class MessageServiceImpl implements MessageService {

    private static MessageService messageService;

    private List<Messages> messages;

    public MessageServiceImpl() {
        this.messages = new ArrayList<>();
    }

    public static MessageService getInstance() {
        if (messageService == null) {
            messageService = new MessageServiceImpl();
        }

        return messageService;
    }

    @Override
    public boolean add(Messages o) {
        return messages.add(o);
    }

    @Override
    public boolean delete(Messages message) {
        return messages.remove(message);
    }

    @Override
    public Messages get(String messageId) {
        for (Messages message : messages) {
            if (message.getId().equals(messageId)) {
                return message;
            }
        }
        return null;
    }

    @Override
    public List<Messages> getList() {
        return messages;
    }

    @Override
    public List<Messages> getMessagesOfChat(Chats chat) {
        List<Messages> messagesOfChat = new ArrayList<>();

        for (Messages message : messages) {
            if (message.getType().equals(MessageType.PRIVATE) && message.getToId().equals(chat.getId())) {
                messagesOfChat.add(message);
            }
        }

        return messagesOfChat;
    }

    @Override
    public List<Messages> getMessagesGroupOrChannel(String groupId) {
        List<Messages> messagesList = new ArrayList<>();
        for (Messages message : messages) {
            if (message.getToId().equals(groupId)) {
                messagesList.add(message);
            }
        }
        return messagesList;
    }

    @Override
    public int countNotReadMessages(String fromId, String toId) {
        int count = 0;
        for (Messages message : messages) {
            if (message.getToId().equals(toId) && message.getUserId().equals(fromId) && !message.isRead()) {
                count++;
            }
        }
        return count;
    }
}
