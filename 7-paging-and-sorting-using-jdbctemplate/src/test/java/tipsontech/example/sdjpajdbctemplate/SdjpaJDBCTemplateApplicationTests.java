package tipsontech.example.sdjpajdbctemplate;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import tipsontech.example.sdjpajdbctemplate.repositories.AuthorRepository;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
class SdjpaJDBCTemplateApplicationTests {

	@Autowired
	AuthorRepository authorRepository;

	@Test
	void testAuthorRepository() {
		long count = authorRepository.count();

		assertThat(count).isGreaterThan(0);
	}
	@Test
	void contextLoads() {
	}

}
