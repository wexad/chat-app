package uz.pdp.backend.service.user_service;

import uz.pdp.backend.dto.LoginDTO;
import uz.pdp.backend.model.user.Users;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserServiceImpl implements UserService {


    private static UserService userService;

    private List<Users> users;

    public UserServiceImpl() {
        this.users = new ArrayList<>();
        users.add(new Users("a","a","a","a",LocalTime.now()));
    }

    public static UserService getInstance() {
        if (userService == null) {
            userService = new UserServiceImpl();
        }

        return userService;
    }

    @Override
    public boolean add(Users o) {
        return users.add(o);
    }

    @Override
    public boolean delete(Users user) {
        return users.remove(user);
    }

    @Override
    public Users get(String userId) {
        for (Users user : users) {
            if (user.getId().equals(userId)) {
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
    public boolean checkUnique(String number, String nickname) {
        for (Users user : users) {
            if (user.getNickname().equals(nickname) || user.getNumber().equals(number)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public Users getAndAdd(String name, String number, String nickname, String password) {
        Users user = new Users(name, number, nickname, password, LocalTime.now());
        users.add(user);
        return user;
    }

    @Override
    public Optional<Users> findUser(LoginDTO loginDTO) {
        for (Users user : users) {
            if ((user.getNickname().equals(loginDTO.nickOrNum()) ||
                    user.getNumber().equals(loginDTO.nickOrNum())) &&
                    user.getPassword().equals(loginDTO.password())) {
                return Optional.of(user);
            }
        }
        return Optional.empty();
    }

    @Override
    public String getUserByNumber(String number) {
        for (Users user : users) {
            if (user.getNumber().equals(number)) {
                return user.getId();
            }
        }
        return null;
    }

    @Override
    public List<Users> getUsersByWord(String s, String currUserId) {
        ArrayList<Users> searchedUsers = new ArrayList<>();
        for (Users user : users) {
            if (user.getNickname().contains(s) && !user.getId().equals(currUserId)) {
                searchedUsers.add(user);
            }
        }
        return searchedUsers;
    }

    @Override
    public void deleteByUserId(String id) {

    }
}
