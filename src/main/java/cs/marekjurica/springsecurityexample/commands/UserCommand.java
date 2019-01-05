package cs.marekjurica.springsecurityexample.commands;

import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserCommand {
    
    @NotBlank
    private String username;
    
    @NotBlank
    private String password;
    
    private String repeatPassword;
}
