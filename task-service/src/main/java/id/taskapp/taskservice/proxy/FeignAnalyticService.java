package id.taskapp.taskservice.proxy;

import id.taskapp.taskservice.dto.TaskDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(
        value = "analytic-service",
        fallback = FeignAnalyticServiceFallback.class,
        configuration = { DefaultFeignConfiguration.class}
)
public interface FeignAnalyticService {

    @PostMapping("/analytic/createFinishedTask")
    String createFinishedTask(@RequestBody final TaskDto dto);

}
