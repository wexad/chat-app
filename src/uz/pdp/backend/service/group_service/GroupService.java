package uz.pdp.backend.service.group_service;

import uz.pdp.backend.model.group.Groups;
import uz.pdp.backend.service.base_service.BaseService;

import java.util.List;

public interface GroupService extends BaseService<Groups> {
    List<Groups> getGroupsOfUser(String id);
}
