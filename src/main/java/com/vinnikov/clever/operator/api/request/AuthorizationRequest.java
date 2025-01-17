package com.vinnikov.clever.operator.api.request;

import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AuthorizationRequest extends AbstractRequest {
    private String userName;
    private String userPassword;
}
