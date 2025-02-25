package task_management_system.FirstSecurityApp.dto;

import lombok.Getter;
import lombok.Setter;
import task_management_system.util.Role;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

/**
 * @author Neil Alishev
 */
@Setter
@Getter
public class AuthenticationDTO {
    @NotEmpty(message = "Имя не должно быть пустым")
    @Email
    private String email;

    private String password;

    private Role role;

}
