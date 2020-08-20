package com.vinnikov.clever.operator.controller;

import com.vinnikov.clever.operator.api.request.AuthorizationRequest;
import com.vinnikov.clever.operator.api.response.AuthorizationResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@Slf4j
@RestController
@RequestMapping("/api/v1/clever/operator")
public class OperatorController {
/*
    @Resource
    private OpsService opsService;


 */
    @PostMapping("/auth")
    public ResponseEntity<AuthorizationResponse> authorization(@RequestBody AuthorizationRequest request) {
        return null;
    }
}
