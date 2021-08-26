package com.bohrer.budgetapi.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/auth/vuln")
public class AuthVulnController {
    /**
     * This controllers will serve as endpoints to test for no auth
     * or for poorly implemented authentication in an application
     * For now will only contain two endpoints noAuth and expired
     * which are implemented as their endpoints described.
     * Hopefully this will get filled with some real world examples
     * at a later date.
     */

    @GetMapping("/noAuth")
    public String getNoAuthCheck(@RequestParam String param) {
        return "You are authenticated!";
    }

    @GetMapping("/expired")
    public String getExpiredNotChecked(@RequestParam String param) {
        return "Your token has not expired!";
    }
    
    
    
}
