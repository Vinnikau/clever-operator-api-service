package com.vinnikov.clever.operator.api.request;

import com.vinnikov.clever.operator.api.MessageType;
import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public abstract class AbstractRequest {
    protected MessageType messageType;
}
