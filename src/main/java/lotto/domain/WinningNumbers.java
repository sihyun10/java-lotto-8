package lotto.domain;

import static lotto.constant.ErrorMessage.EMPTY_WINNING_NUMBERS;
import static lotto.constant.ErrorMessage.INVALID_DELIMITER;
import static lotto.constant.ErrorMessage.INVALID_WINNING_NUMBER;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class WinningNumbers {

    private static final String COMMA_DELIMITER = ",";
    private final Lotto lotto;

    public WinningNumbers(String input) {
        validateInput(input);
        List<Integer> numbers = parseNumbers(input);
        this.lotto = new Lotto(numbers);
    }

    private void validateInput(String input) {
        validateNotBlank(input);
        validateDelimiter(input);
        validateNumericValues(input);
    }

    private void validateNotBlank(String input) {
        if (input == null || input.isBlank()) {
            throw new IllegalArgumentException(EMPTY_WINNING_NUMBERS.getMessage());
        }
    }

    private void validateDelimiter(String input) {
        if (!input.contains(COMMA_DELIMITER)) {
            throw new IllegalArgumentException(INVALID_DELIMITER.getMessage());
        }
    }

    private void validateNumericValues(String input) {
        try {
            Arrays.stream(input.split(COMMA_DELIMITER))
                    .map(String::trim)
                    .forEach(Integer::parseInt);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(INVALID_WINNING_NUMBER.getMessage());
        }
    }

    private List<Integer> parseNumbers(String input) {
        return Arrays.stream(input.split(COMMA_DELIMITER))
                .map(String::trim)
                .map(Integer::parseInt)
                .collect(Collectors.toList());
    }

    public int countMatch(Lotto other) {
        return (int) other.numbers().stream()
                .filter(lotto.numbers()::contains)
                .count();
    }

    public boolean contains(int number) {
        return lotto.numbers().contains(number);
    }
}
