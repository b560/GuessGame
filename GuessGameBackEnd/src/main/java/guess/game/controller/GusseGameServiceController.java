package guess.game.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import guess.game.service.GuessGameService;

@RestController
public class GusseGameServiceController {
	
	@Autowired
	private GuessGameService guessService;
	
	@GetMapping("/guessedescriptions/{word}")
	@ResponseBody
	public List<String>  guesses(@PathVariable("word") String guessWord) {
		return guessService.validateCharacters(guessWord);
	}

}
