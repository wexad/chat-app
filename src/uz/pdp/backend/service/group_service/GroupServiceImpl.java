package uz.pdp.backend.service.group_service;

import uz.pdp.backend.model.group.Groups;

import java.util.ArrayList;
import java.util.List;

public class GroupServiceImpl implements GroupService {

    private static GroupService groupService;

    private List<Groups> groups;

    public GroupServiceImpl() {
        this.groups = new ArrayList<>();
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
    public List<Groups> getGroupsOfUser(String id) {
        return null;
    }

    @Override
    public void deleteById(String groupId) {

    }

    @Override
    public boolean isUnique(String name) {
        return false;
    }

    @Override
    public List<Groups> getGroupsByWord(String search) {
        return null;
    }

    @Override
    public boolean isMember() {
        return false;
    }
}
