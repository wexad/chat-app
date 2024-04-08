package uz.pdp.backend.service.post_service;

import uz.pdp.backend.model.post.Posts;

import java.util.ArrayList;
import java.util.List;

public class PostServiceImpl implements PostService {

    private static PostService postService;

    private List<Posts> posts;

    public PostServiceImpl() {
        this.posts = new ArrayList<>();
    }

    public static PostService getInstance() {
        if (postService == null) {
            postService = new PostServiceImpl();
        }

        return postService;
    }
}
