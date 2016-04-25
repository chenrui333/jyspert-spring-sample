package org.hbsp.cl.report.controller;

/**
 * Created by rui.chen on 4/5/16.
 */

import com.zaxxer.hikari.HikariConfig;
import org.jasypt.encryption.pbe.config.SimplePBEConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class ReportController {

    @Autowired
    private HikariConfig config;

    @RequestMapping(value = "/ping", method = RequestMethod.GET)
    @ResponseBody
    public String showConfig() {
        return "pong";
    }

    @RequestMapping(value = "/ds", method = RequestMethod.GET)
    @ResponseBody
    public String showHikariConfig() {
        return "password: " + config.getPassword() + " \n";
    }
}
