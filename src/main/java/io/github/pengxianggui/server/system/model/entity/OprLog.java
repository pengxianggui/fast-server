package io.github.pengxianggui.server.system.model.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.gitee.sunchenbin.mybatis.actable.annotation.Column;
import com.gitee.sunchenbin.mybatis.actable.annotation.TableComment;
import com.gitee.sunchenbin.mybatis.actable.constants.MySqlTypeConstant;
import lombok.Data;

import java.util.Date;

/**
 * 操作日志
 *
 * @author pengxg
 * @date 2026/2/22 15:55
 */
@Data
@TableName("opr_log")
@TableComment("操作日志")
public class OprLog extends BaseEntity {
    /**
     * 业务单据编码值
     */
    @TableField
    @Column(type = MySqlTypeConstant.VARCHAR, length = 200, comment = "业务单据编码值")
    private String code;
    /**
     * 操作描述
     */
    @TableField
    @Column(type = MySqlTypeConstant.VARCHAR, length = 200, comment = "操作描述")
    private String operateDesc;
    /**
     * 操作时间
     */
    @TableField
    @Column(type = MySqlTypeConstant.DATETIME, comment = "操作时间")
    private Date operateTime;
    /**
     * 操作来源IP
     */
    @TableField
    @Column(type = MySqlTypeConstant.VARCHAR, length = 20, comment = "操作来源IP")
    private String ip;
    /**
     * 操作终端类型
     */
    @TableField
    @Column(type = MySqlTypeConstant.VARCHAR, length = 20, comment = "操作终端类型")
    private String terminalType;
    /**
     * 操作入参
     */
    @TableField
    @Column(type = MySqlTypeConstant.LONGTEXT, comment = "操作参数")
    private String param;
}
