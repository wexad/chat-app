package uz.pdp.backend.service.channel_service.channel_user_service;

import uz.pdp.backend.model.channel.ChannelUsers;
import uz.pdp.backend.service.base_service.BaseService;

public interface ChannelUserService extends BaseService<ChannelUsers> {
    int countMembers();

    boolean isAdmin(String id, String channelId);

    void deleteMember(String id, String channelId);
}
