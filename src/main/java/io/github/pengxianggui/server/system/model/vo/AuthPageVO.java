package io.github.pengxianggui.server.system.model.vo;

import io.github.pengxianggui.crud.join.JoinMain;
import io.github.pengxianggui.crud.join.LeftJoin;
import io.github.pengxianggui.crud.join.OnCond;
import io.github.pengxianggui.crud.join.RelateTo;
import io.github.pengxianggui.server.system.model.entity.Auth;
import io.github.pengxianggui.server.system.model.entity.Module;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 权限分页VO
 *
 * @author pengxg
 * @date 2026/3/1 17:13
 */
@JoinMain(Auth.class)
@LeftJoin(value = Module.class, on = @OnCond(field = "id", targetField = "moduleId"))
@NoArgsConstructor
@Data
public class AuthPageVO {
    private Long id;
    private String code;
    private String name;
    private String description;
    private Long moduleId;
    @RelateTo(value = Module.class, field = "name")
    private String moduleName;
    private Date createTime;
    private String createName;
    private String updateName;
    private Date updateTime;
}
