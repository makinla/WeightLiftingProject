package hh.backend.weight_lifting_project.domain;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
public class ExerciseTest {

    @Autowired
    private ExerciseRepository repository;

    @Test
    public void createNewExercise() {
        Exercise exercise = new Exercise("Pull up", new Category("Calisthenics"));
        repository.save(exercise);
        assertThat(exercise.getExerciseId()).isNotNull();
    }

    
}
