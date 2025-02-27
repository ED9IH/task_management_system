package task_management_system;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import task_management_system.entity.Task;
import task_management_system.repositories.TaskRepository;
import task_management_system.service.ServiceTask;
import task_management_system.util.Priority;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class TaskServiceTestEditPriorityTaskTaskNotFound {
    @Mock
    private TaskRepository taskRepository;
    @InjectMocks
    private ServiceTask serviceTask;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testEditPriorityTask_Success() {
        long taskId = 1L;
        Priority newPriority = Priority.HIGH;
        Task existingTask = new Task();
        existingTask.setId(taskId);
        existingTask.setPriority(Priority.LOW);
        when(taskRepository.findById(taskId)).thenReturn(Optional.of(existingTask));
        when(taskRepository.save(any(Task.class))).thenAnswer(invocation -> invocation.getArgument(0));
        Task result = serviceTask.editPriorityTask(taskId, newPriority);
        assertNotNull(result);
        assertEquals(taskId, result.getId());
        assertEquals(newPriority, result.getPriority());
        verify(taskRepository, times(1)).findById(taskId);
        verify(taskRepository, times(1)).save(existingTask);
    }

    @Test
    public void testEditPriorityTask_TaskNotFound() {
        long taskId = 1L;
        Priority newPriority = Priority.HIGH;
        when(taskRepository.findById(taskId)).thenReturn(Optional.empty());
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            serviceTask.editPriorityTask(taskId, newPriority);
        });
        assertEquals("Задача не найдена", exception.getMessage());
        verify(taskRepository, times(1)).findById(taskId);
        verify(taskRepository, never()).save(any(Task.class));
    }
}
