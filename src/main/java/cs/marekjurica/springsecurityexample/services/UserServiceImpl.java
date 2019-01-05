package cs.marekjurica.springsecurityexample.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import cs.marekjurica.springsecurityexample.commands.UserCommand;
import cs.marekjurica.springsecurityexample.domain.User;
import cs.marekjurica.springsecurityexample.repositories.UserRepository;

@Service
public class UserServiceImpl implements UserService{
    
    private UserRepository userRepository;
    
    private BCryptPasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Optional<User> findByUsername(String username) {         
        
        return Optional.ofNullable(userRepository.findByUsername(username));
    }

    @Override
    public UserCommand save(UserCommand command) {
        
        User user = new User();
        user.setUsername(command.getUsername());
        user.setPassword(passwordEncoder.encode(command.getPassword()));
        
        User savedUser = userRepository.save(user);
        
        UserCommand savedCommand = new UserCommand();
        savedCommand.setUsername(savedUser.getUsername());
        savedCommand.setPassword(savedUser.getPassword());
        
        return savedCommand;
    }

    @Override
    public List<User> findAll() {

        List<User> users = new ArrayList<>();
        userRepository.findAll().forEach(users::add);
        
        return users;
    }

}
