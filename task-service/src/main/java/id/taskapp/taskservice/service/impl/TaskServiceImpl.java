package id.taskapp.taskservice.service.impl;

import id.taskapp.taskservice.domain.Task;
import id.taskapp.taskservice.repo.TaskRepository;
import id.taskapp.taskservice.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    private TaskRepository taskRepository;

//    @Qualifier("analytic-service")
//    @Autowired
//    private FeignAnalyticService feignAnalyticService;

    @Override
    public List<Task> findAll() {
        return taskRepository.findByIsAndFinished(false);
    }

    @Override
    public Task findById(String id) {
        return taskRepository.findById(id).orElse(null);
    }

//    private TaskDto createDto(Task newTask) {
//        final TaskDto taskDto = new TaskDto();
//        taskDto.setId(newTask.getId());
//        taskDto.setKategori(newTask.getKategori());
//        taskDto.setNama(newTask.getNama());
//        taskDto.setCreatedDate(newTask.getCreatedDate().toString());
//        taskDto.setFinished(true);
//        return taskDto;
//    }

    @Override
    public Task saveOrUpdateTask(Task task) {
        task.setCreatedDate(LocalDate.now());
        Task newTask  = taskRepository.save(task);
//        TaskDto dto =  createDto(newTask);
//        if(task.isFinished()) {
//            String result = feignAnalyticService.createFinishedTask(dto);
//        }
        return taskRepository.save(task);
    }

    @Override
    public void deleteById(String id) {
        taskRepository.deleteById(id);
    }

    @Override
    public void deleteAll() {
        taskRepository.deleteAll();
    }

    @Override
    public List<Task> findByIsFinished(boolean finished) {
        return taskRepository.findByIsAndFinished(finished);
    }

    @Override
    public List<Task> findByName(String name) {
        return taskRepository.findByNamaContaining(name);
    }
}
