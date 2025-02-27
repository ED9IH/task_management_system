package task_management_system.controller;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import task_management_system.dto.UserDTO;
import task_management_system.service.ServiceUser;

import java.util.List;

@RestController
@RequestMapping("/users")
public class ControllerUser {
    @Autowired
    private ServiceUser serviceUser;

    @Operation(summary = "Посмотреть всех Users")
    @GetMapping("allUsers")
    public Page<UserDTO> getAllUsers(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return serviceUser.getAllUsers(pageable);
    }
}
