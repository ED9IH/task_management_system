package task_management_system.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import task_management_system.dto.CreateNewTaskDTO;
import task_management_system.dto.TaskDTO;
import task_management_system.entity.Comment;
import task_management_system.entity.Task;
import task_management_system.mapper.CreateTaskDTO;
import task_management_system.repositories.CommentRepository;
import task_management_system.repositories.TaskRepository;
import task_management_system.repositories.UserRepository;
import task_management_system.util.Priority;
import task_management_system.util.Status;
import javax.transaction.Transactional;
import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ServiceTask {
    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private CreateTaskDTO createTaskDTO;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CommentRepository commentRepository;

    @Transactional
    public Page<TaskDTO> getAllTasks(Pageable pageable) {
        Page<Task> taskPage = taskRepository.findAll(pageable);
        return createTaskDTO.toDTOPage(taskPage);
    }
    public Page<TaskDTO> getTaskById(long id,Pageable pageable) {
       TaskDTO taskDTO= createTaskDTO.toDTO(taskRepository.findById(id).orElseThrow(()->new RuntimeException("Задача не найдена")));
        List<TaskDTO> taskDTOList = Collections.singletonList(taskDTO);
        return new PageImpl<>(taskDTOList, pageable, 1);
    }

    @Transactional
    public Task createNewTask(CreateNewTaskDTO newTaskDTO) {
        Task task = new Task();
        task.setPriority(newTaskDTO.getPriority());
        task.setTitle(newTaskDTO.getTitle());
        task.setDescription(newTaskDTO.getDescription());
        task.setStatus(Status.FREE);
        task.setAuthor(userRepository.findByEmail(newTaskDTO.getEmail()).orElseThrow(() ->
                new RuntimeException("Вы указали не правильный email")));
        long id = taskRepository.save(task).getId();
        Comment comment = new Comment();
        comment.setTask(taskRepository.getById(id));
        comment.setText(newTaskDTO.getText());
        comment.setAuthor(task.getAuthor().getEmail());
        commentRepository.save(comment);
        return taskRepository.getById(id);
    }

    public Task editStatusTask(long id, Status status) {
        Task task = taskRepository.findById(id).orElseThrow(() -> new RuntimeException("Задача не найдена"));
        task.setStatus(status);
        return taskRepository.save(task);
    }

    public Task editPriorityTask(long id, Priority priority) {
        Task task = taskRepository.findById(id).orElseThrow(() -> new RuntimeException("Задача не найдена"));
        task.setPriority(priority);
        return taskRepository.save(task);
    }


    public Page<TaskDTO> getAllTaskStatus(Status status,Pageable pageable){
        Page<Task> tasks=taskRepository.findAllByAuthorStatus(status,pageable);
        return tasks.map(createTaskDTO::toDTO);
    }
    public Page<TaskDTO> getAllTasksByUser(String email, Pageable pageable) {
        Page<Task> tasks = taskRepository.findAllByAuthorEmail(email, pageable);
        return tasks.map(createTaskDTO::toDTO);
    }

    public void deleteTask(long id) {
        taskRepository.deleteById(id);
    }

    public Comment addComment(long id, String text, String author) {
        Task task = taskRepository.findById(id).orElseThrow(() -> new RuntimeException("Задача не найдена"));
        Comment comment = new Comment();
        comment.setText(text);
        comment.setTask(task);
        comment.setAuthor(author);
        return commentRepository.save(comment);
    }


}


