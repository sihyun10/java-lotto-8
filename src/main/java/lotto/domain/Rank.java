package lotto.domain;

import java.util.List;

public enum Rank {

    FIRST(6, false, 2_000_000_000, "6개 일치 (2,000,000,000원)"),
    SECOND(5, true, 30_000_000, "5개 일치, 보너스 볼 일치 (30,000,000원)"),
    THIRD(5, false, 1_500_000, "5개 일치 (1,500,000원)"),
    FOURTH(4, false, 50_000, "4개 일치 (50,000원)"),
    FIFTH(3, false, 5_000, "3개 일치 (5,000원)"),
    NONE(0, false, 0, "0개 일치 (0원)");

    private final int matchCount;
    private final boolean matchBonus;
    private final long prize;
    private final String displayText;

    Rank(int matchCount, boolean matchBonus, long prize, String displayText) {
        this.matchCount = matchCount;
        this.matchBonus = matchBonus;
        this.prize = prize;
        this.displayText = displayText;
    }

    public long getPrize() {
        return prize;
    }

    public String getDisplayText() {
        return displayText;
    }

    public static List<Rank> printableRanks() {
        return List.of(FIFTH, FOURTH, THIRD, SECOND, FIRST);
    }

    public static Rank of(Lotto lotto, WinningNumbers winningNumbers, BonusNumber bonusNumber) {
        int matchCount = winningNumbers.countMatch(lotto);
        boolean bonusMatch = isBonusMatch(lotto, bonusNumber);

        return getRank(matchCount, bonusMatch);
    }

    private static boolean isBonusMatch(Lotto lotto, BonusNumber bonusNumber) {
        return lotto.numbers().stream()
                .anyMatch(bonusNumber::isSameAs);
    }

    private static Rank getRank(int matchCount, boolean bonusMatch) {
        if (matchCount == 6) {
            return FIRST;
        }
        if (matchCount == 5 && bonusMatch) {
            return SECOND;
        }
        if (matchCount == 5) {
            return THIRD;
        }
        if (matchCount == 4) {
            return FOURTH;
        }
        if (matchCount == 3) {
            return FIFTH;
        }
        return NONE;
    }
}
