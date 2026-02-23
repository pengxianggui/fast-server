package io.github.pengxianggui.server.system.model.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.gitee.sunchenbin.mybatis.actable.annotation.Column;
import com.gitee.sunchenbin.mybatis.actable.annotation.TableComment;
import com.gitee.sunchenbin.mybatis.actable.annotation.Unique;
import com.gitee.sunchenbin.mybatis.actable.constants.MySqlTypeConstant;
import lombok.Data;

/**
 * 用户实体类
 *
 * @author pengxg
 * @date 2026/2/21 15:31
 */
@TableName("user")
@TableComment("用户表")
@Data
public class User extends BaseEntity {
    @Unique
    @TableField
    @Column(type = MySqlTypeConstant.VARCHAR, length = 50, comment = "用户名", isNull = false)
    private String username;
    @TableField
    @Column(type = MySqlTypeConstant.VARCHAR, length = 200, comment = "密码")
    private String password;
    @TableField
    @Column(type = MySqlTypeConstant.VARCHAR, length = 20, comment = "手机号")
    private String phone;
    @TableField
    @Column(type = MySqlTypeConstant.VARCHAR, length = 50, comment = "昵称")
    private String nickName;
    @TableField
    @Column(type = MySqlTypeConstant.VARCHAR, length = 50, comment = "真实姓名")
    private String realName;
    @TableField
    @Column(type = MySqlTypeConstant.VARCHAR, length = 20, comment = "身份证号")
    private String idCard;
    @TableField
    @Column(type = MySqlTypeConstant.VARCHAR, length = 100, comment = "邮箱")
    private String email;
}
