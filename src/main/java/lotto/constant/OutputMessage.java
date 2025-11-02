package lotto.constant;

public enum OutputMessage {

    PURCHASE_AMOUNT_REQUEST("구입금액을 입력해 주세요."),
    PURCHASE_COUNT("%d개를 구매했습니다.%n"),
    WINNING_NUMBER_REQUEST("당첨 번호를 입력해 주세요."),
    BONUS_NUMBER_REQUEST("보너스 번호를 입력해 주세요."),
    RESULT_HEADER("당첨 통계"),
    RESULT_DIVIDER("---"),
    PROFIT_RATE("총 수익률은 %.1f%%입니다.");

    private final String message;

    OutputMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
