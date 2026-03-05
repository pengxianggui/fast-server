package io.github.pengxianggui.server.system.model.dto;

import cn.hutool.core.lang.tree.TreeNode;
import io.github.pengxianggui.server.system.model.entity.Auth;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 权限树节点DTO
 *
 * @author pengxg
 * @date 2026/3/4 20:12
 */
@NoArgsConstructor
@Data
public class AuthTreeNodeDTO extends TreeNode<Long> {
    private String description;

    public AuthTreeNodeDTO(Auth auth) {
        this.setId(-auth.getId());
        this.setName(auth.getName());
        this.setParentId(auth.getModuleId());
        this.setDescription(auth.getDescription());
    }
}
