package lotto.domain;

import java.util.List;

public class Lotto {

    private static final int LOTTO_SIZE = 6;
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
    }

    private static void validateNullOrEmpty(List<Integer> numbers) {
        if (numbers == null || numbers.isEmpty()) {
            throw new IllegalArgumentException("[ERROR] 로또 번호가 비어 있습니다.");
        }
    }

    private static void validateSize(List<Integer> numbers) {
        if (numbers.size() != LOTTO_SIZE) {
            throw new IllegalArgumentException("[ERROR] 로또 번호는 6개여야 합니다.");
        }
    }

    private static void validateDistinct(List<Integer> numbers) {
        long distinctCount = numbers.stream().distinct().count();
        if (distinctCount != LOTTO_SIZE) {
            throw new IllegalArgumentException("[ERROR] 로또 번호에 중복된 숫자가 있습니다.");
        }
    }

    public List<Integer> numbers() {
        return List.copyOf(numbers);
    }
}
