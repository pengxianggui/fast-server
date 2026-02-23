package io.github.pengxianggui.server.common.mybatis;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import io.github.pengxianggui.server.auth.SecurityUtil;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * mybatis审计字段自动填充
 *
 * @author pengxg
 * @date 2026/2/20 23:56
 */
@Component
public class AuditFieldMetaObjectHandler implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {
        this.strictInsertFill(metaObject, "createTime", Date.class, new Date());
        this.strictInsertFill(metaObject, "updateTime", Date.class, new Date());
        final Long loginUserId = SecurityUtil.getLoginUserId();
        final String loginUsername = SecurityUtil.getLoginUsername();
        this.strictInsertFill(metaObject, "createBy", Long.class, loginUserId);
        this.strictInsertFill(metaObject, "updateBy", Long.class, loginUserId);
        this.strictInsertFill(metaObject, "createName", String.class, loginUsername);
        this.strictInsertFill(metaObject, "updateName", String.class, loginUsername);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        metaObject.setValue("updateTime", new Date());
        metaObject.setValue("updateBy", SecurityUtil.getLoginUserId());
        metaObject.setValue("updateName", SecurityUtil.getLoginUsername());
    }
}
