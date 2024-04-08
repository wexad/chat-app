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
        users.add(o);
        return true;
    }

    @Override
    public boolean delete(Users user) {
        for (Users user1 : users) {
            if (user1.getId().equals(user.getId())) {
                users.remove(user);
                return true;
            }
        }
        return false;
    }

    @Override
    public Users get(String id) {
        for (Users user : users) {
            if (user.getId().equals(id)) {
                return user;
            }
        }
        return null;
    }

    @Override
    public List<Users> getList() {
        return users;
    }

    @Override
    public boolean checkUnique(Integer number, String nickname) {
        for (Users user : users) {
            if (user.getNickname().equals(nickname) || user.getNumber().equals(number)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public Users getAndAdd(String name, Integer number, String nickname, String password) {
        return null;
    }
}
