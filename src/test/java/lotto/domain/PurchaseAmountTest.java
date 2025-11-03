package lotto.domain;

import static lotto.constant.ErrorMessage.EMPTY_PURCHASE_AMOUNT;
import static lotto.constant.ErrorMessage.INVALID_PURCHASE_NUMBER;
import static lotto.constant.ErrorMessage.NEGATIVE_PURCHASE_AMOUNT;
import static lotto.constant.ErrorMessage.NOT_DIVISIBLE_BY_UNIT;
import static lotto.constant.ErrorMessage.ZERO_PURCHASE_AMOUNT;
import static lotto.constant.LottoConstants.LOTTO_PRICE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import lotto.constant.ErrorMessage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class PurchaseAmountTest {

    @Nested
    @DisplayName("구입 금액 계산 정상 동작 테스트")
    class NormalCases {

        @Test
        @DisplayName("1000원 단위로 나누어 떨어지는 금액은 로또 장수로 계산된다.")
        void 정산_금액_입력() {
            PurchaseAmount purchaseAmount = new PurchaseAmount("8000");
            int count = purchaseAmount.calculateLottoCount();
            assertThat(count).isEqualTo(8);
        }

        @ParameterizedTest
        @ValueSource(strings = {"1000", "2000", "45000"})
        @DisplayName("다양한 유효 금액 입력 시 정상적으로 로또 장수를 계산한다.")
        void 다양한_정상_입력값(String input) {
            PurchaseAmount purchaseAmount = new PurchaseAmount(input);
            int count = purchaseAmount.calculateLottoCount();
            assertThat(count).isEqualTo(Integer.parseInt(input) / LOTTO_PRICE);
        }

        @Test
        @DisplayName("공백을 포함하여 입력해도 정상적으로 처리된다.")
        void 공백_포함_입력() {
            PurchaseAmount purchaseAmount = new PurchaseAmount("   8000  ");
            int count = purchaseAmount.calculateLottoCount();
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
            assertPurchaseAmountThrows(input, EMPTY_PURCHASE_AMOUNT);
        }

        @ParameterizedTest
        @ValueSource(strings = {"abc", "천원", "만원", "1,000", "1000원", "1_000"})
        @DisplayName("숫자가 아닌 값을 입력하면 예외가 발생한다.")
        void 숫자가_아닌_입력값(String input) {
            assertPurchaseAmountThrows(input, INVALID_PURCHASE_NUMBER);
        }

        @Test
        @DisplayName("구입 금액을 0원을 입력하면 예외가 발생한다.")
        void 입력값_0원() {
            assertPurchaseAmountThrows("0", ZERO_PURCHASE_AMOUNT);
        }

        @ParameterizedTest
        @ValueSource(strings = {"-1000", "-5000"})
        @DisplayName("음수 값을 입력하면 예외가 발생한다.")
        void 음수_입력값(String input) {
            assertPurchaseAmountThrows(input, NEGATIVE_PURCHASE_AMOUNT);
        }

        @ParameterizedTest
        @ValueSource(strings = {"800", "1500", "999", "2500", "12345"})
        @DisplayName("1,000원 단위로 나누어떨어지지 않으면 예외가 발생한다.")
        void 천원단위가_아닌_입력값(String input) {
            assertPurchaseAmountThrows(input, NOT_DIVISIBLE_BY_UNIT);
        }

        private void assertPurchaseAmountThrows(String input, ErrorMessage expected) {
            assertThatThrownBy(() -> new PurchaseAmount(input))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage(expected.getMessage());
        }
    }
}
