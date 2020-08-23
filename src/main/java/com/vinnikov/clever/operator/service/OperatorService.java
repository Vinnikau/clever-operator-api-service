package com.vinnikov.clever.operator.service;

import com.vinnikov.clever.operator.api.request.*;
import com.vinnikov.clever.operator.api.response.*;
import org.springframework.http.ResponseEntity;

public interface OperatorService {
    ResponseEntity<AuthorizationResponse> authorization(AuthorizationRequest request);
    ResponseEntity<ServiceListResponse> getServiceList(ServiceListRequest request);
    ResponseEntity<TicketUseResponse> canApproveTicketUseQr(TicketUseRequest request);
    ResponseEntity<TicketUseProofResponse> proofTicketUseQr(TicketUseProofRequest request);
    ResponseEntity<TicketSearchResponse> searchTicket(TicketSearchRequest request);
}
