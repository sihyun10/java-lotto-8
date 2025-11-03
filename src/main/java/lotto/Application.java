package lotto;

import lotto.controller.LottoController;
import lotto.service.LottoGameService;
import lotto.service.LottoPurchaseService;
import lotto.service.LottoResultService;
import lotto.service.calculator.LottoResultCalculator;
import lotto.util.LottoNumberGenerator;
import lotto.view.InputView;
import lotto.view.OutputView;

public class Application {
    public static void main(String[] args) {
        InputView inputView = new InputView();
        OutputView outputView = new OutputView();
        LottoNumberGenerator numberGenerator = new LottoNumberGenerator();
        LottoResultCalculator resultCalculator = new LottoResultCalculator();
        LottoPurchaseService purchaseService = new LottoPurchaseService(numberGenerator);
        LottoResultService resultService = new LottoResultService(resultCalculator);
        LottoGameService gameService = new LottoGameService(purchaseService, resultService);
        LottoController controller = new LottoController(inputView, outputView, gameService);

        controller.start();
    }
}
