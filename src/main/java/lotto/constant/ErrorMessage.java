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
    OUT_OF_RANGE("로또 번호는 1부터 45 사이의 숫자여야 합니다.");

    private static final String PREFIX = "[ERROR] ";
    private final String message;

    ErrorMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return PREFIX + message;
    }
}
