package task_management_system.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import task_management_system.dto.UserDTO;
import task_management_system.entity.Task;
import task_management_system.entity.User;
import task_management_system.mapper.CreateUserDTO;
import task_management_system.repositories.UserRepository;
import java.util.List;

@Service
public class ServiceUser {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CreateUserDTO createUserDTO;

    public Page<UserDTO> getAllUsers(Pageable pageable) {
        Page<User> taskPage = userRepository.findAll(pageable);
        return createUserDTO.toDTOPage(taskPage);
    }

    public User getUserByID(long id) {
        return userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
    }
}
