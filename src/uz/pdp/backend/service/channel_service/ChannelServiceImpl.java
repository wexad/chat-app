package uz.pdp.backend.service.channel_service;

import uz.pdp.backend.enums.Type;
import uz.pdp.backend.model.channel.ChannelUsers;
import uz.pdp.backend.model.channel.Channels;
import uz.pdp.backend.model.user.Users;
import uz.pdp.backend.service.channel_service.channel_user_service.ChannelUserService;
import uz.pdp.backend.service.channel_service.channel_user_service.ChannelUserServiceImpl;

import java.util.ArrayList;
import java.util.List;

public class ChannelServiceImpl implements ChannelService {
    ChannelUserService channelUserService = ChannelUserServiceImpl.getInstance();

    private static ChannelService channelService;

    private List<Channels> channels;

    public ChannelServiceImpl() {
        this.channels = new ArrayList<>();
    }

    public static ChannelService getInstance() {
        if (channelService == null) {
            channelService = new ChannelServiceImpl();
        }

        return channelService;
    }

    @Override
    public boolean add(Channels o) {
        return channels.add(o);
    }

    @Override
    public boolean delete(Channels channel) {
        return channels.remove(channel);
    }

    @Override
    public Channels get(String channelId) {
        for (Channels channel : channels) {
            if (channel.getId().equals(channelId)) {
                return channel;
            }
        }
        return null;
    }

    @Override
    public List<Channels> getList() {
        return channels;
    }

    @Override
    public List<Channels> getChannelsOfUser(String userId) {
        List<Channels> channelsList = new ArrayList<>();
        for (ChannelUsers channelUsers : channelUserService.getList()) {
            if (channelUsers.getUserId().equals(userId)) {
                channelsList.add(channelService.get(channelUsers.getChannelId()));
            }
        }
        return channelsList;
    }

    @Override
    public void deleteById(String channelId) {
        for (Channels channel : channels) {
            if (channel.getId().equals(channelId)) {
                channels.remove(channel);
            }
        }
    }

    @Override
    public List<Channels> getChannelsByWord(String search) {
        List<Channels> channelsList = new ArrayList<>();
        for (Channels channel : channels) {
            if (channel.getName().contains(search) && channel.getType().equals(Type.PUBLIC)) {
                channelsList.add(channel);
            }
        }
        return channelsList;
    }

    @Override
    public boolean isUniqueName(String name) {
        for (Channels channel : channels) {
            if (channel.getName().equals(name)) {
                return false;
            }
        }
        return true;
    }

}
