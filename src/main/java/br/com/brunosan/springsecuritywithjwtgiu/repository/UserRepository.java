package br.com.brunosan.springsecuritywithjwtgiu.repository;

import br.com.brunosan.springsecuritywithjwtgiu.model.UserModel;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<UserModel, String> {
    
    Optional<UserModel> findByUsername(String username);
}
