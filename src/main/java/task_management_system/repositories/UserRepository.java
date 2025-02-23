package task_management_system.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import task_management_system.entity.User;
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
