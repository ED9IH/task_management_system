package task_management_system.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import task_management_system.dto.TaskDTO;
import task_management_system.entity.Task;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", uses = {CreateUserDTO.class, CreateCommentDTO.class})
public interface CreateTaskDTO {
    @Mapping(source = "id", target = "taskId")
    @Mapping(source = "author", target = "userDTO")
    @Mapping(source = "comment", target = "commentsDTO")
    TaskDTO toDTO(Task task);
    @Mapping(source = "taskId", target = "id")
    @Mapping(source = "userDTO", target = "author")
    @Mapping(source = "commentsDTO", target = "comment")
    Task toEntity(TaskDTO taskDTO);
    default List<TaskDTO> toDTOAllTask(List<Task> tasks) {
        return tasks.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }
    default List<Task> toEntityList(List<TaskDTO> taskDTOs) {
        return taskDTOs.stream()
                .map(this::toEntity)
                .collect(Collectors.toList());
    }
}
