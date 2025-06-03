package com.example.memory.client;

import com.example.memory.dto.AddressResponse;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.QueryParam;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@Path("/v3/6e8d6e99-715f-41dc-96a7-c8e11b4cdd30")
@RegisterRestClient(configKey = "faker-api")
public interface FakerApiClient {

    @GET
    AddressResponse getAddresses(@QueryParam("mocky-delay") String delay);
}