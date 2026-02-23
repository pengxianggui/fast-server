package io.github.pengxianggui.server.system.service.impl;

import io.github.pengxianggui.crud.BaseServiceImpl;
import io.github.pengxianggui.server.system.model.entity.Auth;
import io.github.pengxianggui.server.system.mapper.AuthMapper;
import io.github.pengxianggui.server.system.service.AuthService;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl extends BaseServiceImpl<AuthMapper, Auth> implements AuthService {

}
