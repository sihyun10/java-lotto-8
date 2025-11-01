package lotto.controller;

import java.util.List;
import lotto.domain.Lotto;
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

    public List<Lotto> purchaseLottoNumbers() {
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
}
