package es.springboot.bci;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import es.springboot.bci.controller.UsuarioController;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


@SpringBootTest
class PruebaBciRestApplicationTests {
	
	UsuarioController methods = new UsuarioController();
	
	@Test
	void contextLoads() {
	}
	
	@ParameterizedTest(name = "#{index} - Run test with email = {0}")
    @MethodSource("validEmailProvider")
    void test_email_regex_valid(String email) {
        assertTrue(methods.checkEmailFormat(email));
    }
	
	@ParameterizedTest(name = "#{index} - Run test with email = {0}")
    @MethodSource("invalidEmailProvider")
    void test_email_regex_invalid(String email) {
        assertFalse(methods.checkEmailFormat(email));
    }
	
	static Stream<String> validEmailProvider(){
		return Stream.of(
				"aaaa@gmail.com",
				"test@gmail.org",
				"emailvalid@organization.cl",
				"juanRodriguez@empresa.cl",
				"aaaaaaa@test.org"
				);
	}
	
	static Stream<String> invalidEmailProvider(){
		return Stream.of(
				"@test.com",
				"test.com",
				"a@.com",
				"wrongemail@com."
				);
	}

}
