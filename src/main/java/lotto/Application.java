package lotto;

import java.util.List;
import lotto.controller.LottoController;
import lotto.domain.Lotto;
import lotto.service.LottoPurchaseService;
import lotto.service.calculator.LottoPurchaseCalculator;
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

        LottoController controller = new LottoController(inputView, outputView, purchaseService);
        List<Lotto> purchasedLottos = controller.purchaseLottoNumbers();
    }
}
