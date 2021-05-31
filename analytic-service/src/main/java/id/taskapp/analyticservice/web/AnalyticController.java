package id.taskapp.analyticservice.web;

import id.taskapp.analyticservice.dto.AnalyticReportDto;
import id.taskapp.analyticservice.dto.CountReportDto;
import id.taskapp.analyticservice.dto.TaskDto;
import id.taskapp.analyticservice.model.Task;
import id.taskapp.analyticservice.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Project Name     : workspace-services
 * Date Time        : 9/8/2020
 *
 * @author Teten Nugraha
 */

@RestController
@RequestMapping("/analytic")
public class AnalyticController {

    @Autowired
    private TaskService taskService;

    @GetMapping("/getList")
    public ResponseEntity<List<AnalyticReportDto>> getList() {
        try {
            List<AnalyticReportDto> taskList = taskService.findAll();
            return new ResponseEntity<>(taskList, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }
    }

    @PostMapping("/createFinishedTask")
    public ResponseEntity<String> createTutorial(@RequestBody TaskDto taskDto) {
        try {
            Task _task = taskService.createFinishedTask(taskDto);
            return new ResponseEntity<>("Created", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping("/countReport")
    public ResponseEntity<CountReportDto> getCountReport() {
        final CountReportDto dto = taskService.getReport();
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }
}
