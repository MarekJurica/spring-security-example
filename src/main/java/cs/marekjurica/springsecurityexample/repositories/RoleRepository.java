package cs.marekjurica.springsecurityexample.repositories;

import org.springframework.data.repository.CrudRepository;

import cs.marekjurica.springsecurityexample.domain.Role;

public interface RoleRepository extends CrudRepository<Role, Long>{

}
