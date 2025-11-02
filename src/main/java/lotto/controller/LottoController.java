package lotto.controller;

import java.util.List;
import lotto.domain.BonusNumber;
import lotto.domain.Lotto;
import lotto.domain.LottoGameResult;
import lotto.domain.WinningNumbers;
import lotto.service.LottoGameService;
import lotto.view.InputView;
import lotto.view.OutputView;

public class LottoController {

    private final InputView inputView;
    private final OutputView outputView;
    private final LottoGameService lottoGameService;

    public LottoController(InputView inputView, OutputView outputView, LottoGameService lottoGameService) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.lottoGameService = lottoGameService;
    }

    public void start() {
        List<Lotto> purchased = requestPurchase();
        WinningNumbers winningNumbers = requestWinningNumbers();
        BonusNumber bonusNumber = requestBonusNumber(winningNumbers);

        LottoGameResult result = lottoGameService.generateResult(purchased, winningNumbers, bonusNumber);
        outputView.printLottoResult(result.rankCounts(), result.profitRate());
    }

    private List<Lotto> requestPurchase() {
        while (true) {
            try {
                outputView.printPurchaseAmountRequest();
                String amount = inputView.readPurchaseAmount();

                List<Lotto> purchased = lottoGameService.purchaseLotto(amount);
                outputView.printPurchaseCount(purchased.size());
                outputView.printLottoNumbers(purchased);
                return purchased;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private WinningNumbers requestWinningNumbers() {
        while (true) {
            try {
                outputView.printWinningNumberRequest();
                return new WinningNumbers(inputView.readWinningNumbers());
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private BonusNumber requestBonusNumber(WinningNumbers winningNumbers) {
        while (true) {
            try {
                outputView.printBonusNumberRequest();
                return new BonusNumber(inputView.readBonusNumber(), winningNumbers);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
