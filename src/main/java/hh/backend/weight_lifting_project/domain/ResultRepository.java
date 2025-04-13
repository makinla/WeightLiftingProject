package hh.backend.weight_lifting_project.domain;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface ResultRepository extends CrudRepository<Result, Long> {
    
    List<Result> findByAppUser_Username(String username);
}
