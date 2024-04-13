package uz.pdp.backend.service.group_service.group_user_service;

import uz.pdp.backend.model.group.GroupUsers;
import uz.pdp.backend.model.user.Users;
import uz.pdp.backend.service.file_service.FileService;
import uz.pdp.backend.service.file_service.FileServiceImpl;
import uz.pdp.backend.service.user_service.UserService;
import uz.pdp.backend.service.user_service.UserServiceImpl;

import java.util.ArrayList;
import java.util.List;

public class GroupUserServiceImpl implements GroupUserService {
    UserService userServiceImpl = UserServiceImpl.getInstance();

    private static GroupUserService groupUserService;
    static UserService userService = UserServiceImpl.getInstance();
    static FileService fileService = FileServiceImpl.getInstance();

    private List<GroupUsers> groupUsers;

    public GroupUserServiceImpl() {
        this.groupUsers = new ArrayList<>();
        fileService.loadGroupUsers();
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
    public int getCountOfMembers(String groupId) {
        int count = 0;
        for (GroupUsers groupUser : groupUsers) {
            if (groupUser.getGroupId().equals(groupId)) {
                count++;
            }
        }
        return count;
    }

    @Override
    public boolean isAdmin(String userId, String groupId) {
        for (GroupUsers groupUser : groupUsers) {
            if (groupUser.getUserId().equals(userId) && groupUser.getGroupId().equals(groupId) && groupUser.isAdmin()) {
                return true;
            }
        }
        return false;
    }

    @Override
    public List<Users> getMembers(String groupId, String userId) {
        List<Users> users = new ArrayList<>();
        for (GroupUsers groupUser : groupUsers) {
            if (groupUser.getGroupId().equals(groupId) && !groupUser.getUserId().equals(userId)) {
                users.add(userService.get(groupUser.getUserId()));
            }
        }
        return users;
    }

    @Override
    public void deleteByMemberId(String userId, String groupId) {
        groupUsers.removeIf(groupUsers1 -> groupUsers1.getUserId().equals(userId) && groupUsers1.getGroupId().equals(groupId));
    }

    @Override
    public void deleteAllMembers(String groupId) {
        groupUsers.removeIf(groupUsers1 -> groupUsers1.getGroupId().equals(groupId));
    }

    @Override
    public List<GroupUsers> getListOfGroupsByUserId(String userId) {
        List<GroupUsers> groupUsersList = new ArrayList<>();
        for (GroupUsers groupUser : groupUsers) {
            if (userId.equals(groupUser.getUserId())) {
                groupUsersList.add(groupUser);
            }
        }
        return groupUsersList;
    }

    @Override
    public boolean isMember(String contactId, String groupId) {
        for (GroupUsers groupUser : groupUsers) {
            if (groupUser.getGroupId().equals(groupId) && groupUser.getUserId().equals(contactId)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public List<Users> getAdminsWithoutMe(String userId, String groupId) {
        List<Users> usersList = new ArrayList<>();
        for (GroupUsers groupUser : groupUsers) {
            if ((!groupUser.getUserId().equals(userId)) && groupUser.getGroupId().equals(groupId) && groupUser.isAdmin()) {
                usersList.add(userServiceImpl.get(groupUser.getUserId()));
            }
        }
        return usersList;
    }

    @Override
    public void deleteAdminStatus(String userId, String groupId) {
        for (GroupUsers groupUser : groupUsers) {
            if (groupUser.getUserId().equals(userId) && groupUser.getGroupId().equals(groupId)) {
                groupUser.setAdmin(false);
            }
        }
    }

    @Override
    public int countAdmins(String groupId) {
        int count = 0;
        for (GroupUsers groupUser : groupUsers) {
            if (groupUser.getGroupId().equals(groupId) && groupUser.isAdmin()) {
                count++;
            }
        }
        return count;
    }

    @Override
    public void deleteByUserId(String userId) {
        groupUsers.removeIf(groupUsers1 -> groupUsers1.getUserId().equals(userId));
    }
}
