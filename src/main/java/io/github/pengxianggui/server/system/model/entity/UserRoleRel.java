package io.github.pengxianggui.server.system.model.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.gitee.sunchenbin.mybatis.actable.annotation.Column;
import com.gitee.sunchenbin.mybatis.actable.annotation.TableComment;
import com.gitee.sunchenbin.mybatis.actable.constants.MySqlTypeConstant;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 用户角色关系表
 *
 * @author pengxg
 * @date 2026/2/21 22:50
 */
@TableName("user_role_rel")
@TableComment("用户角色关系表")
@Data
@NoArgsConstructor
public class UserRoleRel extends BaseEntity {
    @TableField
    @Column(type = MySqlTypeConstant.BIGINT, length = 20, comment = "用户ID", isNull = false)
    private Long userId;
    @TableField
    @Column(type = MySqlTypeConstant.BIGINT, length = 20, comment = "角色ID", isNull = false)
    private Long roleId;

    public UserRoleRel(Long userId, Long roleId) {
        this.userId = userId;
        this.roleId = roleId;
    }
}
