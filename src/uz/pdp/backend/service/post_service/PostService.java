package uz.pdp.backend.service.post_service;

import uz.pdp.backend.model.post.Posts;
import uz.pdp.backend.service.base_service.BaseService;

import java.util.List;

public interface PostService extends BaseService<Posts> {
    List<Posts> getPosts(String channelId);
}
