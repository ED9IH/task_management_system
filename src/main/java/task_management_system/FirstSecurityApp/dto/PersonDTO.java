package task_management_system.FirstSecurityApp.dto;

import lombok.Getter;
import lombok.Setter;
import task_management_system.util.Role;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
@Getter
@Setter
public class PersonDTO {
    @NotEmpty(message = "Имя не должно быть пустым")
    @Email
    private String email;
    private String password;
    private Role role;
}
