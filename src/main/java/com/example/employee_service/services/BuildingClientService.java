package com.example.employee_service.services;

import com.example.employee_service.exceptions.CustomServiceException;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class BuildingClientService {

    private final WebClient webClient = WebClient.builder().baseUrl("http://localhost:8081/api/buildings").build();

    public Mono<Void> requestServiceEmployeeToBuilding(String buildingName, String endpoint) {
        return webClient.patch()
                .uri("/"+buildingName+"/employee/"+endpoint)
                .retrieve()
                .onStatus(HttpStatusCode::isError, this::clientResponseError)
                .toBodilessEntity()
                .flatMap(response ->
                        response.getStatusCode()
                                .equals(HttpStatus.OK)
                                        ? Mono.empty()
                                        : Mono.error(new CustomServiceException("Unexpected status: " + response.getStatusCode())));
    }

    private Mono<CustomServiceException> clientResponseError(ClientResponse clientResponse) {
        return clientResponse
                .bodyToMono(String.class)
                .flatMap(errorBody -> Mono.error(new CustomServiceException(errorBody)));
    }

}
