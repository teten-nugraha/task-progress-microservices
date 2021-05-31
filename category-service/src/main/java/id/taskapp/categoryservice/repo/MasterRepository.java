package id.taskapp.categoryservice.repo;

import id.taskapp.categoryservice.domain.Master;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MasterRepository extends JpaRepository<Master, String> {
}
