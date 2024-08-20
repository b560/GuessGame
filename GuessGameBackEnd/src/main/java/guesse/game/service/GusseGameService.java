package guesse.game.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class GusseGameService {

	@Value("${original.word}")
	private String originalWord;

	@Value("#{'${guess.description}'.split(',')}")
	private List<String> guessDescriptionList;

	private final static String LETTER = "Letter";
	private final static String EMPTY = " ";
	private final static String COMMA = ",";

	public List<String> validateCharacters(String guessedWord) {

		if (guessedWord == null || guessedWord.length() != 5) {
			throw new RuntimeException("The guessed string length must be equal to 5");
		}
		String characterDescription = "";
		int correctGuesse = 0;
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < guessedWord.length(); i++) {
			if (originalWord.contains(String.valueOf(guessedWord.charAt(i)))) {
				if (guessedWord.charAt(i) == originalWord.charAt(i)) {
					characterDescription = guessDescriptionList.get(0);
					correctGuesse++;
				} else {
					characterDescription = guessDescriptionList.get(1);
				}
			} else {
				characterDescription = guessDescriptionList.get(2);
			}

			sb.append(LETTER).append(EMPTY).append(guessedWord.charAt(i)).append(EMPTY).append(characterDescription)
					.append(COMMA);
		}
		sb.append(correctGuesse);

		return Arrays.asList(sb.toString().split(COMMA));

	}

}
