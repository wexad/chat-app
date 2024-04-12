package uz.pdp.backend.service.channel_service;

import uz.pdp.backend.model.channel.Channels;
import uz.pdp.backend.model.user.Users;
import uz.pdp.backend.service.base_service.BaseService;

import java.util.List;

public interface ChannelService extends BaseService<Channels> {
    List<Channels> getChannelsOfUser(String id);
}
