package io.github.pengxianggui.server.system.service;

import io.github.pengxianggui.crud.BaseService;
import io.github.pengxianggui.server.system.model.entity.Auth;
import io.github.pengxianggui.server.system.model.entity.Role;
import io.github.pengxianggui.server.system.model.entity.User;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Set;

public interface UserService extends BaseService<User> {

    User getByUsername(String username);

    List<Role> getRolesOfUser(Long userId);

    List<Auth> getAuthsOfUser(Long userId);

    boolean setRoleForUser(Long id, @NotNull Set<Long> roleIds);
}