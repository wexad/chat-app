package uz.pdp.backend.service.group_service.group_user_service;

import uz.pdp.backend.model.group.GroupUsers;
import uz.pdp.backend.service.base_service.BaseService;

public interface GroupUserService extends BaseService<GroupUsers> {
    int getCountOfMembers(String id);

    boolean isAdmin(String id, String groupId);
}
