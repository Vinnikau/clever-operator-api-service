package com.vinnikov.clever.operator.api.response;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class TicketUseResponse {

    private Boolean fail;
    private String failDescription;
    private Integer accessRights;
    private Boolean authorizationSuccess;
    private Boolean serviceProvideSuccess;
    private Long serviceId;
    private String serviceName;
    private String customerName;
    private String customerPhone;
    private String ticketCode;
}
