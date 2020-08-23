package com.vinnikov.clever.operator.service;

import com.vinnikov.clever.operator.api.response.ServiceListResponse;
import com.vinnikov.clever.operator.db.entity.AuthorizationHistoryEntity;
import com.vinnikov.clever.operator.db.repository.AuthorizationHistoryRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.Date;

@Slf4j
public abstract class AbstractService {

    @Resource
    protected AuthorizationHistoryRepository authorizationHistoryRepository;

    protected boolean isValidAuthorization(String key) {

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
        return true;
    }

    private boolean isValidTime(AuthorizationHistoryEntity auth) {
        Long now = System.currentTimeMillis();
        if (auth.getStartAuthorization().getTime() > now || auth.getEndAuthorization().getTime() < now) {
            throw new RuntimeException("Вышло время авторизации.");
        }
        return true;
    }
}
