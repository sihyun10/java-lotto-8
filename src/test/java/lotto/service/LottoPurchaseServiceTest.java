package lotto.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import lotto.domain.Lotto;
import lotto.util.LottoNumberGenerator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LottoPurchaseServiceTest {

    private LottoPurchaseService purchaseService;

    @BeforeEach
    void setUp() {
        FakeNumberGenerator fakeNumberGenerator = new FakeNumberGenerator();
        purchaseService = new LottoPurchaseService(fakeNumberGenerator);
    }

    @Test
    @DisplayName("입력 금액에 따라 올바른 개수의 로또가 생성된다.")
    void 로또_구입() {
        List<Lotto> result = purchaseService.purchase("3000");

        assertThat(result).hasSize(3);
        assertThat(result.getFirst().numbers()).containsExactly(17, 19, 29, 34, 39, 40);
    }

    static class FakeNumberGenerator extends LottoNumberGenerator {
        @Override
        public List<Integer> generate() {
            return List.of(17, 19, 29, 34, 39, 40);
        }
    }
}
