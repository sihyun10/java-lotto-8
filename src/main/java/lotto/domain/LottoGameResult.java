package lotto.domain;

import java.util.Map;

public record LottoGameResult(Map<Rank, Long> rankCounts, double profitRate) {
}
