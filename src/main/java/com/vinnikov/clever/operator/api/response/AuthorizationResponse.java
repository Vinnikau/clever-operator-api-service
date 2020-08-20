package com.vinnikov.clever.operator.api.response;

import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AuthorizationResponse {
    private String authorizationKey;
}
