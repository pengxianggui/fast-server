package io.github.pengxianggui.server.system.service;

import io.github.pengxianggui.crud.BaseService;
import io.github.pengxianggui.server.system.model.entity.Auth;
import io.github.pengxianggui.server.system.model.entity.Role;
import io.github.pengxianggui.server.system.model.entity.User;

import java.util.List;

public interface UserService extends BaseService<User> {

    User getByUsername(String username);

    List<Role> getRolesOfUser(Long userId);

    List<Auth> getAuthsOfUser(Long userId);
}