package uz.pdp.backend.model.channel;

import uz.pdp.backend.model.baseModel.BaseModel;

public class ChannelUsers extends BaseModel {
    private final String userId;
    private final String channelId;
    private boolean isAdmin;

    public ChannelUsers(String userId, String channelId, boolean isAdmin) {
        this.userId = userId;
        this.channelId = channelId;
        this.isAdmin = isAdmin;
    }

    public String getUserId() {
        return userId;
    }

    public String getChannelId() {
        return channelId;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }
}
