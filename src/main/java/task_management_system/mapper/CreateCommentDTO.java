package task_management_system.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import task_management_system.dto.GetAllCommentsDTO;
import task_management_system.entity.Comment;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface CreateCommentDTO {
    @Mapping(source = "author", target = "userDTO")
    GetAllCommentsDTO toDto (Comment comment);
    default List<GetAllCommentsDTO> getAllCommentDTO(List<Comment> comment){
        return comment.stream().map(this::toDto).
                collect(Collectors.toList());
    }
}
