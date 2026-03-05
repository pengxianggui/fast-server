package io.github.pengxianggui.server.common.log.opr_log;

import cn.hutool.core.util.StrUtil;
import cn.hutool.extra.servlet.ServletUtil;
import cn.hutool.http.useragent.Platform;
import cn.hutool.http.useragent.UserAgent;
import cn.hutool.http.useragent.UserAgentUtil;
import io.github.pengxianggui.server.auth.LoginUser;
import io.github.pengxianggui.server.auth.SecurityUtil;
import io.github.pengxianggui.server.common.i18n.I18nUtil;
import io.github.pengxianggui.server.system.model.entity.OprLog;
import io.github.pengxianggui.server.system.service.OprLogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.Optional;

/**
 * @author pengxg
 * @date 2025/11/11 15:04
 */
@Slf4j
@Component
public class OprLogUtil {
    @Autowired
    private OprLogService oprLogService;

    /**
     * 记录操作日志
     *
     * @param code  编码
     * @param desc  操作描述
     * @param param 操作入参
     */
    public void recordBizLog(String code, String desc, String param) {
        if (StrUtil.isBlank(code) || StrUtil.isNullOrUndefined(code)) {
            throw new IllegalArgumentException(I18nUtil.get("opr_log.biz_code_required", code));
        }
        OprLog bizLog = new OprLog();
        bizLog.setCode(code);
        bizLog.setOperateDesc(desc);
        bizLog.setParam(param);
        bizLog.setOperateTime(new Date());
        LoginUser loginUser = SecurityUtil.getLoginUser();
        if (loginUser != null) {
            bizLog.setCreateBy(loginUser.getId());
            bizLog.setCreateName(loginUser.getRealName());
        }
        RequestAttributes attrs = RequestContextHolder.getRequestAttributes();
        if (attrs instanceof ServletRequestAttributes) {
            ServletRequestAttributes sra = (ServletRequestAttributes) attrs;
            HttpServletRequest request = sra.getRequest();
            bizLog.setIp(ServletUtil.getClientIP(request));
            UserAgent userAgent = UserAgentUtil.parse(request.getHeader("User-Agent"));
            if (userAgent != null) {
                bizLog.setTerminalType(
                        Optional.ofNullable(userAgent.getPlatform())
                                .map(Platform::getName)
                                .orElse(Platform.Unknown.getName())
                );
            }
        }

        oprLogService.save(bizLog);
    }
}
