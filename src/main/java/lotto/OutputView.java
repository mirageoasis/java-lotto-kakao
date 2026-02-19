package lotto;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class OutputView {
	public void printPurchaseResult(int manualCount, int autoCount, LottoTickets lottoTickets) {
		System.out.printf("수동으로 %d장, 자동으로 %d개를 구매했습니다.%n", manualCount, autoCount);
		lottoTickets.forEach(lottoTicket -> System.out.println(lottoTicket.sortedNumbers()));
		System.out.println();
	}

	public void printStatistics(LottoStatistics lottoStatistics) {
		System.out.println();
		System.out.println("당첨 통계");
		System.out.println("---------");
		printRankResult("3개 일치 (5000원)", lottoStatistics.countOf(Rank.FIFTH));
		printRankResult("4개 일치 (50000원)", lottoStatistics.countOf(Rank.FOURTH));
		printRankResult("5개 일치 (1500000원)", lottoStatistics.countOf(Rank.THIRD));
		printRankResult("5개 일치, 보너스 볼 일치(30000000원)", lottoStatistics.countOf(Rank.SECOND));
		printRankResult("6개 일치 (2000000000원)", lottoStatistics.countOf(Rank.FIRST));
		System.out.printf("총 수익률은 %s입니다.%n", formatProfitRate(lottoStatistics.profitRate()));
	}

	public void printError(String message) {
		if (message == null || message.isBlank()) {
			System.out.println("[ERROR] 잘못된 입력입니다.");
			return;
		}
		System.out.printf("[ERROR] %s%n", message);
	}

	private void printRankResult(String label, int count) {
		System.out.printf("%s - %d개%n", label, count);
	}

	private String formatProfitRate(double profitRate) {
		return BigDecimal.valueOf(profitRate)
			.setScale(2, RoundingMode.DOWN)
			.toPlainString();
	}
}
