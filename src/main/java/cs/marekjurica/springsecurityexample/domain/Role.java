package cs.marekjurica.springsecurityexample.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import lombok.Data;

@Entity
@Data
public class Role {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    private String name;
    
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_role", 
    joinColumns = @JoinColumn(name = "role_id"), 
    inverseJoinColumns = @JoinColumn(name = "user_id"))
    private List<User> users = new ArrayList<>();
    

}
