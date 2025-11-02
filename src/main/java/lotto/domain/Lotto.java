package lotto.domain;

import static lotto.constant.ErrorMessage.DUPLICATED_NUMBER;
import static lotto.constant.ErrorMessage.EMPTY_NUMBERS;
import static lotto.constant.ErrorMessage.INVALID_SIZE;
import static lotto.constant.ErrorMessage.OUT_OF_RANGE;
import static lotto.constant.LottoConstants.LOTTO_SIZE;
import static lotto.constant.LottoConstants.MAX_NUMBER;
import static lotto.constant.LottoConstants.MIN_NUMBER;

import java.util.List;

public class Lotto {

    private final List<Integer> numbers;

    public Lotto(List<Integer> numbers) {
        validate(numbers);
        this.numbers = numbers.stream()
                .sorted()
                .toList();
    }

    private void validate(List<Integer> numbers) {
        validateNullOrEmpty(numbers);
        validateSize(numbers);
        validateDistinct(numbers);
        validateRange(numbers);
    }

    private void validateNullOrEmpty(List<Integer> numbers) {
        if (numbers == null || numbers.isEmpty()) {
            throw new IllegalArgumentException(EMPTY_NUMBERS.getMessage());
        }
    }

    private void validateSize(List<Integer> numbers) {
        if (numbers.size() != LOTTO_SIZE) {
            throw new IllegalArgumentException(INVALID_SIZE.getMessage());
        }
    }

    private void validateDistinct(List<Integer> numbers) {
        long distinctCount = numbers.stream()
                .distinct()
                .count();

        if (distinctCount != LOTTO_SIZE) {
            throw new IllegalArgumentException(DUPLICATED_NUMBER.getMessage());
        }
    }

    private void validateRange(List<Integer> numbers) {
        boolean isOutOfRange = numbers.stream()
                .anyMatch(num -> num < MIN_NUMBER || num > MAX_NUMBER);

        if (isOutOfRange) {
            throw new IllegalArgumentException(OUT_OF_RANGE.getMessage());
        }
    }

    public List<Integer> numbers() {
        return List.copyOf(numbers);
    }
}
