package lotto.view;

import java.util.List;
import lotto.domain.Lotto;

public class OutputView {

    public void printPurchaseAmountRequest() {
        System.out.println("구입금액을 입력해 주세요.");
    }

    public void printPurchaseCount(int count) {
        printBlankLine();
        System.out.printf("%d개를 구매했습니다.%n", count);
    }

    public void printLottoNumbers(List<Lotto> lottoNumbers) {
        for (Lotto lottoNumber : lottoNumbers) {
            System.out.println(lottoNumber.numbers());
        }
    }

    public void printWinningNumberRequest() {
        System.out.println("당첨 번호를 입력해 주세요.");
    }

    public void printBonusNumberRequest() {
        System.out.println("보너스 번호를 입력해 주세요.");
    }

    private void printBlankLine() {
        System.out.println();
    }
}
