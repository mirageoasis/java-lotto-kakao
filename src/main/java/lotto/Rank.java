package lotto;

public enum Rank {
	FIRST(2000000000L),
	SECOND(30000000L),
	THIRD(1500000L),
	FOURTH(50000L),
	FIFTH(5000L),
	OTHER(0L)
	;
	private final long prizeMoney;

	Rank(long prizeMoney) {
		this.prizeMoney = prizeMoney;
	}

	public long prizeMoney() {
		return prizeMoney;
	}

	public static Rank fromMatchResult(int matchCount, boolean bonusMatch) {
		if (matchCount == 6) return FIRST;
		if (matchCount == 5 && bonusMatch) return SECOND;
		if (matchCount == 5) return THIRD;
		if (matchCount == 4) return FOURTH;
		if (matchCount == 3) return FIFTH;
		return OTHER;
	}
}
