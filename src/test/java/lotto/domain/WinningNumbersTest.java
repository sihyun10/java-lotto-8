package lotto.domain;

import static lotto.constant.ErrorMessage.EMPTY_WINNING_NUMBERS;
import static lotto.constant.ErrorMessage.INVALID_DELIMITER;
import static lotto.constant.ErrorMessage.INVALID_WINNING_NUMBER;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import lotto.constant.ErrorMessage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

class WinningNumbersTest {

    @Nested
    @DisplayName("당첨 번호 정상 입력 테스트")
    class ValidWinningNumbers {

        @Test
        @DisplayName("올바른 당첨 번호 입력은 Lotto 객체가 정상 생성된다.")
        void 당첨번호_정상_입력() {
            WinningNumbers winningNumbers = new WinningNumbers("1,2,3,4,5,6");

            assertThat(winningNumbers.countMatch(new Lotto(List.of(1, 3, 5, 14, 22, 45)))).isEqualTo(3);
            assertThat(winningNumbers.contains(7)).isFalse();
        }
    }

    @Nested
    @DisplayName("당첨 번호 입력 예외 상황 테스트")
    class InvalidWinningNumbers {

        @ParameterizedTest
        @NullAndEmptySource
        @DisplayName("입력이 비어있으면 예외가 발생한다.")
        void 입력_비어있으면_예외_발생(String input) {
            assertWinningNumbersThrows(input, EMPTY_WINNING_NUMBERS);
        }

        @ParameterizedTest
        @ValueSource(strings = {"123456", "1.2.3.4.5.6", "1|2|3|4|5|6"})
        @DisplayName("쉼표(,)로 구분되지 않은 입력이면 예외가 발생한다.")
        void 쉼표_구분되지않은_입력이면_예외_발생(String input) {
            assertWinningNumbersThrows(input, INVALID_DELIMITER);
        }

        @ParameterizedTest
        @ValueSource(strings = {"1,2,a,4,5,6", "1,x,로,또,번,호"})
        @DisplayName("숫자가 아닌 값이 포함되면 예외가 발생한다.")
        void 숫자_아닌_값_포함되면_예외_발생(String input) {
            assertWinningNumbersThrows(input, INVALID_WINNING_NUMBER);
        }
    }

    private void assertWinningNumbersThrows(String input, ErrorMessage expected) {
        assertThatThrownBy(() -> new WinningNumbers(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(expected.getMessage());
    }
}
