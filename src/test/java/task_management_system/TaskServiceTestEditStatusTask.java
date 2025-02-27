package task_management_system;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import task_management_system.entity.Task;
import task_management_system.repositories.TaskRepository;
import task_management_system.service.ServiceTask;
import task_management_system.util.Status;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
public class TaskServiceTestEditStatusTask {
    @Mock
    private TaskRepository taskRepository;

    @InjectMocks
    private ServiceTask serviceTask;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testEditStatusTask_Success() {
        // Arrange
        long taskId = 1L;
        Status newStatus = Status.IN_PROGRESS;

        Task existingTask = new Task();
        existingTask.setId(taskId);
        existingTask.setStatus(Status.FREE);

        when(taskRepository.findById(taskId)).thenReturn(Optional.of(existingTask));
        when(taskRepository.save(any(Task.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Task result = serviceTask.editStatusTask(taskId, newStatus);

        assertNotNull(result);
        assertEquals(taskId, result.getId());
        assertEquals(newStatus, result.getStatus());

        verify(taskRepository, times(1)).findById(taskId);
        verify(taskRepository, times(1)).save(existingTask);
    }

    @Test
    public void testEditStatusTask_TaskNotFound() {
        long taskId = 1L;
        Status newStatus = Status.IN_PROGRESS;

        when(taskRepository.findById(taskId)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            serviceTask.editStatusTask(taskId, newStatus);
        });

        assertEquals("Задача не найдена", exception.getMessage());

        verify(taskRepository, times(1)).findById(taskId);
        verify(taskRepository, never()).save(any(Task.class));
    }
}
