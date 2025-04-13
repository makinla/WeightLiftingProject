package hh.backend.weight_lifting_project;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import hh.backend.weight_lifting_project.domain.AppUser;
import hh.backend.weight_lifting_project.domain.AppUserRepository;
import hh.backend.weight_lifting_project.domain.Category;
import hh.backend.weight_lifting_project.domain.CategoryRepository;
import hh.backend.weight_lifting_project.domain.Exercise;
import hh.backend.weight_lifting_project.domain.ExerciseRepository;
import hh.backend.weight_lifting_project.domain.Result;
import hh.backend.weight_lifting_project.domain.ResultRepository;

@SpringBootApplication
public class WeightLiftingProjectApplication {
	private static final Logger log = LoggerFactory.getLogger(WeightLiftingProjectApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(WeightLiftingProjectApplication.class, args);
	}

	@Bean
	public CommandLineRunner demo(CategoryRepository cRepository, ExerciseRepository eRepository, ResultRepository rRepository, AppUserRepository uRepository) {
		return (args) -> {
			log.info("save a couple results");
			Category category1 = new Category("Lower Body");
			cRepository.save(category1);
			Category category2 = new Category("Upper Body");
			cRepository.save(category2);

			Exercise exercise1 = new Exercise("Squat", category1);
			eRepository.save(exercise1);
			Exercise exercise2 = new Exercise("Deadlift", category1);
			eRepository.save(exercise2);
			Exercise exercise3 = new Exercise("Bench Press", category2);
			eRepository.save(exercise3);

			rRepository.save(new Result(BigDecimal.valueOf(50.0), 2, 1, 6, LocalDate.of(2024, 12, 15), exercise3));
			rRepository.save(new Result(BigDecimal.valueOf(80.0), 1, 1, 4, LocalDate.of(2025, 3, 5), exercise2));
			rRepository.save(new Result(BigDecimal.valueOf(60.0), 2, 3, 7, LocalDate.of(2024, 11, 9), exercise1));
			rRepository.save(new Result(BigDecimal.valueOf(74.0), 1, 8, 3, LocalDate.of(2024, 12, 27), exercise2));
			rRepository.save(new Result(BigDecimal.valueOf(40.0), 8, 3, 6, LocalDate.of(2025, 1, 17), exercise3));

			AppUser user1 = new AppUser("user", "$2a$10$.HPh.2w0qOWlhkiL7iY6Tu7fqgW7c4JOeMevu7T8lrRrinXfsewZO", "USER");
			AppUser user2 = new AppUser("admin", "$2a$10$n01kJPgoY9VX9Rb/0/2sMeknpUyjqbbJuYUyVW/vHwsV2Z0rclIES", "ADMIN");
			uRepository.save(user1);
			uRepository.save(user2);

			log.info("fetch all categories");
			for (Category category : cRepository.findAll()) {
				log.info(category.toString());
			
			}

		};
	}
}	