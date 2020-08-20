package com.vinnikov.clever.operator.api.response;

import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public abstract class AbstractResponse {
    protected String authorizationKey;
    protected Boolean fail;
    protected String failDescription;
    protected Integer accessRights;
    protected Boolean authorizationSuccess;
}
