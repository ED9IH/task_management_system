package task_management_system.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import task_management_system.dto.GetAllCommentsDTO;
import task_management_system.entity.Comment;
import task_management_system.mapper.CreateCommentDTO;
import task_management_system.repositories.CommentRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ServiceComment {
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private CreateCommentDTO createCommentDTO;
//    public Comment createNewComment (long taskId,long authorId,String text){
//        User author = userRepository.findById(authorId).orElseThrow(()-> new RuntimeException("Comment not found"));
//        Task task = taskRepository.findById(taskId).orElseThrow(()-> new RuntimeException("Comment not found"));
//        return commentRepository.save(new Comment(text,task,author));
//    }
    public Comment getCommentById(long id){
       return commentRepository.findById(id).orElseThrow(()-> new RuntimeException("Comment not found"));
    }
    public List<GetAllCommentsDTO> getAllComments(){
        return createCommentDTO.getAllCommentDTO(commentRepository.findAll());
    }
}
