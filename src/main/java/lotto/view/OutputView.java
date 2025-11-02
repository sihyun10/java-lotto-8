package lotto.view;

import static lotto.constant.OutputMessage.BONUS_NUMBER_REQUEST;
import static lotto.constant.OutputMessage.PROFIT_RATE;
import static lotto.constant.OutputMessage.PURCHASE_AMOUNT_REQUEST;
import static lotto.constant.OutputMessage.PURCHASE_COUNT;
import static lotto.constant.OutputMessage.RESULT_DIVIDER;
import static lotto.constant.OutputMessage.RESULT_HEADER;
import static lotto.constant.OutputMessage.WINNING_NUMBER_REQUEST;

import java.util.List;
import java.util.Map;
import lotto.domain.Lotto;
import lotto.domain.Rank;

public class OutputView {

    public void printPurchaseAmountRequest() {
        System.out.println(PURCHASE_AMOUNT_REQUEST.getMessage());
    }

    public void printPurchaseCount(int count) {
        printBlankLine();
        System.out.printf(PURCHASE_COUNT.getMessage(), count);
    }

    public void printLottoNumbers(List<Lotto> lottoNumbers) {
        for (Lotto lottoNumber : lottoNumbers) {
            System.out.println(lottoNumber.numbers());
        }
    }

    public void printWinningNumberRequest() {
        printBlankLine();
        System.out.println(WINNING_NUMBER_REQUEST.getMessage());
    }

    public void printBonusNumberRequest() {
        printBlankLine();
        System.out.println(BONUS_NUMBER_REQUEST.getMessage());
    }

    public void printLottoResult(Map<Rank, Long> result, double profitRate) {
        printBlankLine();
        System.out.println(RESULT_HEADER.getMessage());
        System.out.println(RESULT_DIVIDER.getMessage());

        for (Rank rank : Rank.printableRanks()) {
            Long count = result.get(rank);
            System.out.printf("%s - %d개%n", rank.getDisplayText(), count);
        }

        System.out.printf(PROFIT_RATE.getMessage(), profitRate);
    }

    public void printErrorMessage(String message) {
        System.out.println(message);
    }

    private void printBlankLine() {
        System.out.println();
    }
}
