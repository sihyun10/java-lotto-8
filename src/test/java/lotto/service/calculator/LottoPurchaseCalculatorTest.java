package lotto.service.calculator;

import static lotto.constant.ErrorMessage.EMPTY_PURCHASE_AMOUNT;
import static lotto.constant.ErrorMessage.INVALID_PURCHASE_NUMBER;
import static lotto.constant.ErrorMessage.NEGATIVE_PURCHASE_AMOUNT;
import static lotto.constant.ErrorMessage.NOT_DIVISIBLE_BY_UNIT;
import static lotto.constant.ErrorMessage.ZERO_PURCHASE_AMOUNT;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class LottoPurchaseCalculatorTest {

    private final LottoPurchaseCalculator lottoPurchaseCalculator = new LottoPurchaseCalculator();

    @Nested
    @DisplayName("구입 금액 계산 정상 동작 테스트")
    class NormalCases {

        @Test
        @DisplayName("1000원 단위로 나누어 떨어지는 금액은 로또 장수로 계산된다.")
        void 정상_금액_입력() {
            int count = lottoPurchaseCalculator.calculateCount("8000");
            assertThat(count).isEqualTo(8);
        }

        @ParameterizedTest
        @ValueSource(strings = {"1000", "2000", "45000"})
        @DisplayName("다양한 유효 금액 입력 시 정상적으로 로또 장수를 계산한다.")
        void 다양한_정상_입력값(String input) {
            int count = lottoPurchaseCalculator.calculateCount(input);
            assertThat(count).isEqualTo(Integer.parseInt(input) / 1000);
        }

        @Test
        @DisplayName("공백을 포함하여 입력해도 정상적으로 처리된다.")
        void 공백_포함_입력() {
            int count = lottoPurchaseCalculator.calculateCount("  8000 ");
            assertThat(count).isEqualTo(8);
        }
    }

    @Nested
    @DisplayName("구입 금액 계산 예외 상황 테스트")
    class ExceptionCases {

        @ParameterizedTest
        @ValueSource(strings = {"", "   ", "\n"})
        @DisplayName("입력값이 비어 있으면 예외가 발생한다.")
        void 비어있는_입력값(String input) {
            assertThatThrownBy(() -> lottoPurchaseCalculator.calculateCount(input))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage(EMPTY_PURCHASE_AMOUNT.getMessage());
        }

        @ParameterizedTest
        @ValueSource(strings = {"abc", "천원", "만원", "1,000", "1000원", "1_000"})
        @DisplayName("숫자가 아닌 값을 입력하면 예외가 발생한다.")
        void 숫자가_아닌_입력값(String input) {
            assertThatThrownBy(() -> lottoPurchaseCalculator.calculateCount(input))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage(INVALID_PURCHASE_NUMBER.getMessage());
        }

        @Test
        @DisplayName("구입 금액을 0원을 입력하면 예외가 발생한다.")
        void 입력값_0원() {
            assertThatThrownBy(() -> lottoPurchaseCalculator.calculateCount("0"))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage(ZERO_PURCHASE_AMOUNT.getMessage());
        }

        @ParameterizedTest
        @ValueSource(strings = {"-1000", "-5000"})
        @DisplayName("음수 값을 입력하면 예외가 발생한다.")
        void 음수_입력값(String input) {
            assertThatThrownBy(() -> lottoPurchaseCalculator.calculateCount(input))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage(NEGATIVE_PURCHASE_AMOUNT.getMessage());
        }

        @ParameterizedTest
        @ValueSource(strings = {"800", "1500", "999", "2500", "12345"})
        @DisplayName("1,000원 단위로 나누어떨어지지 않으면 예외가 발생한다.")
        void 천원단위가_아닌_입력값(String input) {
            assertThatThrownBy(() -> lottoPurchaseCalculator.calculateCount(input))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage(NOT_DIVISIBLE_BY_UNIT.getMessage());
        }
    }
}
