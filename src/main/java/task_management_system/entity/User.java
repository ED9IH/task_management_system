package task_management_system.entity;
import lombok.*;
import task_management_system.util.Role;
import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Email
    @Column(name = "email")
    private String email;
    @NotEmpty(message = "Поле не должно быть пустым")
    @Column(name = "password")
    private String password;
    @Enumerated(value = EnumType.STRING)
    @Column(name = "role")
    private Role role;
}
