package lotto.constant;

public enum ErrorMessage {

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
