package task_management_system.FirstSecurityApp.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import task_management_system.util.Role;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

/**
 * @author Neil Alishev
 */
public class PersonDTO {
    @NotEmpty(message = "Имя не должно быть пустым")
    @Email
    private String email;

    private String password;

    private Role role;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
