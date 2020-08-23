package com.vinnikov.clever.operator.api.request;

import com.vinnikov.clever.operator.api.MessageType;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class TicketSearchRequest {
    private String authorizationKey;
    private MessageType messageType;
    private Long serviceId;
    private String ticketCode;
    private String customerName;
    private String customerPhone;


}
