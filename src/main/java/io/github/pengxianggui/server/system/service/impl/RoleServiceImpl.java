package io.github.pengxianggui.server.system.service.impl;

import io.github.pengxianggui.crud.BaseServiceImpl;
import io.github.pengxianggui.server.system.mapper.UserRoleRelMapper;
import io.github.pengxianggui.server.system.model.entity.Role;
import io.github.pengxianggui.server.system.mapper.RoleMapper;
import io.github.pengxianggui.server.system.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl extends BaseServiceImpl<RoleMapper, Role> implements RoleService {

}
