package io.github.pengxianggui.server.system.model.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.gitee.sunchenbin.mybatis.actable.annotation.Column;
import com.gitee.sunchenbin.mybatis.actable.annotation.TableComment;
import com.gitee.sunchenbin.mybatis.actable.constants.MySqlTypeConstant;
import lombok.Data;

/**
 * 角色权限关系实体类
 *
 * @author pengxg
 * @date 2026/2/21 22:53
 */
@TableName("role_auth_rel")
@TableComment("角色权限关系表")
@Data
public class RoleAuthRel extends BaseEntity {
    @TableField
    @Column(type = MySqlTypeConstant.BIGINT, length = 20, comment = "角色ID", isNull = false)
    private Long roleId;
    @TableField
    @Column(type = MySqlTypeConstant.BIGINT, length = 20, comment = "权限ID", isNull = false)
    private Long authId;
}
