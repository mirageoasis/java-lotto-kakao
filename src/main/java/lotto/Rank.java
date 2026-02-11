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
}
