package id.taskapp.taskservice.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TaskDto {

    private String id;
    private String kategori;
    private String nama;
    private boolean finished;

    @JsonFormat(pattern="yyyy-MM-dd")
    private String createdDate;

}
