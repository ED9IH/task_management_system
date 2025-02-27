package task_management_system.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import task_management_system.entity.Task;
import task_management_system.util.Status;

import java.util.Optional;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    @Query("SELECT t FROM Task t WHERE t.author.email = :email")
    Page<Task> findAllByAuthorEmail(@Param("email") String email, Pageable pageable);
    @Query("SELECT t FROM Task t WHERE t.status = :status")
    Page<Task> findAllByAuthorStatus(@Param("status") Status status, Pageable pageable);
}
