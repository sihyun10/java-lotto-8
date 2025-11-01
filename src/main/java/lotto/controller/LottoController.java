package lotto.controller;

import java.util.List;
import lotto.domain.BonusNumber;
import lotto.domain.Lotto;
import lotto.domain.WinningNumbers;
import lotto.service.LottoPurchaseService;
import lotto.view.InputView;
import lotto.view.OutputView;

public class LottoController {

    private final InputView inputView;
    private final OutputView outputView;
    private final LottoPurchaseService purchaseService;

    public LottoController(InputView inputView, OutputView outputView, LottoPurchaseService purchaseService) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.purchaseService = purchaseService;
    }

    public void start() {
        List<Lotto> purchaseLottoNumbers = purchaseLottoNumbers();
        WinningNumbers winningNumbers = readWinningNumbers();
        BonusNumber bonusNumber = readBonusNumber(winningNumbers);
    }

    private List<Lotto> purchaseLottoNumbers() {
        while (true) {
            try {
                outputView.printPurchaseAmountRequest();
                String inputAmount = inputView.readPurchaseAmount();

                List<Lotto> purchasedLottos = purchaseService.purchase(inputAmount);
                outputView.printPurchaseCount(purchasedLottos.size());
                outputView.printLottoNumbers(purchasedLottos);

                return purchasedLottos;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private WinningNumbers readWinningNumbers() {
        while (true) {
            try {
                outputView.printWinningNumberRequest();
                String input = inputView.readWinningNumbers();
                return new WinningNumbers(input);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private BonusNumber readBonusNumber(WinningNumbers winningNumbers) {
        while (true) {
            try {
                outputView.printBonusNumberRequest();
                String input = inputView.readBonusNumber();
                return new BonusNumber(input, winningNumbers);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
