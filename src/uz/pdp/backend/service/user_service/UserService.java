package uz.pdp.backend.service.user_service;

import uz.pdp.backend.model.user.Users;
import uz.pdp.backend.service.base_service.BaseService;

public interface UserService extends BaseService<Users> {
    boolean checkUnique(Integer number, String password);

    Users getAndAdd(String name, Integer number, String nickname, String password);
}


