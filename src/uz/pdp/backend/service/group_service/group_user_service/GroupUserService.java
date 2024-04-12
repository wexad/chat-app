package uz.pdp.backend.service.group_service.group_user_service;

import uz.pdp.backend.model.group.GroupUsers;
import uz.pdp.backend.model.user.Users;
import uz.pdp.backend.service.base_service.BaseService;

import java.util.List;

public interface GroupUserService extends BaseService<GroupUsers> {
    int getCountOfMembers(String groupId);

    boolean isAdmin(String userId, String groupId);

    List<Users> getMembers(String groupId);

    void deleteByMemberId(String userId, String groupId);

    void deleteAllMembers(String groupId);
    List<GroupUsers> getListOfGroupsByUserId(String userId);

    boolean isMember(String userId, String groupId);

    List<Users> getAdminsWithinMe(String id);

    void deleteAdminStatus(String id, String groupId);

    int countAdmins(String groupId);

    void deleteByUserId(String id);
}
