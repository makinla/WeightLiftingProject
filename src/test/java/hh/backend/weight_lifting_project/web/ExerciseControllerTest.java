package hh.backend.weight_lifting_project.web;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ExerciseControllerTest {
    
    @Autowired
    private ExerciseController controller;

        @Test
            public void contextLoads() throws Exception {
                assertThat(controller).isNotNull();
            }


}
