package uz.pdp.backend.service.user_service;

import uz.pdp.backend.dto.LoginDTO;
import uz.pdp.backend.model.user.Users;
import uz.pdp.backend.service.base_service.BaseService;

import java.util.Optional;

public interface UserService extends BaseService<Users> {
    boolean checkUnique(String number, String password);

    Users getAndAdd(String name, String number, String nickname, String password);

    Optional<Users> findUser(LoginDTO loginDTO);
}


