package com.vinnikov.clever.operator.api.request;

import com.vinnikov.clever.operator.api.MessageType;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AuthorizationRequest {
    private String userName;
    private String userPassword;
    protected MessageType messageType;
}
