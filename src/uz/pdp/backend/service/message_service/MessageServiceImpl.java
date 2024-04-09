package uz.pdp.backend.service.message_service;

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
}
