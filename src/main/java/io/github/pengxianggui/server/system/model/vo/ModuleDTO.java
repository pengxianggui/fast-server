package io.github.pengxianggui.server.system.model.vo;

import cn.hutool.core.lang.tree.TreeNode;
import io.github.pengxianggui.server.system.model.entity.Module;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 模块节点DTO
 *
 * @author pengxg
 * @date 2026/2/26 11:18
 */
@NoArgsConstructor
@Data
public class ModuleDTO extends TreeNode<Long> {
    private static final long serialVersionUID = 1L;
    private String description;

    public ModuleDTO(Module module) {
        this.setId(module.getId());
        this.setName(module.getName());
        this.setParentId(module.getParentId());
        this.setDescription(module.getDescription());
    }
}
