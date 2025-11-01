package lotto.constant;

public enum ErrorMessage {

    EMPTY_PURCHASE_AMOUNT("구입 금액을 입력해 주세요."),
    INVALID_PURCHASE_NUMBER("구입 금액은 숫자여야 합니다."),
    ZERO_PURCHASE_AMOUNT("구입 금액은 1,000원 이상이어야 합니다."),
    NEGATIVE_PURCHASE_AMOUNT("구입 금액은 음수가 될 수 없습니다."),
    NOT_DIVISIBLE_BY_UNIT("구입 금액은 1,000원 단위여야 합니다."),

    EMPTY_NUMBERS("로또 번호가 비어 있습니다."),
    INVALID_SIZE("로또 번호는 6개여야 합니다."),
    DUPLICATED_NUMBER("로또 번호에 중복된 숫자가 있습니다."),
    OUT_OF_RANGE("로또 번호는 1부터 45 사이의 숫자여야 합니다."),

    EMPTY_WINNING_NUMBERS("당첨 번호를 입력해 주세요."),
    INVALID_DELIMITER("당첨 번호는 쉼표(,)로 구분되어야 합니다."),
    INVALID_WINNING_NUMBER("당첨 번호는 숫자만 입력할 수 있습니다."),

    EMPTY_BONUS_NUMBER("보너스 번호를 입력해 주세요."),
    INVALID_BONUS_NUMBER("보너스 번호는 숫자여야 합니다."),
    BONUS_NUMBER_OUT_OF_RANGE("보너스 번호는 1부터 45 사이의 숫자여야 합니다."),
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
