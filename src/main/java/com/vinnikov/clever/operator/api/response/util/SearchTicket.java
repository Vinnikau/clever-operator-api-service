package com.vinnikov.clever.operator.api.response.util;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class SearchTicket {
    private Long serviceId;
    private String serviceName;
    private String customerName;
    private String customerPhone;
    private String ticketCode;
}
