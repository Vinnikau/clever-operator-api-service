package com.vinnikov.clever.operator.api.request;

import com.vinnikov.clever.operator.api.MessageType;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class ServiceListRequest {
    private String authorizationKey;
    private MessageType messageType;
}
