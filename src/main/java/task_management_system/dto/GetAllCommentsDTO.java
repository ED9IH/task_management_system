package task_management_system.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetAllCommentsDTO {
    private long id;
    private String text;
    private UserDTO userDTO;
}
