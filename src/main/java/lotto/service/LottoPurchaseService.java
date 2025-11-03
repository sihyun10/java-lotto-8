package lotto.service;

import java.util.ArrayList;
import java.util.List;
import lotto.domain.Lotto;
import lotto.domain.PurchaseAmount;
import lotto.util.LottoNumberGenerator;

public class LottoPurchaseService {

    private final LottoNumberGenerator numberGenerator;

    public LottoPurchaseService(LottoNumberGenerator numberGenerator) {
        this.numberGenerator = numberGenerator;
    }

    public List<Lotto> purchase(String inputAmount) {
        PurchaseAmount purchaseAmount = new PurchaseAmount(inputAmount);
        int lottoCount = purchaseAmount.calculateLottoCount();
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
