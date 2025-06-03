package com.example.memory;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.SQSEvent;
import com.example.memory.client.FakerApiClient;
import com.example.memory.dto.AddressResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

@ApplicationScoped
@Named("lambdaMemory")
public class LambdaMemory implements RequestHandler<Map<String, String>, Void> {

    private static final Logger LOGGER = LoggerFactory.getLogger(LambdaMemory.class);
    private final ObjectMapper objectMapper;

    @Inject
    @RestClient
    FakerApiClient fakerApiClient;

    public LambdaMemory(final ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public Void handleRequest(final Map<String, String> event, final Context context) {
        LOGGER.info("Eventos recebidos: {}", event);
        String delay = System.getenv().getOrDefault("MOCKY_DELAY", "60s");

        try {
            AddressResponse addressResponse = fakerApiClient.getAddresses(delay);
            LOGGER.info("Resposta do FakerAPI: {}",
                    addressResponse.getData() != null && !addressResponse.getData().isEmpty() ?
                            objectMapper.writeValueAsString(addressResponse.getData().get(0)):
                            "Sem dados");
        } catch (Exception e) {
            LOGGER.error("Erro ao chamar o FakerAPI", e);
        }
        return null;
    }
}