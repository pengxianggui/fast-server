package io.github.pengxianggui.server.system.model.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.gitee.sunchenbin.mybatis.actable.annotation.Column;
import com.gitee.sunchenbin.mybatis.actable.annotation.TableComment;
import com.gitee.sunchenbin.mybatis.actable.annotation.Unique;
import com.gitee.sunchenbin.mybatis.actable.constants.MySqlTypeConstant;
import lombok.Data;

/**
 * 角色实体类
 *
 * @author pengxg
 * @date 2026/2/21 22:47
 */
@TableName("role")
@TableComment("角色表")
@Data
public class Role extends BaseEntity {
    @Unique
    @TableField
    @Column(type = MySqlTypeConstant.VARCHAR, length = 50, comment = "角色编码", isNull = false)
    private String code;
    @TableField
    @Column(type = MySqlTypeConstant.VARCHAR, length = 50, comment = "角色名称", isNull = false)
    private String name;
    @TableField
    @Column(type = MySqlTypeConstant.VARCHAR, length = 300, comment = "角色描述")
    private String description;
}
