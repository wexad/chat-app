package uz.pdp.backend.service.channel_service.channel_user_service;

import uz.pdp.backend.model.channel.ChannelUsers;
import uz.pdp.backend.model.user.Users;
import uz.pdp.backend.service.file_service.FileService;
import uz.pdp.backend.service.file_service.FileServiceImpl;
import uz.pdp.backend.service.user_service.UserService;
import uz.pdp.backend.service.user_service.UserServiceImpl;

import java.util.ArrayList;
import java.util.List;

public class ChannelUserServiceImpl implements ChannelUserService {
    UserService userService = UserServiceImpl.getInstance();
    private static ChannelUserService channelUserService;

    static FileService fileService = FileServiceImpl.getInstance();

    private List<ChannelUsers> channelUsers;

    public ChannelUserServiceImpl() {
        this.channelUsers = new ArrayList<>();
        fileService.loadChannelUsers();
    }

    public static ChannelUserService getInstance() {
        if (channelUserService == null) {
            channelUserService = new ChannelUserServiceImpl();
        }

        return channelUserService;
    }

    @Override
    public boolean add(ChannelUsers o) {
        return channelUsers.add(o);
    }

    @Override
    public boolean delete(ChannelUsers channelUser) {
        return channelUsers.remove(channelUser);
    }

    @Override
    public ChannelUsers get(String channelUserId) {
        for (ChannelUsers channelUser : channelUsers) {
            if (channelUser.getId().equals(channelUserId)) {
                return channelUser;
            }
        }
        return null;
    }

    @Override
    public List<ChannelUsers> getList() {
        return channelUsers;
    }

    @Override
    public int countMembers(String channelId) {
        int count = 0;
        for (ChannelUsers channelUser : channelUsers) {
            if (channelUser.getChannelId().equals(channelId)) {
                count++;
            }
        }
        return count;
    }

    @Override
    public boolean isAdmin(String userId, String channelId) {
        for (ChannelUsers channelUser : channelUsers) {
            if (channelUser.getUserId().equals(userId) && channelUser.getChannelId().equals(channelId) && channelUser.isAdmin()) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void deleteMember(String userId, String channelId) {
        for (ChannelUsers channelUser : channelUsers) {
            if (channelUser.getUserId().equals(userId) && channelUser.getChannelId().equals(channelId)) {
                channelUsers.remove(channelUser);
            }
        }
    }

    @Override
    public List<Users> getSubscribersWithoutAdmins(String channelId) {
        List<Users> usersList = new ArrayList<>();
        for (ChannelUsers channelUser : channelUsers) {
            if (channelUser.getChannelId().equals(channelId) && !channelUser.isAdmin()) {
                usersList.add(userService.get(channelUser.getUserId()));
            }
        }
        return usersList;
    }

    @Override
    public ChannelUsers getByMemberId(String userId, String channelId) {
        for (ChannelUsers channelUser : channelUsers) {
            if (channelUser.getChannelId().equals(channelId) && channelUser.getUserId().equals(userId)) {
                return channelUser;
            }
        }
        return null;
    }

    @Override
    public List<Users> getAdminsWithoutMe(String channelId, String userId) {
        List<Users> usersList = new ArrayList<>();
        for (ChannelUsers channelUser : channelUsers) {
            if (channelUser.getChannelId().equals(channelId) && !channelUser.getUserId().equals(userId)) {
                usersList.add(userService.get(channelUser.getUserId()));
            }
        }
        return usersList;
    }

    @Override
    public void deleteAllMembers(String channelId) {
        for (ChannelUsers channelUser : channelUsers) {
            if (channelUser.getChannelId().equals(channelId)) {
                channelUsers.remove(channelUser);
            }
        }
    }

    @Override
    public void deleteByUserId(String userId) {
        channelUsers.removeIf(channelUsers1 -> channelUsers1.getUserId().equals(userId));
    }

    @Override
    public boolean isMember(String userId, String channelId) {
        for (ChannelUsers channelUser : channelUsers) {
            if (channelUser.getUserId().equals(userId) && channelUser.getChannelId().equals(channelId)) {
                return true;
            }
        }
        return false;
    }
}
