package guess.game.service;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import guess.game.service.GuessGameService;

@SpringBootTest
@TestPropertySource("classpath:application-test.properties")
public class GuessGameServiceTest {

	@Autowired
	private GuessGameService guessGameService;

	@Test
	public void validate_word_with_incorrect_characters() {
		List<String> results = guessGameService.validateCharacters("hello");
		Assertions.assertEquals(results.get(0), "Letter h is not found in the word");
		Assertions.assertEquals(results.get(1), "Letter e is not found in the word");
		Assertions.assertTrue(results.contains("Letter e is not found in the word"));
		Assertions.assertEquals(results.get(4), "Letter o is found in the word BUT it is incorrectly positioned");
		// assert the total total of guess that are right
		Assertions.assertEquals(results.get(5), "1");
	}

	@Test
	public void validate_word_with_complete_correct_characters() {
		List<String> results = guessGameService.validateCharacters("world");
		Assertions.assertEquals(results.get(0), "Letter w is found in the word AND it is in the correct position");
		Assertions.assertEquals(results.get(1), "Letter o is found in the word AND it is in the correct position");
		Assertions.assertTrue(results.contains("Letter r is found in the word AND it is in the correct position"));
		Assertions.assertEquals(results.get(4), "Letter d is found in the word AND it is in the correct position");
		// assert the total total of guess that are right
		Assertions.assertEquals(results.get(5), "5");
	}

	@Test
	public void null_guess_input_should_return_exception() throws Exception {
		Throwable exception = Assertions.assertThrows(RuntimeException.class,
				() -> guessGameService.validateCharacters(null));
		Assertions.assertEquals("The guessed string length must be equal to 5", exception.getMessage());
	}

	@Test
	public void guess_input_greaterthan_five_should_return_exception() throws Exception {
		Throwable exception = Assertions.assertThrows(RuntimeException.class,
				() -> guessGameService.validateCharacters("Friends"));
		Assertions.assertEquals("The guessed string length must be equal to 5", exception.getMessage());
	}
	
	@Test
	public void guess_input_lessthan_five_should_return_exception() throws Exception {
		Throwable exception = Assertions.assertThrows(RuntimeException.class,
				() -> guessGameService.validateCharacters("Lies"));
		Assertions.assertEquals("The guessed string length must be equal to 5", exception.getMessage());
	}

}
