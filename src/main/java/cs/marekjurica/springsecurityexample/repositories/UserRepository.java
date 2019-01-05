package cs.marekjurica.springsecurityexample.repositories;

import org.springframework.data.repository.CrudRepository;

import cs.marekjurica.springsecurityexample.domain.User;

public interface UserRepository extends CrudRepository<User, Long>{
    User findByUsername(String username);
}
