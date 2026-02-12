package lotto;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LottoStatisticsTest {

	@Test
	@DisplayName("로또 통계 생성")
	public void lotto_statistic_create() {
		LottoStatistics lottoStatistics = new LottoStatistics();

		List<Rank> ranks = List.of(
			Rank.OTHER, Rank.OTHER, Rank.OTHER,
			Rank.OTHER, Rank.OTHER, Rank.OTHER,
			Rank.OTHER, Rank.OTHER, Rank.OTHER,
			Rank.FIFTH
		);
		final long totalPrizeMoney = ranks.stream().mapToLong(Rank::prizeMoney).sum();
		final int totalMoneySpent = ranks.size() * Const.TICKET_PRICE;
		final double profitRate = (double)totalPrizeMoney / totalMoneySpent;

		// 통계생성
		for (Rank rank : ranks) {
			lottoStatistics.add(rank);
		}

		assertThat(lottoStatistics.profitRate()).isEqualTo(profitRate);
		assertThat(lottoStatistics.totalPrizeMoney()).isEqualTo(totalPrizeMoney);
	}

	@Test
	@DisplayName("뽑은 로또가 없을 때 엣지케이스")
	public void divide_by_zero() {
		LottoStatistics lottoStatistics = new LottoStatistics();

		List<Rank> ranks = List.of();

		for (Rank rank : ranks) {
			lottoStatistics.add(rank);
		}

		assertThat(lottoStatistics.profitRate()).isEqualTo(0);
		assertThat(lottoStatistics.totalPrizeMoney()).isEqualTo(0);
	}
}
