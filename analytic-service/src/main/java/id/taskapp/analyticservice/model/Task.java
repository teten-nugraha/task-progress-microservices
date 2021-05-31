package id.taskapp.analyticservice.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;


@Entity
@Table(name = "tb_task_analyze")
@Data
@ToString
@NoArgsConstructor
public class Task {

    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;
    private String kategori;
    private String nama;
    private boolean finished;
    private LocalDate createdDate;
    private LocalDate finishedDate;

}
