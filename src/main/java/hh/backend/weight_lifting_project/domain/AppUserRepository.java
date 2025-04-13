package hh.backend.weight_lifting_project.domain;

import org.springframework.data.repository.CrudRepository;

public interface AppUserRepository  extends CrudRepository<AppUser, Long>{
    AppUser findByUsername(String username);
}
