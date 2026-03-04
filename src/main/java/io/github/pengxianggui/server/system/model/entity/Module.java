package io.github.pengxianggui.server.system.model.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.gitee.sunchenbin.mybatis.actable.annotation.Column;
import com.gitee.sunchenbin.mybatis.actable.annotation.TableComment;
import com.gitee.sunchenbin.mybatis.actable.constants.MySqlTypeConstant;
import lombok.Data;

/**
 * 模块
 *
 * @author pengxg
 * @date 2026/2/24 16:18
 */
@Data
@TableName("module")
@TableComment("模块(权限分组)")
public class Module extends BaseEntity {
    @TableField
    @Column(type = MySqlTypeConstant.VARCHAR, length = 50, comment = "模块名称", isNull = false)
    private String name;
    @TableField
    @Column(type = MySqlTypeConstant.VARCHAR, length = 300, comment = "模块描述")
    private String description;
    @TableField
    @Column(type = MySqlTypeConstant.BIGINT, comment = "父模块ID(为null则表示为根模块)")
    private Long parentId;
}
