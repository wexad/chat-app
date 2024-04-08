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
        return false;
    }

    @Override
    public boolean delete(String id) {
        return false;
    }

    @Override
    public Channels get(String id) {
        return null;
    }

    @Override
    public List<Channels> getList() {
        return null;
    }
}
