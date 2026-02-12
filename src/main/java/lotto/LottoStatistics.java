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
		return countByRank.entrySet().stream()
			.mapToLong(this::calculatePrizePerRank)
			.sum();
	}

	public double profitRate() {
		final int totalTicketCount = totalTicketCount();
		if (totalTicketCount == 0)
			return 0.0;
		return (double)totalPrizeMoney() / (totalTicketCount * Const.TICKET_PRICE);
	}

	private int totalTicketCount() {
		int totalCount = 0;
		for (Map.Entry<Rank, Integer> rankEntry : countByRank.entrySet()) {
			totalCount += rankEntry.getValue();
		}
		return totalCount;
	}

	private long calculatePrizePerRank(Map.Entry<Rank, Integer> entry) {
		return entry.getKey().prizeMoney() * entry.getValue();
	}
}
