package lotto.service.calculator;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lotto.domain.BonusNumber;
import lotto.domain.Lotto;
import lotto.domain.Rank;
import lotto.domain.WinningNumbers;

public class LottoResultCalculator {

    private static final int PERCENTAGE_MULTIPLIER = 100;
    private static final int ROUNDING_SCALE = 10;

    public Map<Rank, Long> calculateResult(List<Lotto> purchased,
                                           WinningNumbers winningNumbers,
                                           BonusNumber bonusNumber) {
        Map<Rank, Long> rankCount = initializeRankCount();

        for (Lotto lotto : purchased) {
            Rank rank = Rank.of(lotto, winningNumbers, bonusNumber);
            rankCount.put(rank, rankCount.get(rank) + 1);
        }

        return rankCount;
    }

    public double calculateProfitRate(Map<Rank, Long> result, long purchaseAmount) {
        long totalPrize = result.entrySet().stream()
                .mapToLong(entry -> entry.getKey().getPrize() * entry.getValue())
                .sum();

        double rate = (double) totalPrize / purchaseAmount * PERCENTAGE_MULTIPLIER;
        return roundToScale(rate);
    }

    private Map<Rank, Long> initializeRankCount() {
        Map<Rank, Long> map = new HashMap<>();
        for (Rank rank : Rank.values()) {
            map.put(rank, 0L);
        }
        return map;
    }

    private static double roundToScale(double rate) {
        return Math.round(rate * ROUNDING_SCALE) / (double) ROUNDING_SCALE;
    }
}
