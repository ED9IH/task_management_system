package task_management_system.FirstSecurityApp.services;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import task_management_system.FirstSecurityApp.security.PersonDetails;
import task_management_system.entity.User;
import task_management_system.repositories.UserRepository;
import java.util.Optional;

@Service
public class PersonDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Autowired
    public PersonDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Optional<User> person = userRepository.findByEmail(s);

        if (person.isEmpty())
            throw new UsernameNotFoundException("User not found");

        return new PersonDetails(person.get());
    }
}
