package cs.marekjurica.springsecurityexample.services;

import java.util.List;
import java.util.Optional;

import cs.marekjurica.springsecurityexample.commands.UserCommand;
import cs.marekjurica.springsecurityexample.domain.User;

public interface UserService {
    
    List<User> findAll();
    
    Optional<User> findByUsername(String username);
    
    UserCommand save(UserCommand command);
}
