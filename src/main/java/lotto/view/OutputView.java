package lotto.view;

import java.util.List;
import java.util.Map;
import lotto.domain.Lotto;
import lotto.domain.Rank;

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
        printBlankLine();
        System.out.println("당첨 번호를 입력해 주세요.");
    }

    public void printBonusNumberRequest() {
        printBlankLine();
        System.out.println("보너스 번호를 입력해 주세요.");
    }

    public void printLottoResult(Map<Rank, Long> result, double profitRate) {
        printBlankLine();
        System.out.println("당첨 통계");
        System.out.println("---");

        for (Rank rank : Rank.printableRanks()) {
            Long count = result.get(rank);
            System.out.printf("%s - %d개%n", rank.getDisplayText(), count);
        }

        System.out.printf("총 수익률은 %.1f%%입니다.%n", profitRate);
    }

    private void printBlankLine() {
        System.out.println();
    }
}
