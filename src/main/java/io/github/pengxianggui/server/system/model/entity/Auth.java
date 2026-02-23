package io.github.pengxianggui.server.system.model.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.gitee.sunchenbin.mybatis.actable.annotation.Column;
import com.gitee.sunchenbin.mybatis.actable.annotation.TableComment;
import com.gitee.sunchenbin.mybatis.actable.constants.MySqlTypeConstant;
import lombok.Data;

/**
 * 权限实体类
 *
 * @author pengxg
 * @date 2026/2/21 22:51
 */
@TableName("auth")
@TableComment("权限表")
@Data
public class Auth extends BaseEntity {
    @TableField
    @Column(type = MySqlTypeConstant.VARCHAR, length = 50, comment = "权限编码", isNull = false)
    private String code;
    @TableField
    @Column(type = MySqlTypeConstant.VARCHAR, length = 50, comment = "权限名称", isNull = false)
    private String name;
    @TableField
    @Column(type = MySqlTypeConstant.VARCHAR, length = 300, comment = "权限描述")
    private String description;
}
