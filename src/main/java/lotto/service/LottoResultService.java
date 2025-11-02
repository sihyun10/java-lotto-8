package lotto.service;

import static lotto.constant.LottoConstants.LOTTO_PRICE;

import java.util.List;
import java.util.Map;
import lotto.domain.BonusNumber;
import lotto.domain.Lotto;
import lotto.domain.LottoGameResult;
import lotto.domain.Rank;
import lotto.domain.WinningNumbers;
import lotto.service.calculator.LottoResultCalculator;

public class LottoResultService {

    private final LottoResultCalculator calculator;

    public LottoResultService(LottoResultCalculator calculator) {
        this.calculator = calculator;
    }

    public LottoGameResult generateResult(List<Lotto> purchased,
                                          WinningNumbers winningNumbers,
                                          BonusNumber bonusNumber) {
        int purchaseAmount = purchased.size() * LOTTO_PRICE;
        Map<Rank, Long> rankCounts = calculator.calculateResult(purchased, winningNumbers, bonusNumber);
        double profitRate = calculator.calculateProfitRate(rankCounts, purchaseAmount);
        return new LottoGameResult(rankCounts, profitRate);
    }
}
