package task_management_system.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import task_management_system.dto.CreateNewTaskDTO;
import task_management_system.dto.TaskDTO;
import task_management_system.service.ServiceTask;
import task_management_system.util.Priority;
import task_management_system.util.Status;

import java.util.List;


@Tag(name = "Работа с задачами")
@RestController
@RequestMapping("/task")
public class ControllerTask {
    @Autowired
    private ServiceTask taskService;

    @Operation(summary = "Получение всех задач")
    @GetMapping("/all")
    public ResponseEntity<List<TaskDTO>> getAllTask() {
        return ResponseEntity.ok(taskService.getAllTask());
    }

    @Operation(summary = "Поиск задачи по id")
    @GetMapping("/{taskId}")
    public ResponseEntity<TaskDTO> getTaskById(long taskId) {
        return ResponseEntity.ok(taskService.getTaskById(taskId));
    }
    @Operation(summary = "Создание новой задачи")
    @PostMapping("/newTask")
    private ResponseEntity<String> createNewTask(@RequestBody CreateNewTaskDTO createNewTaskDTO) {
        taskService.createNewTask(createNewTaskDTO);
        return ResponseEntity.ok("Новая задача создана");
    }

    @Operation(summary = "Обновление статуса задачи")
    @PutMapping("/editStatus")
    public ResponseEntity<String> editStatusTask(long id, Status status){
        taskService.editStatusTask(id,status);
        return ResponseEntity.ok("Статус задачи изменен");
    }
    @Operation(summary = "Обновление приоритета задачи")
    @PutMapping("/editPriority")
    public ResponseEntity<String> editPriorityTask(long id, Priority priority){
        taskService.editPriorityTask(id,priority);
        return ResponseEntity.ok("Приоритет задачи изменен");
    }
    @Operation(summary = "Удаление задачи")
    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteTask(long id){
        taskService.deleteTask(id);
        return ResponseEntity.ok("Задача удалена");
    }
    @Operation(summary = "Добавление комментария к задачи")
    @PostMapping("/addComment")
    public ResponseEntity<String> addCommentTask(long id,String text,String author){
        taskService.addComment(id,text,author);
        return ResponseEntity.ok("Коментарий добавлен");
    }


}
