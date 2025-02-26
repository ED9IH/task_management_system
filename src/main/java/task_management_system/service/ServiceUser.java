package task_management_system.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import task_management_system.dto.UserDTO;
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

    public List<UserDTO> getAllUsers() {
        return createUserDTO.toUserDTOAll(userRepository.findAll());
    }

    public User getUserByID(long id) {
        return userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
    }
}
