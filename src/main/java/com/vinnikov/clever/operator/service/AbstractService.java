package com.vinnikov.clever.operator.service;

import com.vinnikov.clever.operator.db.entity.AuthorizationHistoryEntity;
import com.vinnikov.clever.operator.db.entity.TicketEntity;
import com.vinnikov.clever.operator.db.repository.AuthorizationHistoryRepository;
import lombok.extern.slf4j.Slf4j;

import javax.annotation.Resource;
import java.util.Collection;

@Slf4j
public abstract class AbstractService {

    @Resource
    protected AuthorizationHistoryRepository authorizationHistoryRepository;

    protected void isValidAuthorization(String key) throws RuntimeException {
        Collection<AuthorizationHistoryEntity> authorizations =
                authorizationHistoryRepository.findByAuthorizationKey(key);
        AuthorizationHistoryEntity auth = null;
        if (authorizations != null && authorizations.size() > 0) {
            auth = authorizations.stream()
                    .findAny()
                    .get();
        }
        if (auth == null) {
            log.warn("Auth is not correct. Authorization key from request: {}", key);
            throw new RuntimeException("Не корректные данные для авторизации.");
        }
        if (!isValidTime(auth)) {
            log.warn("Auth is time out. Authorization key from request: {}", key);
            throw new RuntimeException("Время сессии истекло.");
        }
    }

    private boolean isValidTime(AuthorizationHistoryEntity auth) {
        Long now = System.currentTimeMillis();
        if (auth.getStartAuthorization().getTime() > now || auth.getEndAuthorization().getTime() < now) {
            throw new RuntimeException("Вышло время авторизации.");
        }
        return true;
    }

    protected void isValidTicket(Long serviceId, TicketEntity ticket) throws RuntimeException {
        if (ticket == null) {
            throw new RuntimeException("Билет не найден");
        }
        if (ticket.getIdService() != serviceId) {
            throw new RuntimeException("Вы не авторизованы для гашения этого билета");
        }
        if (ticket.getService() != null) {
            throw new RuntimeException("По данному билету услуга уже оказана");
        }
        if (ticket.getIdRefund() != null) {
            throw new RuntimeException("По даному билеты был осуществелн возврат");
        }
    }
}
