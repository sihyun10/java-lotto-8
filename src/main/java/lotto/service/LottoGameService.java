package lotto.service;

import java.util.List;
import lotto.domain.BonusNumber;
import lotto.domain.Lotto;
import lotto.domain.LottoGameResult;
import lotto.domain.WinningNumbers;

public class LottoGameService {

    private final LottoPurchaseService purchaseService;
    private final LottoResultService resultService;

    public LottoGameService(LottoPurchaseService purchaseService, LottoResultService resultService) {
        this.purchaseService = purchaseService;
        this.resultService = resultService;
    }

    public List<Lotto> purchaseLotto(String amount) {
        return purchaseService.purchase(amount);
    }

    public LottoGameResult generateResult(List<Lotto> purchased,
                                          WinningNumbers winningNumbers,
                                          BonusNumber bonusNumber) {
        return resultService.generateResult(purchased, winningNumbers, bonusNumber);
    }
}
