package uz.pdp.backend.model.chat;

import uz.pdp.backend.model.baseModel.BaseModel;

public class Chats extends BaseModel {
    private final String firstUserId;
    private final String secondUserId;

    public Chats(String firstUserId, String secondUserId) {
        this.firstUserId = firstUserId;
        this.secondUserId = secondUserId;
    }

    public String getFirstUserId() {
        return firstUserId;
    }

    public String getSecondUserId() {
        return secondUserId;
    }
}
