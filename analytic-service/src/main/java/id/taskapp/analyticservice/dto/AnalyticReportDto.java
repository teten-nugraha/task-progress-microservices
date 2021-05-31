package id.taskapp.analyticservice.dto;

import lombok.Data;

@Data
public class AnalyticReportDto {

    private String uuid;
    private String kategori;
    private String nama;
    private Long countDays;

}
