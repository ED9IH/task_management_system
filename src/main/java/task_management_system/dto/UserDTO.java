package task_management_system.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import task_management_system.util.Role;
@Getter
@Setter
@AllArgsConstructor
public class UserDTO {
    private long id;
    private String email;
    private String role;
}
