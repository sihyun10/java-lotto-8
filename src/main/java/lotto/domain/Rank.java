package lotto.domain;

import java.util.List;

public enum Rank {

    FIRST(6, false, 2_000_000_000),
    SECOND(5, true, 30_000_000),
    THIRD(5, false, 1_500_000),
    FOURTH(4, false, 50_000),
    FIFTH(3, false, 5_000),
    NONE(0, false, 0);

    private final int matchCount;
    private final boolean matchBonus;
    private final long prize;

    Rank(int matchCount, boolean matchBonus, long prize) {
        this.matchCount = matchCount;
        this.matchBonus = matchBonus;
        this.prize = prize;
    }

    public long getPrize() {
        return prize;
    }

    public static Rank of(Lotto lotto, Lotto winning, int bonusNumber) {
        List<Integer> numbers = lotto.numbers();
        long match = numbers.stream()
                .filter(winning.numbers()::contains)
                .count();
        boolean bonus = numbers.contains(bonusNumber);

        if (match == 6) {
            return FIRST;
        }
        if (match == 5 && bonus) {
            return SECOND;
        }
        if (match == 5) {
            return THIRD;
        }
        if (match == 4) {
            return FOURTH;
        }
        if (match == 3) {
            return FIFTH;
        }
        return NONE;
    }
}
