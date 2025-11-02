package lotto.constant;

import static lotto.constant.LottoConstants.LOTTO_PRICE;
import static lotto.constant.LottoConstants.LOTTO_SIZE;
import static lotto.constant.LottoConstants.MAX_NUMBER;
import static lotto.constant.LottoConstants.MIN_NUMBER;

public enum ErrorMessage {

    EMPTY_PURCHASE_AMOUNT("구입 금액을 입력해 주세요."),
    INVALID_PURCHASE_NUMBER("구입 금액은 숫자여야 합니다."),
    ZERO_PURCHASE_AMOUNT(String.format("구입 금액은 %d원 이상이어야 합니다.", LOTTO_PRICE)),
    NEGATIVE_PURCHASE_AMOUNT("구입 금액은 음수가 될 수 없습니다."),
    NOT_DIVISIBLE_BY_UNIT(String.format("구입 금액은 %d원 단위여야 합니다.", LOTTO_PRICE)),

    EMPTY_NUMBERS("로또 번호가 비어 있습니다."),
    INVALID_SIZE(String.format("로또 번호는 %d개여야 합니다.", LOTTO_SIZE)),
    DUPLICATED_NUMBER("로또 번호에 중복된 숫자가 있습니다."),
    OUT_OF_RANGE(String.format("로또 번호는 %d부터 %d 사이의 숫자여야 합니다.", MIN_NUMBER, MAX_NUMBER)),

    EMPTY_WINNING_NUMBERS("당첨 번호를 입력해 주세요."),
    INVALID_DELIMITER("당첨 번호는 쉼표(,)로 구분되어야 합니다."),
    INVALID_WINNING_NUMBER("당첨 번호는 숫자만 입력할 수 있습니다."),

    EMPTY_BONUS_NUMBER("보너스 번호를 입력해 주세요."),
    INVALID_BONUS_NUMBER("보너스 번호는 숫자여야 합니다."),
    BONUS_NUMBER_OUT_OF_RANGE(String.format("보너스 번호는 %d부터 %d 사이의 숫자여야 합니다.", MIN_NUMBER, MAX_NUMBER)),
    DUPLICATED_BONUS_NUMBER("보너스 번호는 당첨 번호와 중복될 수 없습니다.");

    private static final String PREFIX = "[ERROR] ";
    private final String message;

    ErrorMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return PREFIX + message;
    }
}
