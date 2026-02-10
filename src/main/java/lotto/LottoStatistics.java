package lotto;

import java.util.HashMap;
import java.util.Map;

public class LottoStatistics {
	private final Map<Rank, Integer> countByRank = new HashMap<>();

	public void add(Rank rank) {
		countByRank.put(rank, countByRank.getOrDefault(rank, 0) + 1);
	}

	public int countOf(Rank rank) {
		return countByRank.getOrDefault(rank, 0);
	}

	public long totalPrizeMoney() {
		long totalPrizeMoneyAmount = 0;
		for (Map.Entry<Rank, Integer> rankEntry : countByRank.entrySet()) {
			totalPrizeMoneyAmount += rankEntry.getKey().prizeMoney() * rankEntry.getValue();
		}
		return totalPrizeMoneyAmount;
	}

	public double profitRate() {
		final int totalTicketCount = totalTicketCount();
		if (totalTicketCount == 0)
			return 0.0;
		return (double) totalPrizeMoney() / (totalTicketCount * 1000);
	}

	private int totalTicketCount() {
		int totalCount = 0;
		for (Map.Entry<Rank, Integer> rankEntry : countByRank.entrySet()) {
			totalCount += rankEntry.getValue();
		}
		return totalCount;
	}
}
