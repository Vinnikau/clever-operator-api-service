package com.vinnikov.clever.operator.controller;

import com.vinnikov.clever.operator.api.request.*;
import com.vinnikov.clever.operator.api.response.*;
import com.vinnikov.clever.operator.service.OperatorService;
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

    @Resource
    private OperatorService operatorService;

    @PostMapping("/auth")
    public ResponseEntity<AuthorizationResponse> authorization(@RequestBody AuthorizationRequest request) {
        log.info("Was received auth-request by user: {}", request.getUserName());
        return operatorService.authorization(request);
    }

    @PostMapping("/service/list")
    public ResponseEntity<ServiceListResponse> serviceList(@RequestBody ServiceListRequest request) {
        log.info("Was received service-list-request: {}", request.getAuthorizationKey());
        return operatorService.getServiceList(request);
    }

    @PostMapping("/ticket/can/approve")
    public ResponseEntity<TicketUseResponse> canApproveTicket(@RequestBody TicketUseRequest request) {
        log.info("Was received approve-request: {}", request.getAuthorizationKey());
        return operatorService.canApproveTicketUseQr(request);
    }

    @PostMapping("/ticket/proof")
    public ResponseEntity<TicketUseProofResponse> proofTicket(@RequestBody TicketUseProofRequest request) {
        log.info("Was received proof-request: {}", request.getAuthorizationKey());
        return operatorService.proofTicketUseQr(request);
    }

    @PostMapping("/ticket/search")
    public ResponseEntity<TicketSearchResponse> searchTicket(@RequestBody TicketSearchRequest request) {
        log.info("Was received search-request: {}", request.toString());
        return operatorService.searchTicket(request);
    }
}
