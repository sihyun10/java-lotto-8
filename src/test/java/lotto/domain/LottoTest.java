package lotto.domain;

import static lotto.constant.ErrorMessage.DUPLICATED_NUMBER;
import static lotto.constant.ErrorMessage.EMPTY_NUMBERS;
import static lotto.constant.ErrorMessage.INVALID_SIZE;
import static lotto.constant.ErrorMessage.OUT_OF_RANGE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class LottoTest {

    @DisplayName("로또 번호가 6개 미만이면 예외가 발생한다.")
    @Test
    void 로또_번호가_6개_미만이면_예외가_발생한다() {
        assertThatThrownBy(() -> new Lotto(List.of(1, 2, 3, 4, 5)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(INVALID_SIZE.getMessage());
    }

    @DisplayName("로또 번호가 6개 넘으면 예외가 발생한다.")
    @Test
    void 로또_번호의_개수가_6개가_넘어가면_예외가_발생한다() {
        assertThatThrownBy(() -> new Lotto(List.of(1, 2, 3, 4, 5, 6, 7)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(INVALID_SIZE.getMessage());
    }

    @DisplayName("로또 번호가 비어 있으면 예외가 발생한다.")
    @Test
    void 로또_번호가_비어있으면_예외가_발생한다() {
        assertThatThrownBy(() -> new Lotto(List.of()))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(EMPTY_NUMBERS.getMessage());
    }

    @DisplayName("로또 번호에 중복된 숫자가 있으면 예외가 발생한다.")
    @Test
    void 로또_번호에_중복된_숫자가_있으면_예외가_발생한다() {
        assertThatThrownBy(() -> new Lotto(List.of(1, 2, 3, 4, 5, 5)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(DUPLICATED_NUMBER.getMessage());
    }

    @ParameterizedTest
    @ValueSource(ints = {-1, 0, 46, 100})
    @DisplayName("로또 번호가 1~45 범위를 벗어나면 예외가 발생한다.")
    void 로또_번호가_1에서_45_범위를_벗어나면_예외가_발생한다(int invalidNumber) {
        List<Integer> numbers = List.of(1, 2, 3, 4, 5, invalidNumber);

        assertThatThrownBy(() -> new Lotto(numbers))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(OUT_OF_RANGE.getMessage());
    }

    @DisplayName("로또 번호가 유효하면 정렬된 상태로 저장된다.")
    @Test
    void 로또_번호가_유효하면_정렬된_상태로_저장된다() {
        Lotto lotto = new Lotto(List.of(8, 3, 1, 45, 12, 32));

        assertThat(lotto.numbers()).containsExactly(1, 3, 8, 12, 32, 45);
    }
}
