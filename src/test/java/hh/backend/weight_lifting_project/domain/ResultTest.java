package hh.backend.weight_lifting_project.domain;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
public class ResultTest {

    @Autowired
    private ResultRepository repository;

    @Test
    public void createNewResult() {
        Result result = new Result(BigDecimal.valueOf(10.0), 2, 1, 9, LocalDate.of(2024, 12, 15), new Exercise("Pull up", new Category("Calisthenics")));
        repository.save(result);
        assertThat(result.getId()).isNotNull();
    }
}
