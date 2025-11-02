package lotto.service;

import java.util.List;
import lotto.domain.BonusNumber;
import lotto.domain.Lotto;
import lotto.domain.LottoGameResult;
import lotto.domain.WinningNumbers;
import lotto.view.InputView;
import lotto.view.OutputView;

public class LottoGameService {

    private final LottoPurchaseService purchaseService;
    private final LottoResultService resultService;

    public LottoGameService(LottoPurchaseService purchaseService, LottoResultService resultService) {
        this.purchaseService = purchaseService;
        this.resultService = resultService;
    }

    public LottoGameResult playGame(InputView inputView, OutputView outputView) {
        List<Lotto> purchased = readAndPurchase(inputView, outputView);
        WinningNumbers winningNumbers = readWinningNumbers(inputView, outputView);
        BonusNumber bonusNumber = readBonusNumber(inputView, outputView, winningNumbers);

        return resultService.generateResult(purchased, winningNumbers, bonusNumber);
    }

    private List<Lotto> readAndPurchase(InputView inputView, OutputView outputView) {
        while (true) {
            try {
                outputView.printPurchaseAmountRequest();
                String amount = inputView.readPurchaseAmount();

                List<Lotto> purchased = purchaseService.purchase(amount);
                outputView.printPurchaseCount(purchased.size());
                outputView.printLottoNumbers(purchased);
                return purchased;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private WinningNumbers readWinningNumbers(InputView inputView, OutputView outputView) {
        while (true) {
            try {
                outputView.printWinningNumberRequest();
                return new WinningNumbers(inputView.readWinningNumbers());
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private BonusNumber readBonusNumber(InputView inputView, OutputView outputView, WinningNumbers winningNumbers) {
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
