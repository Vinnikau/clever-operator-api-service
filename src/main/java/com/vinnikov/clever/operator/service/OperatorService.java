package com.vinnikov.clever.operator.service;

import com.vinnikov.clever.operator.api.request.AuthorizationRequest;
import com.vinnikov.clever.operator.api.request.ServiceListRequest;
import com.vinnikov.clever.operator.api.request.TicketUseProofRequest;
import com.vinnikov.clever.operator.api.request.TicketUseRequest;
import com.vinnikov.clever.operator.api.response.AuthorizationResponse;
import com.vinnikov.clever.operator.api.response.ServiceListResponse;
import com.vinnikov.clever.operator.api.response.TicketUseProofResponse;
import com.vinnikov.clever.operator.api.response.TicketUseResponse;
import org.springframework.http.ResponseEntity;

public interface OperatorService {
    ResponseEntity<AuthorizationResponse> authorization(AuthorizationRequest request);
    ResponseEntity<ServiceListResponse> getServiceList(ServiceListRequest request);
    ResponseEntity<TicketUseResponse> canApproveTicketUseQr(TicketUseRequest request);
    ResponseEntity<TicketUseProofResponse> proofTicketUseQr(TicketUseProofRequest request);
}
