package task_management_system.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import task_management_system.dto.TaskDTO;
import task_management_system.entity.Task;
import task_management_system.service.ServiceTask;

import java.util.List;


@Tag(name = "Работа с задачами")
@RestController
@RequestMapping
public class ControllerTask {
    @Autowired
    private ServiceTask taskService;

//    @Operation(summary = "Добавление новой задачи")
//    @PostMapping("/add")
//    public ResponseEntity<Task> createTask(@RequestBody long task, String title, String description) {
//        Task createdTask = taskService.createNewTask(title, description, task);
//        return ResponseEntity.status(HttpStatus.CREATED).body(createdTask);
//    }

    @GetMapping("/all")
    public ResponseEntity<List<TaskDTO>> getAllTask(){
        return ResponseEntity.ok(taskService.getAllTask());
    }

}
