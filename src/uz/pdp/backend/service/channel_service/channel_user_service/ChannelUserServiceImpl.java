package uz.pdp.backend.service.channel_service.channel_user_service;

import uz.pdp.backend.model.channel.ChannelUsers;

import java.util.ArrayList;
import java.util.List;

public class ChannelUserServiceImpl implements ChannelUserService {
    private static ChannelUserService channelUserService;

    private List<ChannelUsers> channelUsers;

    public ChannelUserServiceImpl() {
        this.channelUsers = new ArrayList<>();
    }

    public static ChannelUserService getInstance() {
        if (channelUserService == null) {
            channelUserService = new ChannelUserServiceImpl();
        }

        return channelUserService;
    }

    @Override
    public boolean add(ChannelUsers o) {
        return false;
    }

    @Override
    public boolean delete(String id) {
        return false;
    }

    @Override
    public ChannelUsers get(String id) {
        return null;
    }

    @Override
    public List<ChannelUsers> getList() {
        return null;
    }
}
