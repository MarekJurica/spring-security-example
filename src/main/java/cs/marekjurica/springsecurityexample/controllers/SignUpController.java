package cs.marekjurica.springsecurityexample.controllers;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import cs.marekjurica.springsecurityexample.commands.UserCommand;
import cs.marekjurica.springsecurityexample.services.UserService;

@Controller
public class SignUpController {
    
    UserService userService;
    
    public SignUpController(UserService userService) {
        this.userService = userService;
    }

    @ModelAttribute("userCommand")
    public UserCommand userCommand() {
        return new UserCommand();
    }    
    
    @GetMapping("/signup")
    public String getSignUpPage() {        
        return "signup";
    }
    
    @PostMapping("/signup")
    public String saveUser(@ModelAttribute("userCommand") @Valid UserCommand userCommand, BindingResult bindingResult) {
        
        if (userService.findByUsername(userCommand.getUsername()).isPresent()) {
            bindingResult.rejectValue("username", null, "User " + userCommand.getUsername() + " already exists!");
        }
        
        if (!userCommand.getPassword().equals(userCommand.getRepeatPassword())) {
            bindingResult.rejectValue("repeatPassword", null, "Passwords do not match!");
        }
        
        if (bindingResult.hasErrors()) {
            return "signup";
            
        }else {
            
            userService.save(userCommand);
            return "redirect:/";
        }       
    }
}
