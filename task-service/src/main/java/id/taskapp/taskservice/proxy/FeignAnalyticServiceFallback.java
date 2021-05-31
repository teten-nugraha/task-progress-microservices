package id.taskapp.taskservice.proxy;

import id.taskapp.taskservice.dto.TaskDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class FeignAnalyticServiceFallback implements FeignAnalyticService{

    public static final Logger LOGGER = LoggerFactory.getLogger(FeignAnalyticServiceFallback.class);

    private void logMessage(final String message) {
        LOGGER.debug("Circuit log-service closed (down) {}", message);
    }

    @Override
    public String createFinishedTask(TaskDto dto) {
        logMessage("fallback set value for post createFinishedTask to null");
        return null;
    }
}
