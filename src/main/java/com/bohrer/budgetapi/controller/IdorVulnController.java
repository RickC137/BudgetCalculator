package com.bohrer.budgetapi.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/idor")
public class IdorVulnController {
    /**
     * This controller will serve as a testing ground
     * for idor related api vulnerabilities. For now it
     * will contain some basic level idor bugs to test on,
     * but will contain at a later date some real world examples
     */

     /**
      * This endpoint will return by id without validating user roles
      * or permissions to view said object
      */
     @GetMapping("/getItemById")
     public String getItem(@RequestParam String id) {
         return "This is the id: " + id; 
     }
    
}
