package ru.albina.medical.service.planner;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import ru.albina.medical.client.PlannerClient;
import ru.albina.medical.dto.planner.PlannerOutDateDaysNotification;

@Service
@RequiredArgsConstructor
public class PlannerNotificationService {

    private final PlannerClient plannerClient;

    @Async
    public void notifyAboutOutdatedDays(PlannerOutDateDaysNotification outdatedDays) {
        plannerClient.getDoctors(outdatedDays);
    }
}
