package uz.pdp.backend.service.group_service.group_user_service;

import uz.pdp.backend.model.group.GroupUsers;
import uz.pdp.backend.model.user.Users;

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
        return groupUsers.add(o);
    }

    @Override
    public boolean delete(GroupUsers groupUser) {
        return groupUsers.remove(groupUser);
    }

    @Override
    public GroupUsers get(String groupUserId) {
        for (GroupUsers groupUser : groupUsers) {
            return groupUser;
        }
        return null;
    }

    @Override
    public List<GroupUsers> getList() {
        return groupUsers;
    }

    @Override
    public int getCountOfMembers(String id) {
        return 0;
    }

    @Override
    public boolean isAdmin(String id, String groupId) {
        return false;
    }

    @Override
    public List<Users> getMembers(String groupId) {
        return null;
    }

    @Override
    public void deleteByMemberId(String id, String groupId) {

    }

    @Override
    public void deleteAllMembers(String groupId) {

    }

    @Override
    public int countAdmins(String groupId) {
        return 0;
    }

    @Override
    public void deleteAdminStatus(String id, String groupId) {

    }

    @Override
    public List<Users> getAdminsWithinMe(String userId) {
        return null;
    }
}
