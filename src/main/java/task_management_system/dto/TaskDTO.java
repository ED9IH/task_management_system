package task_management_system.dto;

import lombok.Getter;
import lombok.Setter;
import task_management_system.util.Priority;
import task_management_system.util.Status;

import java.util.List;

@Getter
@Setter
public class
TaskDTO {
    private long taskId;
    private UserDTO userDTO;
    private String title;
    private String description;
    private Status status;
    private Priority priority;
    private List<GetAllCommentsDTO> commentsDTO;
}
