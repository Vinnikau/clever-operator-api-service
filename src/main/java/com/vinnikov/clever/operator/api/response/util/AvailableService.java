package com.vinnikov.clever.operator.api.response.util;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class AvailableService {
    private Long serviceId;
    private String serviceName;
}
