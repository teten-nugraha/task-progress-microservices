package id.taskapp.taskservice.repo;

import id.taskapp.taskservice.domain.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, String> {

    @Query(
            nativeQuery = true,
            value = "select * from tb_task where is_finished = ?1"
    )
    List<Task> findByIsAndFinished(boolean finished);

    @Query(
            nativeQuery = true,
            value = "select * from tb_task where nama  like %?1% and  is_finished = false"
    )
    List<Task> findByNamaContaining(String nama);

    @Query(
            nativeQuery = true,
            value = "select * from tb_task "
    )
    List<Task> getAllTasks();
}
