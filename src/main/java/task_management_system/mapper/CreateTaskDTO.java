package task_management_system.mapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import task_management_system.dto.TaskDTO;
import task_management_system.entity.Task;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", uses = {CreateUserDTO.class, MapperCommentDTO.class})
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

    // Метод для преобразования Page<Task> в Page<TaskDTO>
    default Page<TaskDTO> toDTOPage(Page<Task> taskPage) {
        List<TaskDTO> dtoList = taskPage.getContent().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());

        return new PageImpl<>(dtoList, taskPage.getPageable(), taskPage.getTotalElements());
    }
}