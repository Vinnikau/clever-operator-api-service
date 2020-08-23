package com.vinnikov.clever.operator.api.response;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class TicketUseProofResponse {
    private Boolean fail;
    private String failDescription;
    private Integer accessRights;
    private Boolean authorizationSuccess;
    private Boolean serviceProofSuccess;
    private Long serviceId;
    private String serviceName;
    private String ticketCode;
    private String errorDescription;
    private Date dateTimeProof;

}
