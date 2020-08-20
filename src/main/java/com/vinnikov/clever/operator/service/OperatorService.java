package com.vinnikov.clever.operator.service;

import com.vinnikov.clever.operator.api.request.AuthorizationRequest;
import com.vinnikov.clever.operator.api.response.AuthorizationResponse;
import org.springframework.http.ResponseEntity;

public interface OperatorService {
    ResponseEntity<AuthorizationResponse> authorization(AuthorizationRequest request);
}
