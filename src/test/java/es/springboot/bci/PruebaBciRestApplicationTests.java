package es.springboot.bci;

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
	
	/*TEST EMAIL*/
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
	
	
	/*TEST PASSWORD*/
	@ParameterizedTest(name = "#{index} - Run test with password = {0}")
    @MethodSource("validPasswordProvider")
    void test_password_regex_valid(String password) {
        assertTrue(methods.checkPasswordFormat(password));
    }
	
	@ParameterizedTest(name = "#{index} - Run test with password = {0}")
    @MethodSource("invalidPasswordProvider")
    void test_password_regex_invalid(String password) {
        assertFalse(methods.checkPasswordFormat(password));
    }
	
	/*Ejecucion pruebas Email*/
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
	
	/*Ejecucion pruebas Password*/
	static Stream<String> validPasswordProvider(){
		return Stream.of(
				"123ABc._",
				"AAA!f#$=1",
				"0230420342$gdHGbdfAA",
				"Asd123$h"
				);
	}
	
	static Stream<String> invalidPasswordProvider(){
		return Stream.of(
				"12334563456",
				"abcdefghijkl",
				"ABXCDSEGHSDG",
				"abcd12345$",
				"ABC34562345",
				"dfgd2345ABCD",
				"_______",
				" ",
				""
				);
	}
}
