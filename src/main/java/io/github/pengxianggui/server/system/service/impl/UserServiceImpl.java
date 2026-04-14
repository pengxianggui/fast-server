package io.github.pengxianggui.server.system.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.digest.BCrypt;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import com.google.common.collect.Sets;
import io.github.pengxianggui.crud.BaseServiceImpl;
import io.github.pengxianggui.server.auth.JwtUtils;
import io.github.pengxianggui.server.auth.LoginUser;
import io.github.pengxianggui.server.common.ex.BizException;
import io.github.pengxianggui.server.common.i18n.I18nUtil;
import io.github.pengxianggui.server.system.mapper.AuthMapper;
import io.github.pengxianggui.server.system.mapper.RoleMapper;
import io.github.pengxianggui.server.system.mapper.UserRoleRelMapper;
import io.github.pengxianggui.server.system.model.dto.*;
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
    public LoginResultDTO login(LoginRequest req) {
        User user = getByUsername(req.getUsername());
        Assert.isTrue(user != null, () -> new BizException(I18nUtil.get("auth.username_not_found", req.getUsername())));
        Assert.isTrue(StrUtil.isNotBlank(user.getPassword()) && BCrypt.checkpw(req.getPassword(), user.getPassword()),
                () -> new BizException(I18nUtil.get("auth.invalid_password")));
        List<Role> roles = getRolesOfUser(user.getId());
        List<Auth> auths = getAuthsOfUser(user.getId());
        String token = JwtUtils.createToken(user.getId(), req.getUsername());
        return new LoginResultDTO(token, new LoginInfoDTO(new LoginUser(user, roles, auths)));
    }

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

    @Override
    public void updateUser(Long id, UserUpdateReq req) {
        User user = getById(id);
        Assert.isTrue(user != null, () -> new BizException(I18nUtil.get("user.not_found", id)));
        user.setNickName(req.getNickName());
        user.setRealName(req.getRealName());
        user.setIdCard(req.getIdCard());
        user.setEmail(req.getEmail());
        user.setPhone(req.getPhone());
        saveOrUpdate(user);
    }

    @Override
    public void changePassword(Long id, ChangePasswordReq req) {
        User user = getById(id);
        Assert.isTrue(user != null, () -> new BizException(I18nUtil.get("user.not_found", id)));
        Assert.isTrue(StrUtil.isBlank(user.getPassword()) || BCrypt.checkpw(req.getOldPassword(), user.getPassword()),
                () -> new BizException(I18nUtil.get("auth.invalid_password")));
        user.setPassword(BCrypt.hashpw(req.getNewPassword(), BCrypt.gensalt()));
        saveOrUpdate(user);
    }
}
