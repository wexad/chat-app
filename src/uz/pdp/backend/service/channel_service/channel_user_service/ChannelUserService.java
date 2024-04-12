package uz.pdp.backend.service.channel_service.channel_user_service;

import uz.pdp.backend.model.channel.ChannelUsers;
import uz.pdp.backend.model.user.Users;
import uz.pdp.backend.service.base_service.BaseService;

import java.util.List;

public interface ChannelUserService extends BaseService<ChannelUsers> {
    int countMembers();

    boolean isAdmin(String id, String channelId);

    void deleteMember(String id, String channelId);

    List<Users> getSubscribers(String channelId);

    ChannelUsers getByMemberId(String id);

    List<Users> getAdmins(String channelId);

    void deleteAllMembers(String channelId);

    void deleteByUserId(String id);
}
