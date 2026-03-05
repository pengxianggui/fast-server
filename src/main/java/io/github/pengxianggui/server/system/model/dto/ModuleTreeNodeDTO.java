package io.github.pengxianggui.server.system.model.dto;

import cn.hutool.core.lang.tree.TreeNode;
import io.github.pengxianggui.server.system.model.entity.Module;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 模块树节点DTO
 *
 * @author pengxg
 * @date 2026/2/26 11:18
 */
@NoArgsConstructor
@Data
public class ModuleTreeNodeDTO extends TreeNode<Long> {
    private static final long serialVersionUID = 1L;
    private String description;

    public ModuleTreeNodeDTO(Module module) {
        this.setId(module.getId());
        this.setName(module.getName());
        this.setParentId(module.getParentId());
        this.setDescription(module.getDescription());
    }
}
