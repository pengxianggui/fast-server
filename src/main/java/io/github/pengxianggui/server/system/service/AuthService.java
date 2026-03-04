package io.github.pengxianggui.server.system.service;

import io.github.pengxianggui.crud.BaseService;
import io.github.pengxianggui.crud.query.PagerQuery;
import io.github.pengxianggui.crud.query.PagerView;
import io.github.pengxianggui.server.system.model.entity.Auth;
import io.github.pengxianggui.server.system.model.vo.AuthPageVO;

public interface AuthService extends BaseService<Auth> {

    PagerView<AuthPageVO> getPageVO(PagerQuery query);
}