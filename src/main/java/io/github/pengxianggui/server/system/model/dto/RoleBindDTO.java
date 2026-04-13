package io.github.pengxianggui.server.system.model.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.Set;

/**
 * @author pengxg
 * @date 2026/4/13 18:24
 */
@Data
public class RoleBindDTO {
    @NotNull
    private Set<Long> roleIds;
}
