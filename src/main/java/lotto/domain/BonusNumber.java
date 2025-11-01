package lotto.domain;

import static lotto.constant.ErrorMessage.BONUS_NUMBER_OUT_OF_RANGE;
import static lotto.constant.ErrorMessage.DUPLICATED_BONUS_NUMBER;
import static lotto.constant.ErrorMessage.EMPTY_BONUS_NUMBER;
import static lotto.constant.ErrorMessage.INVALID_BONUS_NUMBER;
import static lotto.constant.LottoConstants.MAX_NUMBER;
import static lotto.constant.LottoConstants.MIN_NUMBER;

public class BonusNumber {

    private final int number;

    public BonusNumber(String input, WinningNumbers winningNumbers) {
        validateInput(input);
        this.number = parseNumber(input);
        validateRange();
        validateDuplicate(winningNumbers);
    }

    private void validateInput(String input) {
        validateNotBlank(input);
        validateNumeric(input);
    }

    private void validateNotBlank(String input) {
        if (input == null || input.isBlank()) {
            throw new IllegalArgumentException(EMPTY_BONUS_NUMBER.getMessage());
        }
    }

    private void validateNumeric(String input) {
        try {
            Integer.parseInt(input.trim());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(INVALID_BONUS_NUMBER.getMessage());
        }
    }

    private int parseNumber(String input) {
        return Integer.parseInt(input.trim());
    }

    private void validateRange() {
        if (number < MIN_NUMBER || number > MAX_NUMBER) {
            throw new IllegalArgumentException(BONUS_NUMBER_OUT_OF_RANGE.getMessage());
        }
    }

    private void validateDuplicate(WinningNumbers winningNumbers) {
        if (winningNumbers.contains(number)) {
            throw new IllegalArgumentException(DUPLICATED_BONUS_NUMBER.getMessage());
        }
    }

    public boolean isSameAs(int other) {
        return this.number == other;
    }
}
