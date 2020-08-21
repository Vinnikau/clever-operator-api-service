package com.vinnikov.clever.operator.service;

import com.vinnikov.clever.operator.api.request.AuthorizationRequest;
import com.vinnikov.clever.operator.api.response.AuthorizationResponse;
import com.vinnikov.clever.operator.db.entity.EmployeeEntity;
import com.vinnikov.clever.operator.db.repository.EmployeeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collection;

@Slf4j
@Service
public class OperatorServiceImpl implements OperatorService {

    @Resource
    private EmployeeRepository employeeRepository;

    @Override
    public ResponseEntity<AuthorizationResponse> authorization(AuthorizationRequest request) {
        AuthorizationResponse response = new AuthorizationResponse();
        Collection<EmployeeEntity> employee = employeeRepository.findByLogin(request.getUserName());

        if (employee == null || employee.size() == 0) {
            response.setFail(true);
            response.setFailDescription("Пользователь не найден.");
            response.setAccessRights(0);
            response.setAuthorizationSuccess(false);
            log.info("Authorization false: {}", response.toString());
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).contentType(MediaType.APPLICATION_JSON).body(response);
        }

        log.info("Authorization: {}", response.toString());
        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(response);
    }
}
