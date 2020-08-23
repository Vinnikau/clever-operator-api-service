package com.vinnikov.clever.operator.api.response;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
@ToString
public class AuthorizationResponse {

    private String authorizationKey;
    private Boolean fail;
    private String failDescription;
    private Integer accessRights;
    private Boolean authorizationSuccess;


}
