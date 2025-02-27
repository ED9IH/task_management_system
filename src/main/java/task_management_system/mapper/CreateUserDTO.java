package task_management_system.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import task_management_system.dto.UserDTO;
import task_management_system.entity.User;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface CreateUserDTO {
    @Mapping(source = "id", target = "id")
    @Mapping(source = "email", target = "email")
    @Mapping(source = "role", target = "role")
    UserDTO toDTO(User user);

    default List<UserDTO> toUserDTOAll(List<User> users) {
        return users.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    default Page<UserDTO> toDTOPage(Page<User> taskPage) {
        List<UserDTO> dtoList = taskPage.getContent().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());

        return new PageImpl<>(dtoList, taskPage.getPageable(), taskPage.getTotalElements());
    }
}
