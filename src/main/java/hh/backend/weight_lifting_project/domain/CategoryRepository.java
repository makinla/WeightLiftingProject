package hh.backend.weight_lifting_project.domain;

import org.springframework.data.repository.CrudRepository;

public interface CategoryRepository extends CrudRepository<Category, Long>{

    boolean existsByCategoryName(String categoryName);
}
