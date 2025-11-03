package lotto.domain;

import static lotto.constant.ErrorMessage.EMPTY_PURCHASE_AMOUNT;
import static lotto.constant.ErrorMessage.INVALID_PURCHASE_NUMBER;
import static lotto.constant.ErrorMessage.NEGATIVE_PURCHASE_AMOUNT;
import static lotto.constant.ErrorMessage.NOT_DIVISIBLE_BY_UNIT;
import static lotto.constant.ErrorMessage.ZERO_PURCHASE_AMOUNT;
import static lotto.constant.LottoConstants.LOTTO_PRICE;

public class PurchaseAmount {

    private final int amount;

    public PurchaseAmount(String inputAmount) {
        validateEmpty(inputAmount);

        int parsedAmount = parseToInt(inputAmount);
        validateAmountRules(parsedAmount);
        this.amount = parsedAmount;
    }

    public int calculateLottoCount() {
        return this.amount / LOTTO_PRICE;
    }

    private void validateEmpty(String input) {
        if (input == null || input.isBlank()) {
            throw new IllegalArgumentException(EMPTY_PURCHASE_AMOUNT.getMessage());
        }
    }

    private int parseToInt(String input) {
        try {
            return Integer.parseInt(input.trim());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(INVALID_PURCHASE_NUMBER.getMessage());
        }
    }

    private void validateAmountRules(int amount) {
        validateZero(amount);
        validateNegative(amount);
        validateDivisible(amount);
    }

    private void validateZero(int amount) {
        if (amount == 0) {
            throw new IllegalArgumentException(ZERO_PURCHASE_AMOUNT.getMessage());
        }
    }

    private void validateNegative(int amount) {
        if (amount < 0) {
            throw new IllegalArgumentException(NEGATIVE_PURCHASE_AMOUNT.getMessage());
        }
    }

    private void validateDivisible(int amount) {
        if (amount % LOTTO_PRICE != 0) {
            throw new IllegalArgumentException(NOT_DIVISIBLE_BY_UNIT.getMessage());
        }
    }
}
