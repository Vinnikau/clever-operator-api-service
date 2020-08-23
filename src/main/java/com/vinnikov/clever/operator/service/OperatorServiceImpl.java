package com.vinnikov.clever.operator.service;

import com.vinnikov.clever.operator.api.request.AuthorizationRequest;
import com.vinnikov.clever.operator.api.request.ServiceListRequest;
import com.vinnikov.clever.operator.api.request.TicketUseProofRequest;
import com.vinnikov.clever.operator.api.request.TicketUseRequest;
import com.vinnikov.clever.operator.api.response.AuthorizationResponse;
import com.vinnikov.clever.operator.api.response.ServiceListResponse;
import com.vinnikov.clever.operator.api.response.TicketUseProofResponse;
import com.vinnikov.clever.operator.api.response.TicketUseResponse;
import com.vinnikov.clever.operator.api.response.util.AvailableService;
import com.vinnikov.clever.operator.db.entity.*;
import com.vinnikov.clever.operator.db.repository.CustomerRepository;
import com.vinnikov.clever.operator.db.repository.EmployeeRepository;
import com.vinnikov.clever.operator.db.repository.ServiceRepository;
import com.vinnikov.clever.operator.db.repository.TicketRepository;
import com.vinnikov.clever.operator.util.KeyGenerator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.Date;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Service
public class OperatorServiceImpl extends AbstractService implements OperatorService {

    @Resource
    private EmployeeRepository employeeRepository;

    @Resource
    private ServiceRepository serviceRepository;

    @Resource
    private TicketRepository ticketRepository;

    @Resource
    private CustomerRepository customerRepository;

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
        if (!employeeEntity.getPasswordEmployee().equals(request.getUserPassword())) {
            response.setFail(true);
            response.setFailDescription("Имя пользователя или пароль не верные.");
            response.setAccessRights(0);
            response.setAuthorizationSuccess(false);
            log.info("Password is not correct. {}. Authorization false: {}", request.getUserName(), response.toString());
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).contentType(MediaType.APPLICATION_JSON).body(response);
        }

        if(!employeeEntity.getActingEmployee()) {
            response.setFail(true);
            response.setFailDescription("Отказано в доступе. Работник не работает в данный момент.");
            response.setAccessRights(0);
            response.setAuthorizationSuccess(false);
            log.info("Password is not correct. {}. Authorization false: {}", request.getUserName(), response.toString());
            return ResponseEntity.status(HttpStatus.FORBIDDEN).contentType(MediaType.APPLICATION_JSON).body(response);
        }
        String ak = getKey(request, employeeEntity.getIdEmployee(), "remote");

        response.setFail(Boolean.FALSE);
        response.setFailDescription("");
        response.setAccessRights(employeeEntity.getAccessRights());
        response.setAuthorizationSuccess(true);
        response.setAuthorizationKey(ak);
        log.info("Authorization: {}", response.toString());
        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(response);
    }

    @Override
    public ResponseEntity<ServiceListResponse> getServiceList(ServiceListRequest request) {
        ServiceListResponse response;
        HttpStatus status;
        Collection<AuthorizationHistoryEntity> authorizations =
                authorizationHistoryRepository.findByAuthorizationKey(request.getAuthorizationKey());
        AuthorizationHistoryEntity auth = null;
        if (authorizations != null && authorizations.size() > 0) {
            auth = authorizations.stream()
                    .findAny()
                    .get();
        }
        try {
            isValidAuthorization(request.getAuthorizationKey());
        } catch (Exception e) {
            response = ServiceListResponse.builder()
                    .accessRights(0)
                    .authorizationSuccess(false)
                    .fail(true)
                    .failDescription(e.getMessage())
                    .availableServices(null)
                    .build();
            log.warn("Auth is time out. Authorization response: {}", response.toString());
            status = HttpStatus.UNAUTHORIZED;
        }
        log.debug("Auth is ok!");
        int access = 0;
        if (auth != null) {
            access = employeeRepository.findById(auth.getIdEmployee()).stream().findAny().get().getAccessRights();
        }
        Set<AvailableService> availableServices = serviceRepository.findAllWorkingServices().stream()
                .map(a -> AvailableService.builder().serviceId(a.getIdService()).serviceName(a.getName()).build())
                .collect(Collectors.toSet());
        response = ServiceListResponse.builder()
                .accessRights(access)
                .authorizationSuccess(true)
                .fail(false)
                .failDescription("")
                .availableServices(availableServices)
                .build();
        status = HttpStatus.OK;
        return ResponseEntity.status(status).contentType(MediaType.APPLICATION_JSON).body(response);
    }

    @Override
    public ResponseEntity<TicketUseResponse> canApproveTicketUseQr(TicketUseRequest request) {
        TicketUseResponse response;
        HttpStatus status;
        Collection<AuthorizationHistoryEntity> authorizations =
                authorizationHistoryRepository.findByAuthorizationKey(request.getAuthorizationKey());
        AuthorizationHistoryEntity auth = null;
        if (authorizations != null && authorizations.size() > 0) {
            auth = authorizations.stream()
                    .findAny()
                    .get();
        }
        try {
            isValidAuthorization(request.getAuthorizationKey());
        } catch (Exception e) {
            response = TicketUseResponse.builder()
                    .accessRights(0)
                    .authorizationSuccess(false)
                    .fail(true)
                    .failDescription(e.getMessage())
                    .customerName("")
                    .customerPhone("")
                    .serviceId(0L)
                    .serviceName("")
                    .ticketCode(request.getTicketCode())
                    .serviceProvideSuccess(false)
                    .build();
            log.warn("Auth is time out. Authorization response: {}", response.toString());
            status = HttpStatus.UNAUTHORIZED;
        }
        log.debug("Auth is ok!");
        int access = 0;
        if (auth != null) {
            access = employeeRepository.findById(auth.getIdEmployee()).stream().findAny().get().getAccessRights();
        }
        try {
            TicketEntity ticket = ticketRepository.findByTicketNumber(request.getTicketCode());
            isValidTicket(request.getServiceId(), ticket);
            ServiceEntity service;
            CustomerEntity customer;
            try {
                service = serviceRepository.findById(request.getServiceId()).get();
                customer = customerRepository.findById(ticket.getIdCustomer()).get();
            }  catch (Exception e) {
                throw new RuntimeException("Некорректные данные");
            }

            response = TicketUseResponse.builder()
                    .accessRights(access)
                    .authorizationSuccess(true)
                    .fail(false)
                    .failDescription("")
                    .customerName(customer.getNameCustomer() + " " + customer.getPatronymicCustomer()
                            + " " + customer.getSurnameCustomer())
                    .customerPhone(customer.getPhoneCustomer())
                    .serviceId(ticket.getIdService())
                    .serviceName(service.getName())
                    .ticketCode(request.getTicketCode())
                    .serviceProvideSuccess(true)
                    .build();
            status = HttpStatus.OK;
        } catch (Exception e) {
            response = TicketUseResponse.builder()
                    .accessRights(access)
                    .authorizationSuccess(true)
                    .fail(true)
                    .failDescription(e.getMessage())
                    .customerName("")
                    .customerPhone("")
                    .serviceId(request.getServiceId())
                    .serviceName("")
                    .ticketCode(request.getTicketCode())
                    .serviceProvideSuccess(false)
                    .build();
            log.warn("Auth is time out. Authorization response: {}", response.toString());
            status = HttpStatus.BAD_REQUEST;
        }
        return ResponseEntity.status(status).contentType(MediaType.APPLICATION_JSON).body(response);
    }

    @Override
    public ResponseEntity<TicketUseProofResponse> proofTicketUseQr(TicketUseProofRequest request) {
        return null;
    }


    private String getKey(AuthorizationRequest request, Long idUser, String remoteAdress) {
        Long now = System.currentTimeMillis();
        Date startTime = new Date(now);
        Date endTime = new Date(now + 28800000L);

        String ak = KeyGenerator.generateKey(request.getUserName(), startTime);
        AuthorizationHistoryEntity history = new AuthorizationHistoryEntity();
        history.setAuthorizationKey(ak);
        history.setStartAuthorization(startTime);
        history.setEndAuthorization(endTime);
        history.setRemoteAddress(remoteAdress);
        history.setIdEmployee(idUser);

        AuthorizationHistoryEntity his = authorizationHistoryRepository.save(history);
        log.debug(his.toString());
        return ak;
    }

}
