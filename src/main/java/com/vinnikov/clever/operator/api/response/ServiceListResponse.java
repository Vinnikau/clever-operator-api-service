package com.vinnikov.clever.operator.api.response;

import com.vinnikov.clever.operator.api.response.util.AvailableService;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class ServiceListResponse {

    private Set<AvailableService> availableServices;
    private Boolean fail;
    private String failDescription;
    private Integer accessRights;
    private Boolean authorizationSuccess;
}
