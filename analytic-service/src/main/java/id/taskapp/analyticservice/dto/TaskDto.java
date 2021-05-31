package id.taskapp.analyticservice.dto;

import lombok.Data;

@Data
public class TaskDto {

    private String kategori;
    private String nama;
    private boolean finished;
    private String createdDate;


}
