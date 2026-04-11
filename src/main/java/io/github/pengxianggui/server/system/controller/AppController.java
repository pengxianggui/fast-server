package io.github.pengxianggui.server.system.controller;

import io.github.pengxianggui.server.auth.Authorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Locale;

/**
 * @author pengxg
 * @date 2026/4/11 10:06
 */
@RestController
@RequestMapping("pub/app")
public class AppController {

    @GetMapping("/lang")
    public String test(Locale locale) {
        return locale.toLanguageTag();
    }
}
