package io.github.pengxianggui.server.system.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import io.github.pengxianggui.crud.BaseServiceImpl;
import io.github.pengxianggui.crud.join.MPJLambdaWrapperBuilder;
import io.github.pengxianggui.crud.query.PagerQuery;
import io.github.pengxianggui.crud.query.PagerView;
import io.github.pengxianggui.server.system.model.entity.Auth;
import io.github.pengxianggui.server.system.mapper.AuthMapper;
import io.github.pengxianggui.server.system.model.entity.Module;
import io.github.pengxianggui.server.system.model.vo.AuthPageVO;
import io.github.pengxianggui.server.system.service.AuthService;
import io.github.pengxianggui.server.system.service.ModuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AuthServiceImpl extends BaseServiceImpl<AuthMapper, Auth> implements AuthService {

    @Autowired
    private ModuleService moduleService;

    @Override
    public PagerView<AuthPageVO> getPageVO(PagerQuery query) {
        Page<AuthPageVO> pager = new Page<>(query.getCurrent(), query.getSize());
        // 0为系统根模块
        Long moduleId = query.getExtra("moduleId", 0L);
        MPJLambdaWrapper<Auth> wrapper = new MPJLambdaWrapperBuilder<Auth>(AuthPageVO.class)
                .query(query)
                .build();
        List<Module> modules = moduleService.getTreeAsFlat(moduleId);
        wrapper.in(CollectionUtil.isNotEmpty(modules), Auth::getModuleId,
                modules.stream().map(Module::getId).collect(Collectors.toSet()));
        Page<AuthPageVO> page = baseMapper.selectJoinPage(pager, AuthPageVO.class, wrapper);
        return PagerView.of(page);
    }
}
