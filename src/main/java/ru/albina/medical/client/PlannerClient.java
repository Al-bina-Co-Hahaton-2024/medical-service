package ru.albina.medical.client;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;
import ru.albina.backlib.configuration.WebConstants;
import ru.albina.medical.dto.planner.PlannerOutDateDaysNotification;

import java.util.Optional;

@Component
public class PlannerClient {

    private final WebClient webClient;

    public PlannerClient(WebClient.Builder libWebClientBuilder) {
        final int size = 30 * 1024 * 1024;
        final ExchangeStrategies strategies = ExchangeStrategies.builder()
                .codecs(codecs -> codecs.defaultCodecs().maxInMemorySize(size))
                .build();

        this.webClient = libWebClientBuilder
                .exchangeStrategies(strategies)
                .baseUrl(Optional.ofNullable(System.getenv("PLANNER_SERVICE_HOST")).orElse("http://localhost:8084"))
                .build();
    }


    public void getDoctors(PlannerOutDateDaysNotification planner) {
        this.webClient.post()
                .uri(WebConstants.FULL_PRIVATE + "/notifications/out-dates")
                .bodyValue(planner)
                .retrieve()
                .toBodilessEntity()
                .block();
    }
}
