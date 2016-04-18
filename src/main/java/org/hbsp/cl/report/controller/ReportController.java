package org.hbsp.cl.report.controller;

/**
 * Created by rui.chen on 4/5/16.
 */

import com.zaxxer.hikari.HikariConfig;
import org.jasypt.encryption.pbe.config.SimplePBEConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class ReportController {

    @Autowired
    private HikariConfig config;

    @RequestMapping("/config")
    public String showConfig() {
        String good = "good";
        return good;
    }

    @RequestMapping("/ds")
    public String showHikariConfig() {
        String ds = "password: " + config.getPassword() + " \n";

        return ds;
    }
}
