package lotto;

import lotto.controller.LottoController;
import lotto.service.LottoPurchaseService;
import lotto.service.calculator.LottoPurchaseCalculator;
import lotto.service.calculator.LottoResultCalculator;
import lotto.util.LottoNumberGenerator;
import lotto.view.InputView;
import lotto.view.OutputView;

public class Application {
    public static void main(String[] args) {
        InputView inputView = new InputView();
        OutputView outputView = new OutputView();
        LottoPurchaseCalculator calculator = new LottoPurchaseCalculator();
        LottoNumberGenerator generator = new LottoNumberGenerator();
        LottoPurchaseService purchaseService = new LottoPurchaseService(calculator, generator);
        LottoResultCalculator resultCalculator = new LottoResultCalculator();

        LottoController controller = new LottoController(inputView, outputView, purchaseService, resultCalculator);
        controller.start();
    }
}
