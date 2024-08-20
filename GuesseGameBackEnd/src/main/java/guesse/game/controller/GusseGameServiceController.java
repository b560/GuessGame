package guesse.game.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import guesse.game.service.GusseGameService;

@RestController
public class GusseGameServiceController {
	
	@Autowired
	private GusseGameService gusseService;
	
	@GetMapping("/guessedescriptions/{word}")
	@ResponseBody
	public List<String>  guesses(@PathVariable("word") String guessWord) {
		return gusseService.validateCharacters(guessWord);
	}

}
