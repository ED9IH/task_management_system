package task_management_system;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import task_management_system.dto.TaskDTO;
import task_management_system.entity.Task;
import task_management_system.mapper.CreateTaskDTO;
import task_management_system.repositories.TaskRepository;
import task_management_system.service.ServiceTask;
import task_management_system.util.Status;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
public class TaskServiceTestGetAllTasks {
    @Mock
    private TaskRepository taskRepository;
    @Mock
    private CreateTaskDTO createTaskDTO;
    @InjectMocks
    private ServiceTask serviceTask;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllTasks_Success() {
        Pageable pageable = mock(Pageable.class);
        Task task = new Task();
        task.setId(1L);
        task.setTitle("Test Task");
        task.setDescription("Test Description");
        task.setStatus(Status.FREE);

        Page<Task> taskPage = new PageImpl<>(Collections.singletonList(task));
        Page<TaskDTO> taskDTOPage = new PageImpl<>(Collections.singletonList(new TaskDTO()));

        when(taskRepository.findAll(pageable)).thenReturn(taskPage);
        when(createTaskDTO.toDTOPage(taskPage)).thenReturn(taskDTOPage);

        Page<TaskDTO> result = serviceTask.getAllTasks(pageable);

        assertNotNull(result);
        assertEquals(taskDTOPage, result);

        verify(taskRepository, times(1)).findAll(pageable);
        verify(createTaskDTO, times(1)).toDTOPage(taskPage);
    }
    @Test
    public void testGetAllTasks_EmptyPage() {
        // Arrange
        Pageable pageable = mock(Pageable.class);
        Page<Task> emptyTaskPage = new PageImpl<>(Collections.emptyList());
        Page<TaskDTO> emptyTaskDTOPage = new PageImpl<>(Collections.emptyList());

        when(taskRepository.findAll(pageable)).thenReturn(emptyTaskPage);
        when(createTaskDTO.toDTOPage(emptyTaskPage)).thenReturn(emptyTaskDTOPage);

        Page<TaskDTO> result = serviceTask.getAllTasks(pageable);

        assertNotNull(result);
        assertTrue(result.isEmpty());

        verify(taskRepository, times(1)).findAll(pageable);
        verify(createTaskDTO, times(1)).toDTOPage(emptyTaskPage);
    }
}
