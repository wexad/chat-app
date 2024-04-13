package uz.pdp.backend.service.group_service;

import uz.pdp.backend.enums.Type;
import uz.pdp.backend.model.group.GroupUsers;
import uz.pdp.backend.model.group.Groups;
import uz.pdp.backend.service.file_service.FileService;
import uz.pdp.backend.service.file_service.FileServiceImpl;
import uz.pdp.backend.service.group_service.group_user_service.GroupUserService;
import uz.pdp.backend.service.group_service.group_user_service.GroupUserServiceImpl;

import java.util.ArrayList;
import java.util.List;

public class GroupServiceImpl implements GroupService {
    static GroupUserService groupUserService = GroupUserServiceImpl.getInstance();
    static FileService fileService = FileServiceImpl.getInstance();
    private static GroupService groupService;

    private List<Groups> groups;

    public GroupServiceImpl() {
        this.groups = new ArrayList<>();
//        fileService.loadGroups();
    }

    public static GroupService getInstance() {
        if (groupService == null) {
            groupService = new GroupServiceImpl();
        }

        return groupService;
    }

    @Override
    public boolean add(Groups o) {
        return groups.add(o);
    }

    @Override
    public boolean delete(Groups group) {
        return groups.remove(group);
    }

    @Override
    public Groups get(String groupId) {
        for (Groups group : groups) {
            if (group.getId().equals(groupId)) {
                return group;
            }
        }
        return null;
    }

    @Override
    public List<Groups> getList() {
        return groups;
    }

    @Override
    public List<Groups> getGroupsOfUser(String userId) {
        List<Groups> groupsList = new ArrayList<>();
        List<GroupUsers> listOfGroupsByUserId = groupUserService.getListOfGroupsByUserId(userId);
        if (!listOfGroupsByUserId.isEmpty()) {
            for (GroupUsers groupUsersList : listOfGroupsByUserId) {
                groupsList.add(groupService.get(groupUsersList.getGroupId()));
            }
        }
        return groupsList;
    }

    @Override
    public void deleteById(String groupId) {
        groups.removeIf(group -> group.getId().equals(groupId));
    }

    @Override
    public boolean isUnique(String name) {
        for (Groups group : groups) {
            if (group.getName().equals(name)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public List<Groups> getGroupsByWord(String hint) {
        List<Groups> groupsList = new ArrayList<>();
        for (Groups group : groups) {
            if (group.getName().contains(hint) && group.getType() != Type.PRIVATE) {
                groupsList.add(group);
            }
        }
        return groupsList;
    }
}
