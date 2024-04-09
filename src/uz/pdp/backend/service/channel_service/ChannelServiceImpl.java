package uz.pdp.backend.service.channel_service;

import uz.pdp.backend.model.channel.Channels;

import java.util.ArrayList;
import java.util.List;

public class ChannelServiceImpl implements ChannelService {

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
}
