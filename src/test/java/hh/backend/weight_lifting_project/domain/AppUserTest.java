package hh.backend.weight_lifting_project.domain;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
public class AppUserTest {
    
    @Autowired
    private AppUserRepository repository;

    @Test
    public void createNewAppUser() {
        AppUser appUser = new AppUser("lifter", "$2a$10$iQZXOHM7DbT6hFOd445AZOkFQuI6OYHICLKucneGSIi/Cs830O4QK", "USER");
        repository.save(appUser);
        assertThat(appUser.getId()).isNotNull();
    }

}    
