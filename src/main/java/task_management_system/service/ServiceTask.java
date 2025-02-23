package task_management_system.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import task_management_system.dto.TaskDTO;
import task_management_system.entity.Comment;
import task_management_system.entity.Task;
import task_management_system.entity.User;
import task_management_system.mapper.CreateTaskDTO;
import task_management_system.repositories.TaskRepository;
import task_management_system.repositories.UserRepository;
import task_management_system.util.Priority;
import task_management_system.util.Role;
import task_management_system.util.Status;


import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ServiceTask {
    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private CreateTaskDTO createTaskDTO;
    @Transactional
    public List<TaskDTO> getAllTask() {
        return createTaskDTO.toDTOAllTask(taskRepository.findAll());
    }
    public Task getTaskById(long id) {
        return taskRepository.findById(id).orElseThrow(() -> new RuntimeException("Task not found"));
    }
//    @Transactional
//    public Task createNewTask(String title,String description,long user_id) {
//        List<Comment> comments=new ArrayList<>();
//        User user=userRepository.findById(user_id).orElseThrow(() -> new RuntimeException("User not found"));
//        Task task = new Task(title,description, Status.NOT_SPECIFIED,Priority.NOT_SPECIFIED,user,null);
//        Comment comment = new Comment("No comment",task,user);
//        comments.add(comment);
//        task.setComments(comments);
//        return taskRepository.save(task);
//    }
    public Task editTaskPriority(long id, Priority priority) {
        Task task = getTaskById(id);
        return taskRepository.save(task);
    }
}
