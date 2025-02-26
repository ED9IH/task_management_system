package task_management_system.dto;

import lombok.Getter;
import lombok.Setter;
import task_management_system.entity.Comment;
import task_management_system.util.Priority;
import task_management_system.util.Status;

import java.util.List;

@Getter
@Setter
public class CreateNewTaskDTO {

    private String title;
    private String description;
    private Status status;
    private Priority priority;
    private String email;
    private String text;
    private String author;



}
