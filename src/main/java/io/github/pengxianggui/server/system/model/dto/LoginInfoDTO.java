package io.github.pengxianggui.server.system.model.dto;

import lombok.Builder;
import lombok.Data;

import java.util.Set;

/**
 * @author pengxg
 * @date 2026/2/23 14:32
 */
@Data
@Builder
public class LoginInfoDTO {
    private Long id;
    private String username;
    private Set<String> roles;
    private Set<String> auths;
}
