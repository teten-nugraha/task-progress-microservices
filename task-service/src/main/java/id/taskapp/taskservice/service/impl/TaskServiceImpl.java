package id.taskapp.taskservice.service.impl;

import id.taskapp.taskservice.domain.Task;
import id.taskapp.taskservice.dto.TaskDto;
import id.taskapp.taskservice.proxy.FeignAnalyticService;
import id.taskapp.taskservice.repo.TaskRepository;
import id.taskapp.taskservice.service.TaskService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@Slf4j
public class TaskServiceImpl implements TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Qualifier("analytic-service")
    @Autowired
    private FeignAnalyticService feignAnalyticService;

    @Override
    public List<Task> findAll() {
        log.info("Access TaskService : findAll()");
        return taskRepository.findByIsAndFinished(false);
    }

    @Override
    public Task findById(String id) {
        log.info("Access TaskService : findById()");
        return taskRepository.findById(id).orElse(null);
    }

    private TaskDto createDto(Task newTask) {
        final TaskDto taskDto = new TaskDto();
        taskDto.setId(newTask.getId());
        taskDto.setKategori(newTask.getKategori());
        taskDto.setNama(newTask.getNama());
        taskDto.setCreatedDate(newTask.getCreatedDate().toString());
        taskDto.setFinished(true);
        return taskDto;
    }

    @Override
    public Task saveOrUpdateTask(Task task) {
        log.info("Access TaskService : saveOrUpdateTask()");
        Task taskdb = taskRepository.findByNama(task.getNama());
        if(Objects.nonNull(taskdb)) {
            taskdb.setFinished(task.isFinished());
            TaskDto dto =  createDto(taskdb);
            sendToAnalyticSrv(taskdb, dto);
            return taskRepository.save(taskdb);

        }else{
            task.setCreatedDate(LocalDate.now());
            Task newTask  = taskRepository.save(task);
            return taskRepository.save(task);
        }
    }

    private void sendToAnalyticSrv(Task task, TaskDto dto) {
        if(task.isFinished()) {
            log.info("Access Analytic Service using Feign : createFinishedTask()");
            String result = feignAnalyticService.createFinishedTask(dto);
            log.info("Result from Analytic Service using Feign is {} ",result);
        }
    }

    @Override
    public void deleteById(String id) {
        log.info("Access TaskService : deleteById()");
        taskRepository.deleteById(id);
    }

    @Override
    public void deleteAll() {
        log.info("Access TaskService : deleteAll()");
        taskRepository.deleteAll();
    }

    @Override
    public List<Task> findByIsFinished(boolean finished) {
        log.info("Access TaskService : findByIsFinished()");
        return taskRepository.findByIsAndFinished(finished);
    }

    @Override
    public List<Task> findByName(String name) {
        log.info("Access TaskService : findByName()");
        return taskRepository.findByNamaContaining(name);
    }
}
