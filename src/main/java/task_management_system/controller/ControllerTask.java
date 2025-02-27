package task_management_system.controller;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import task_management_system.dto.CreateNewTaskDTO;
import task_management_system.dto.TaskDTO;
import task_management_system.entity.Task;
import task_management_system.service.ServiceTask;
import task_management_system.util.Priority;
import task_management_system.util.Status;

@Tag(name = "Работа с задачами")
@RestController
@RequestMapping("/task")
public class ControllerTask {
    @Autowired
    private ServiceTask taskService;

    @Operation(summary = "Получение всех задач")
    @GetMapping("/all")
    public Page<TaskDTO> getAllTasks(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return taskService.getAllTasks(pageable);
    }

    @Operation(summary = "Поиск задачи по id")
    @GetMapping("/{taskId}")
    public ResponseEntity<Page<TaskDTO>> getTaskById(@RequestParam long taskId,
                                            @RequestParam(defaultValue = "0") int page,
                                            @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page,size);
        return ResponseEntity.ok(taskService.getTaskById(taskId,pageable));
    }

    @Operation(summary = "Создание новой задачи")
    @PostMapping("/newTask")
    private ResponseEntity<String> createNewTask(@RequestBody CreateNewTaskDTO createNewTaskDTO) {
        taskService.createNewTask(createNewTaskDTO);
        return ResponseEntity.ok("Новая задача создана");
    }

    @Operation(summary = "Обновление статуса задачи")
    @PutMapping("/editStatus")
    public ResponseEntity<String> editStatusTask(long id, Status status) {
        taskService.editStatusTask(id, status);
        return ResponseEntity.ok("Статус задачи изменен");
    }

    @Operation(summary = "Обновление приоритета задачи")
    @PutMapping("/editPriority")
    public ResponseEntity<String> editPriorityTask(long id, Priority priority) {
        taskService.editPriorityTask(id, priority);
        return ResponseEntity.ok("Приоритет задачи изменен");
    }

    @Operation(summary = "Удаление задачи")
    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteTask(long id) {
        taskService.deleteTask(id);
        return ResponseEntity.ok("Задача удалена");
    }

    @Operation(summary = "Добавление комментария к задачи")
    @PostMapping("/addComment")
    public ResponseEntity<String> addCommentTask(long id, String text, String author) {
        taskService.addComment(id, text, author);
        return ResponseEntity.ok("Коментарий добавлен");
    }

    @Operation(summary = "Получение всех задач по статусу")
    @GetMapping("/getTaskByStatus")
    public ResponseEntity<Page<TaskDTO>> getTaskById(@RequestParam Status status,
                                                     @RequestParam(defaultValue = "0") int page,
                                                     @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page,size);
        return ResponseEntity.ok(taskService.getAllTaskStatus(status,pageable));
    }

    @Operation(summary = "Получение всех задач по Users, нужно указать email")
    @GetMapping("/getTaskByEmail")
    public Page<TaskDTO> getAllTasksByUser(
            @RequestParam String email,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return taskService.getAllTasksByUser(email, pageable);
    }

}
