package hh.backend.weight_lifting_project.domain;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface ExerciseRepository extends CrudRepository<Exercise, Long> {

    List<Exercise> findByCategory_CategoryId(Long categoryId);
    
    boolean existsByName(String name);

    

}
