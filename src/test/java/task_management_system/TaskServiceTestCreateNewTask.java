package task_management_system;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import task_management_system.dto.CreateNewTaskDTO;
import task_management_system.entity.Comment;
import task_management_system.entity.Task;
import task_management_system.entity.User;
import task_management_system.repositories.CommentRepository;
import task_management_system.repositories.TaskRepository;
import task_management_system.repositories.UserRepository;
import task_management_system.service.ServiceTask;
import task_management_system.util.Priority;
import task_management_system.util.Status;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class TaskServiceTestCreateNewTask {
    @Mock
    private TaskRepository taskRepository;
    @Mock
    private UserRepository userRepository;
    @Mock
    private CommentRepository commentRepository;
    @InjectMocks
    private ServiceTask serviceTask;
    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    @Test
    public void testCreateNewTask_Success() {
        // Arrange
        CreateNewTaskDTO newTaskDTO = new CreateNewTaskDTO();
        newTaskDTO.setPriority(Priority.HIGH);
        newTaskDTO.setTitle("Test Task");
        newTaskDTO.setDescription("Test Description");
        newTaskDTO.setEmail("test@example.com");
        newTaskDTO.setText("Test Comment");

        User author = new User();
        author.setEmail("test@example.com");
        Task savedTask = new Task();

        savedTask.setId(2L);
        savedTask.setPriority(newTaskDTO.getPriority());
        savedTask.setTitle(newTaskDTO.getTitle());
        savedTask.setDescription(newTaskDTO.getDescription());
        savedTask.setStatus(Status.FREE);
        savedTask.setAuthor(author);

        Comment savedComment = new Comment();
        savedComment.setTask(savedTask);
        savedComment.setText(newTaskDTO.getText());
        savedComment.setAuthor(author.getEmail());

        when(userRepository.findByEmail(newTaskDTO.getEmail())).thenReturn(Optional.of(author));
        when(taskRepository.save(any(Task.class))).thenReturn(savedTask);
        when(commentRepository.save(any(Comment.class))).thenReturn(savedComment);
        when(taskRepository.getById(savedTask.getId())).thenReturn(savedTask);

        Task result = serviceTask.createNewTask(newTaskDTO);

        assertNotNull(result);
        assertEquals(savedTask.getId(), result.getId());
        assertEquals(newTaskDTO.getTitle(), result.getTitle());
        assertEquals(newTaskDTO.getDescription(), result.getDescription());
        assertEquals(Status.FREE, result.getStatus());
        assertEquals(author, result.getAuthor());

        verify(userRepository, times(1)).findByEmail(newTaskDTO.getEmail());
        verify(taskRepository, times(1)).save(any(Task.class));
        verify(commentRepository, times(1)).save(any(Comment.class));
        verify(taskRepository, times(2)).getById(savedTask.getId());
    }
    @Test
    public void testCreateNewTask_UserNotFound() {
        // Arrange
        CreateNewTaskDTO newTaskDTO = new CreateNewTaskDTO();
        newTaskDTO.setEmail("wrong@example.com");

        when(userRepository.findByEmail(newTaskDTO.getEmail())).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            serviceTask.createNewTask(newTaskDTO);
        });

        assertEquals("Вы указали не правильный email", exception.getMessage());

        verify(userRepository, times(1)).findByEmail(newTaskDTO.getEmail());
        verify(taskRepository, never()).save(any(Task.class));
        verify(commentRepository, never()).save(any(Comment.class));
    }
}
