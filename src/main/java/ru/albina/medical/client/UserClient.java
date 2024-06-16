package ru.albina.medical.client;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;
import ru.albina.backlib.configuration.WebConstants;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Component
public class UserClient {

    private final WebClient webClient;

    public UserClient(WebClient.Builder libWebClientBuilder) {
        final int size = 30 * 1024 * 1024;
        final ExchangeStrategies strategies = ExchangeStrategies.builder()
                .codecs(codecs -> codecs.defaultCodecs().maxInMemorySize(size))
                .build();

        this.webClient = libWebClientBuilder
                .exchangeStrategies(strategies)
                .baseUrl(Optional.ofNullable(System.getenv("USER_SERVICE_HOST")).orElse("http://localhost:8080"))
                .build();
    }


    public List<UUID> findByFullName(String fullName) {
        return this.webClient.post()
                .uri(WebConstants.FULL_PRIVATE + "/users/find-ids")
                .bodyValue(Map.of(
                        "fullName", fullName
                ))
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<List<UUID>>() {
                })
                .block();
    }
}
