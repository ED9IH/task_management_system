package task_management_system.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import task_management_system.dto.UserDTO;
import task_management_system.service.ServiceUser;

import java.util.List;

@RestController
@RequestMapping("/users")
public class ControllerUser {
    @Autowired
    private ServiceUser serviceUser;
    @Operation(summary = "Посмотреть всех User")
    @GetMapping("allUsers")
    public List<UserDTO> getAllUsers(){
        return serviceUser.getAllUsers();
    }




}
