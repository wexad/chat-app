package uz.pdp.backend.service.post_service;

import uz.pdp.backend.model.post.Posts;
import uz.pdp.backend.service.file_service.FileService;
import uz.pdp.backend.service.file_service.FileServiceImpl;

import java.util.ArrayList;
import java.util.List;

public class PostServiceImpl implements PostService {

    private static PostService postService;
    static FileService fileService = FileServiceImpl.getInstance();
    private List<Posts> posts;

    public PostServiceImpl() {
        this.posts = new ArrayList<>();
//        fileService.loadPosts();
    }

    public static PostService getInstance() {
        if (postService == null) {
            postService = new PostServiceImpl();
        }
        return postService;
    }

    @Override
    public boolean add(Posts o) {
        return posts.add(o);
    }

    @Override
    public boolean delete(Posts post) {
        return posts.remove(post);
    }

    @Override
    public Posts get(String postId) {
        for (Posts post : posts) {
            if (post.getId().equals(postId)) {
                return post;
            }
        }
        return null;
    }

    @Override
    public List<Posts> getList() {
        return posts;
    }

    @Override
    public List<Posts> getPosts(String channelId) {
        List<Posts> postsList = new ArrayList<>();
        for (Posts post : posts) {
            if (post.getChannelId().equals(channelId)) {
                postsList.add(post);
            }
        }
        return postsList;
    }
}
