package uz.pdp.backend.service.group_service.group_user_service;

import uz.pdp.backend.model.group.GroupUsers;

import java.util.ArrayList;
import java.util.List;

public class GroupUserServiceImpl implements GroupUserService {

    private static GroupUserService groupUserService;

    private List<GroupUsers> groupUsers;

    public GroupUserServiceImpl() {
        this.groupUsers = new ArrayList<>();
    }

    public static GroupUserService getInstance() {
        if (groupUserService == null) {
            groupUserService = new GroupUserServiceImpl();
        }

        return groupUserService;
    }

    @Override
    public boolean add(GroupUsers o) {
        return false;
    }

    @Override
    public boolean delete(String id) {
        return false;
    }

    @Override
    public GroupUsers get(String id) {
        return null;
    }

    @Override
    public List<GroupUsers> getList() {
        return null;
    }
}
