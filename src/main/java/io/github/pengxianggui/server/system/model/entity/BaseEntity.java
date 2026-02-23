package io.github.pengxianggui.server.system.model.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.gitee.sunchenbin.mybatis.actable.annotation.Column;
import com.gitee.sunchenbin.mybatis.actable.annotation.ColumnType;
import com.gitee.sunchenbin.mybatis.actable.annotation.IsAutoIncrement;
import com.gitee.sunchenbin.mybatis.actable.constants.MySqlTypeConstant;
import lombok.Data;

import java.util.Date;

/**
 * Entity基类——继承公共字段
 *
 * @author pengxg
 * @date 2026/2/20 23:38
 */
@Data
public class BaseEntity {

    @TableId(type = IdType.AUTO)
    @IsAutoIncrement
    @ColumnType(value = MySqlTypeConstant.BIGINT, length = 20)
    private Long id;

    @TableField(value = "create_by", fill = FieldFill.INSERT)
    @Column(type = MySqlTypeConstant.BIGINT, length = 20, comment = "创建人")
    private Long createBy;

    @TableField(value = "create_name", fill = FieldFill.INSERT)
    @Column(type = MySqlTypeConstant.VARCHAR, length = 20, comment = "创建人姓名")
    private String createName;

    @TableField(value = "create_time", fill = FieldFill.INSERT)
    @Column(type = MySqlTypeConstant.DATETIME, defaultValue = "CURRENT_TIMESTAMP", comment = "创建时间")
    private Date createTime;

    @TableField(value = "update_by", fill = FieldFill.INSERT_UPDATE)
    @Column(type = MySqlTypeConstant.BIGINT, length = 20, comment = "更新人")
    private Long updateBy;

    @TableField(value = "update_name", fill = FieldFill.INSERT_UPDATE)
    @Column(type = MySqlTypeConstant.VARCHAR, length = 20, comment = "更新人姓名")
    private String updateName;

    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    @Column(type = MySqlTypeConstant.DATETIME, defaultValue = "CURRENT_TIMESTAMP", comment = "更新时间")
    private Date updateTime;

    @TableField
    @Column(type = MySqlTypeConstant.TINYINT, defaultValue = "0", length = 1, comment = "是否删除(0-否;1-是)")
    @TableLogic(value = "0", delval = "1")
    private Boolean deleted = false;
}
