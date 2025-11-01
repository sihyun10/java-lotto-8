package lotto.service;

import java.util.ArrayList;
import java.util.List;
import lotto.domain.Lotto;
import lotto.service.calculator.LottoPurchaseCalculator;
import lotto.util.LottoNumberGenerator;

public class LottoPurchaseService {

    private final LottoPurchaseCalculator purchaseCalculator;
    private final LottoNumberGenerator numberGenerator;

    public LottoPurchaseService(LottoPurchaseCalculator purchaseCalculator,
                                LottoNumberGenerator numberGenerator) {
        this.purchaseCalculator = purchaseCalculator;
        this.numberGenerator = numberGenerator;
    }

    public List<Lotto> purchase(String inputAmount) {
        int lottoCount = purchaseCalculator.calculateCount(inputAmount);
        return generateLotto(lottoCount);
    }

    private List<Lotto> generateLotto(int count) {
        List<Lotto> lottoNumbers = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            lottoNumbers.add(new Lotto(numberGenerator.generate()));
        }
        return lottoNumbers;
    }
}
