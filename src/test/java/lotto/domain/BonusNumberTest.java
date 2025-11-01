package lotto.domain;

import static lotto.constant.ErrorMessage.BONUS_NUMBER_OUT_OF_RANGE;
import static lotto.constant.ErrorMessage.DUPLICATED_BONUS_NUMBER;
import static lotto.constant.ErrorMessage.EMPTY_BONUS_NUMBER;
import static lotto.constant.ErrorMessage.INVALID_BONUS_NUMBER;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import lotto.constant.ErrorMessage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

class BonusNumberTest {

    private final WinningNumbers winningNumbers = new WinningNumbers("1, 2, 3, 4, 5, 6");

    @Nested
    @DisplayName("보너스번호 정상 입력 테스트")
    class ValidBonusNumber {

        @Test
        @DisplayName("정상적인 보너스 번호 입력 시 성공")
        void 보너스_번호_정상_입력() {
            BonusNumber bonusNumber = new BonusNumber("7", winningNumbers);
            assertThat(bonusNumber.isSameAs(7)).isTrue();
        }
    }

    @Nested
    @DisplayName("보너스번호 예외 상황 테스트")
    class InvalidBonusNumber {

        @ParameterizedTest
        @NullAndEmptySource
        @DisplayName("보너스 번호가 비어 있으면 예외가 발생한다.")
        void 입력_비어있는경우_예외_발생(String input) {
            assertBonusNumberThrows(input, EMPTY_BONUS_NUMBER);
        }

        @ParameterizedTest
        @ValueSource(strings = {"a", "?", "4.2", "오"})
        @DisplayName("숫자가 아닌 보너스 번호면 예외가 발생한다.")
        void 숫자가_아닌경우_예외_발생(String input) {
            assertBonusNumberThrows(input, INVALID_BONUS_NUMBER);
        }

        @ParameterizedTest
        @ValueSource(strings = {"0", "46", "200"})
        @DisplayName("1~45 범위를 벗어난 보너스 번호는 예외가 발생한다.")
        void 범위_벗어난_경우_예외_발생(String input) {
            assertBonusNumberThrows(input, BONUS_NUMBER_OUT_OF_RANGE);
        }

        @Test
        @DisplayName("당첨 번호와 중복되는 경우 예외가 발생한다.")
        void 당첨_번호와_중복된경우_예외_발생() {
            assertBonusNumberThrows("2", DUPLICATED_BONUS_NUMBER);
        }
    }

    private void assertBonusNumberThrows(String input, ErrorMessage expected) {
        assertThatThrownBy(() -> new BonusNumber(input, winningNumbers))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(expected.getMessage());
    }
}
