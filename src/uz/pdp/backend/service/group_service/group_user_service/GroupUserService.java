package uz.pdp.backend.service.group_service.group_user_service;

import uz.pdp.backend.model.group.GroupUsers;
import uz.pdp.backend.model.user.Users;
import uz.pdp.backend.service.base_service.BaseService;

import java.util.List;

public interface GroupUserService extends BaseService<GroupUsers> {
    int getCountOfMembers(String id);

    boolean isAdmin(String id, String groupId);

    List<Users> getMembers(String groupId);

    void deleteByMemberId(String id, String groupId);

    void deleteAllMembers(String groupId);

    int countAdmins(String groupId);

    List<Users> getAdminsWithinMe(String id);

    void deleteAdminStatus(String id, String groupId);
}
