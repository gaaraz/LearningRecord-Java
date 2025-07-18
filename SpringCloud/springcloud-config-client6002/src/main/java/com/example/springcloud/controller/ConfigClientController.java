package com.example.springcloud.controller;

import com.example.springcloud.entities.CommonResult;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RefreshScope
public class ConfigClientController {
    @Value("${config.version}")
    private String version;

    @GetMapping("/version")
    public CommonResult getVersion(){
        return CommonResult.success(version);
    }
}
