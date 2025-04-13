package hh.backend.weight_lifting_project.domain;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
public class CategoryTest {

    @Autowired
    private CategoryRepository repository;

    @Test
    public void createNewCategory() {
        Category category = new Category("Calisthenics");
        repository.save(category);
        assertThat(category.getCategoryId()).isNotNull();
    }

}
