package task_management_system;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import task_management_system.dto.UserDTO;
import task_management_system.entity.User;
import task_management_system.mapper.CreateUserDTO;
import task_management_system.repositories.UserRepository;
import task_management_system.service.ServiceUser;
import task_management_system.util.Role;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class UserServiceTestGetAllUsers {
    @Mock
    private UserRepository userRepository;

    @Mock
    private CreateUserDTO createUserDTO;

    @InjectMocks
    private ServiceUser serviceUser;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    @Test
    public void testGetAllUsers() {
        Pageable pageable = mock(Pageable.class);
        Page<User> userPage = new PageImpl<>(Collections.singletonList(new User()));
        Page<UserDTO> userDTOPage = new PageImpl<>(Collections.singletonList(new UserDTO(1,"demanin@bk.ru", Role.ADMIN.name())));

        when(userRepository.findAll(pageable)).thenReturn(userPage);
        when(createUserDTO.toDTOPage(userPage)).thenReturn(userDTOPage);
        Page<UserDTO> result = serviceUser.getAllUsers(pageable);

        assertEquals(userDTOPage, result);
        verify(userRepository, times(1)).findAll(pageable);
        verify(createUserDTO, times(1)).toDTOPage(userPage);
    }
}
