package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LottoResultCalculatorTest {

    private final LottoResultCalculator calculator = new LottoResultCalculator();

    @Test
    @DisplayName("구매한 로또와 당첨,보너스 번호를 비교하여 등수별 당첨 개수를 계산한다.")
    void 당첨_개수_계산() {
        // given
        Lotto winning = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        int bonusNumber = 7;

        List<Lotto> purchased = List.of(
                new Lotto(List.of(8, 21, 23, 41, 42, 43)),
                new Lotto(List.of(3, 5, 11, 16, 32, 38)),
                new Lotto(List.of(7, 11, 16, 35, 36, 44)),
                new Lotto(List.of(1, 8, 11, 31, 41, 42)),
                new Lotto(List.of(13, 14, 16, 38, 42, 45)),
                new Lotto(List.of(7, 11, 30, 40, 42, 43)),
                new Lotto(List.of(2, 13, 22, 32, 38, 45)),
                new Lotto(List.of(1, 3, 5, 14, 22, 45))
        );

        // when
        Map<Rank, Long> result = calculator.calculateResult(purchased, winning, bonusNumber);

        // then
        assertThat(result.get(Rank.FIRST)).isEqualTo(0);
        assertThat(result.get(Rank.SECOND)).isEqualTo(0);
        assertThat(result.get(Rank.THIRD)).isEqualTo(0);
        assertThat(result.get(Rank.FOURTH)).isEqualTo(0);
        assertThat(result.get(Rank.FIFTH)).isEqualTo(1);
    }

    @Test
    @DisplayName("총 당첨금과 구입 금액을 통해 수익률을 계산한다.")
    void 수익률_계산() {
        // given
        Map<Rank, Long> result = Map.of(
                Rank.FIRST, 0L,
                Rank.SECOND, 0L,
                Rank.THIRD, 0L,
                Rank.FOURTH, 0L,
                Rank.FIFTH, 1L
        );
        long purchaseAmount = 8_000;

        // when
        double profitRate = calculator.calculateProfitRate(result, purchaseAmount);

        // then
        assertThat(profitRate).isEqualTo(62.5);
    }
}
