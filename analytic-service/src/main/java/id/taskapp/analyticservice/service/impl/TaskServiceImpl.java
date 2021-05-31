package id.taskapp.analyticservice.service.impl;

import id.taskapp.analyticservice.dto.AnalyticReportDto;
import id.taskapp.analyticservice.dto.CountReportDto;
import id.taskapp.analyticservice.dto.TaskDto;
import id.taskapp.analyticservice.model.Task;
import id.taskapp.analyticservice.repository.TaskRepository;
import id.taskapp.analyticservice.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;


@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Override
    public Task createFinishedTask(TaskDto taskDto) {
        Task task = createTaskEntity(taskDto);
        return taskRepository.save(task);
    }

    private Task createTaskEntity(TaskDto taskDto) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate createdDate = LocalDate.parse(taskDto.getCreatedDate().substring(0,10), formatter);
        LocalDate finishedDate = LocalDate.now();

        final Task task = new Task();
        task.setKategori(taskDto.getKategori());
        task.setNama(taskDto.getNama());
        task.setCreatedDate(createdDate);
        task.setFinishedDate(finishedDate);
        task.setFinished(true);
        return task;
    }

    @Override
    public List<Task> findAllByFinished(boolean finished) {
        return taskRepository.findAllByFinished(finished);
    }

    @Override
    public List<Task> findAllByKategori(String kategori) {
        return taskRepository.findAllByKategori(kategori);
    }

    @Override
    public List<AnalyticReportDto> findAll() {
        List<Task> taskList = taskRepository.findAll();
        List<AnalyticReportDto> dtos = new ArrayList<>();
        for(Task task: taskList) {
            final AnalyticReportDto dto = new AnalyticReportDto();
            dto.setUuid(task.getId());
            dto.setKategori(task.getKategori());
            dto.setNama(task.getNama());
            dto.setCountDays(calculateCountDay(task));
            dtos.add(dto);
        }
        return dtos;
    }

    @Override
    public CountReportDto getReport() {

        List<Task> listProgramming = taskRepository.findAllByKategori("Programming");
        List<Task> listReading = taskRepository.findAllByKategori("Reading");

        final CountReportDto dto = createReportDto(listProgramming, listReading);


        return dto;
    }

    private CountReportDto createReportDto(List<Task> listProgramming, List<Task> listReading) {
        final CountReportDto dto = new CountReportDto();
        dto.setCountProgramming(listProgramming.size());
        dto.setCountReading(listReading.size());

        return dto;
    }

    private Long calculateCountDay(Task task) {
        LocalDate dateFrom = task.getCreatedDate();
        LocalDate dateTo = task.getFinishedDate();

        long intervalDays = dateTo.toEpochDay() - dateFrom.toEpochDay();
        return intervalDays;
    }

    public long getDaysCountBetweenDates(LocalDate dateBefore, LocalDate dateAfter) {
        return ChronoUnit.DAYS.between(dateBefore, dateAfter);
    }
}
