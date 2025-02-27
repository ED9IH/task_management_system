package task_management_system.mapper;

import org.mapstruct.Mapper;
import task_management_system.dto.GetAllCommentsDTO;
import task_management_system.entity.Comment;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface MapperCommentDTO {

    GetAllCommentsDTO toDto(Comment comment);

    default List<GetAllCommentsDTO> getAllCommentDTO(List<Comment> comment) {
        return comment.stream().map(this::toDto).
                collect(Collectors.toList());
    }
}
