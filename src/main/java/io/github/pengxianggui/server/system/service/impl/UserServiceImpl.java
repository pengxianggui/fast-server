package io.github.pengxianggui.server.system.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import com.google.common.collect.Sets;
import io.github.pengxianggui.crud.BaseServiceImpl;
import io.github.pengxianggui.server.system.mapper.AuthMapper;
import io.github.pengxianggui.server.system.mapper.RoleMapper;
import io.github.pengxianggui.server.system.mapper.UserRoleRelMapper;
import io.github.pengxianggui.server.system.model.entity.*;
import io.github.pengxianggui.server.system.mapper.UserMapper;
import io.github.pengxianggui.server.system.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl extends BaseServiceImpl<UserMapper, User> implements UserService {
    @Autowired
    private UserRoleRelMapper userRoleRelMapper;
    @Autowired
    private AuthMapper authMapper;
    @Autowired
    private RoleMapper roleMapper;

    @Override
    public User getByUsername(String username) {
        return baseMapper.selectOne(new LambdaQueryWrapper<User>().eq(User::getUsername, username));
    }

    @Override
    public List<Role> getRolesOfUser(Long userId) {
        return roleMapper.selectJoinList(Role.class,
                new MPJLambdaWrapper<Role>()
                        .selectAll(Role.class)
                        .leftJoin(UserRoleRel.class, UserRoleRel::getRoleId, Role::getId)
                        .eq(UserRoleRel::getUserId, userId));
    }

    @Override
    public List<Auth> getAuthsOfUser(Long userId) {
        return authMapper.selectJoinList(Auth.class,
                new MPJLambdaWrapper<Auth>()
                        .selectAll(Auth.class)
                        .leftJoin(RoleAuthRel.class, RoleAuthRel::getAuthId, Auth::getId)
                        .leftJoin(Role.class, Role::getId, RoleAuthRel::getRoleId)
                        .leftJoin(UserRoleRel.class, UserRoleRel::getRoleId, Role::getId)
                        .eq(UserRoleRel::getUserId, userId)
        );
    }

    @Override
    public boolean setRoleForUser(Long id, Set<Long> roleIds) {
        List<Role> roles = getRolesOfUser(id);
        Set<Long> existRoleIds = roles.stream().map(Role::getId).collect(Collectors.toSet());
        Set<Long> addList = Sets.difference(roleIds, existRoleIds);
        List<UserRoleRel> addRelList = addList.stream().map(roleId -> new UserRoleRel(id, roleId))
                .collect(Collectors.toList());
        if (CollectionUtil.isNotEmpty(addRelList)) {
            userRoleRelMapper.insert(addRelList);
        }
        Set<Long> deleteList = Sets.difference(existRoleIds, roleIds);
        if (CollectionUtil.isNotEmpty(deleteList)) {
            userRoleRelMapper.delete(new LambdaQueryWrapper<UserRoleRel>().eq(UserRoleRel::getUserId, id)
                    .in(UserRoleRel::getRoleId, deleteList));
        }
        return true;
    }
}
