package io.github.pengxianggui.server.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import io.github.pengxianggui.crud.BaseServiceImpl;
import io.github.pengxianggui.server.system.mapper.UserRoleRelMapper;
import io.github.pengxianggui.server.system.model.entity.*;
import io.github.pengxianggui.server.system.mapper.UserMapper;
import io.github.pengxianggui.server.system.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl extends BaseServiceImpl<UserMapper, User> implements UserService {
    @Autowired
    private UserRoleRelMapper userRoleRelMapper;

    @Override
    public User getByUsername(String username) {
        return baseMapper.selectOne(new LambdaQueryWrapper<User>().eq(User::getUsername, username));
    }

    @Override
    public List<Role> getRolesOfUser(Long userId) {
        return userRoleRelMapper.selectJoinList(Role.class,
                new MPJLambdaWrapper<UserRoleRel>()
                        .selectAll(Role.class)
                        .leftJoin(Role.class, Role::getId, UserRoleRel::getRoleId)
                        .eq(UserRoleRel::getUserId, userId));
    }

    @Override
    public List<Auth> getAuthsOfUser(Long userId) {
        return userRoleRelMapper.selectJoinList(Auth.class,
                new MPJLambdaWrapper<UserRoleRel>()
                        .selectAll(Auth.class)
                        .leftJoin(Role.class, Role::getId, UserRoleRel::getRoleId)
                        .leftJoin(RoleAuthRel.class, RoleAuthRel::getRoleId, Role::getId)
                        .leftJoin(Auth.class, Auth::getId, RoleAuthRel::getAuthId)
                        .eq(UserRoleRel::getUserId, userId)
        );
    }
}
