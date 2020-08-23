package com.vinnikov.clever.operator.api.response;

import com.vinnikov.clever.operator.api.response.util.SearchTicket;
import lombok.*;

import java.util.Collection;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class TicketSearchResponse {
    private Boolean fail;
    private String failDescription;
    private Integer accessRights;
    private Boolean authorizationSuccess;
    private Collection<SearchTicket> searchTickets;
}
