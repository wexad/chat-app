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
}
