package com.vinnikov.clever.operator.service;

import com.vinnikov.clever.operator.api.request.AuthorizationRequest;
import com.vinnikov.clever.operator.api.response.AuthorizationResponse;
import com.vinnikov.clever.operator.db.entity.AuthorizationHistoryEntity;
import com.vinnikov.clever.operator.db.entity.EmployeeEntity;
import com.vinnikov.clever.operator.db.repository.AuthorizationHistoryRepository;
import com.vinnikov.clever.operator.db.repository.EmployeeRepository;
import com.vinnikov.clever.operator.util.KeyGenerator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.Date;

@Slf4j
@Service
public class OperatorServiceImpl implements OperatorService {

    @Resource
    private EmployeeRepository employeeRepository;

    @Resource
    private AuthorizationHistoryRepository authorizationHistoryRepository;

    @Override
    public ResponseEntity<AuthorizationResponse> authorization(AuthorizationRequest request) {
        AuthorizationResponse response = new AuthorizationResponse();
        Collection<EmployeeEntity> employee = employeeRepository.findByLogin(request.getUserName());

        if (employee == null || employee.size() == 0) {
            response.setFail(true);
            response.setFailDescription("Имя пользователя или пароль не верные.");
            response.setAccessRights(0);
            response.setAuthorizationSuccess(false);
            log.info("UserName is not found. {}. Authorization false: {}", request.getUserName(), response.toString());
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).contentType(MediaType.APPLICATION_JSON).body(response);
        }
        EmployeeEntity employeeEntity = employee.stream().findFirst().get();
        if (employeeEntity.getPasswordEmployee() != request.getUserPassword()) {
            response.setFail(true);
            response.setFailDescription("Имя пользователя или пароль не верные.");
            response.setAccessRights(0);
            response.setAuthorizationSuccess(false);
            log.info("Password is not correct. {}. Authorization false: {}", request.getUserName(), response.toString());
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).contentType(MediaType.APPLICATION_JSON).body(response);
        }

        Date authTime = new Date(System.currentTimeMillis());
        String ak = KeyGenerator.generateKey(request.getUserName(), authTime);


        response.setFail(Boolean.FALSE);
        response.setFailDescription("");
        response.setAccessRights(employeeEntity.getAccessRights());
        response.setAuthorizationSuccess(true);
        response.setAuthorizationKey(ak);
        log.info("Authorization: {}", response.toString());
        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(response);
    }

    private String getKey(String key, AuthorizationRequest request, Long idUser, String remoteAdress) {
        Long now = System.currentTimeMillis();
        Date startTime = new Date(now);
        Date endTime = new Date(now + 28800000L);

        String ak = KeyGenerator.generateKey(request.getUserName(), startTime);
        AuthorizationHistoryEntity history = new AuthorizationHistoryEntity();
        history.authorizationKey(ak);
        history.setStartAuthorization(startTime);
        history.setEndAuthorization(endTime);
        history.setRemoteAddress(remoteAdress);
        history.setIdEmployee(idUser);

        authorizationHistoryRepository.save(history);

        return ak;
    }

}
