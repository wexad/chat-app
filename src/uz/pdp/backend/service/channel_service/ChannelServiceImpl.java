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
}
