package cs.marekjurica.springsecurityexample.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import cs.marekjurica.springsecurityexample.domain.User;

@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {
    
    private UserService userService;    

    public UserDetailsServiceImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        
        Optional<User> userOptional = userService.findByUsername(username);
        if (!userOptional.isPresent()) {
            throw new UsernameNotFoundException("User " + username + " does not exist!");
        }
        
        User user = userOptional.get();
        
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        user.getRoles().forEach(role -> {
            grantedAuthorities.add(new SimpleGrantedAuthority(role.getName()));
        });
        
        return new org.springframework.security.core.userdetails.User(
                user.getUsername(), user.getPassword(), grantedAuthorities);
    }
}
