package uz.pdp.backend.service.user_service;

import uz.pdp.backend.model.user.Users;

import java.util.ArrayList;
import java.util.List;

public class UserServiceImpl implements UserService {


    private static UserService userService;

    private List<Users> users;

    public UserServiceImpl() {
        this.users = new ArrayList<>();
    }

    public static UserService getInstance() {
        if (userService == null) {
            userService = new UserServiceImpl();
        }

        return userService;
    }

    @Override
    public boolean add(Users o) {
        return false;
    }

    @Override
    public boolean delete(String id) {
        return false;
    }

    @Override
    public Users get(String id) {
        return null;
    }

    @Override
    public List<Users> getList() {
        return null;
    }
}
