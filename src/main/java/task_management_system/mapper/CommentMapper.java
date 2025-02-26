package task_management_system.mapper;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import task_management_system.entity.Comment;
import java.util.Collections;
import java.util.List;
@Mapper(componentModel = "spring")
public interface CommentMapper {
    CommentMapper INSTANCE = Mappers.getMapper(CommentMapper.class);
    default List<Comment> map(Comment comment) {
        return comment != null ? Collections.singletonList(comment) : Collections.emptyList();
    }
}
