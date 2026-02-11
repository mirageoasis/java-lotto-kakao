package lotto;

public class LottoAnswer {

	private final WinningNumbers winningNumbers;
	private final LottoNumber bonus;

	public LottoAnswer(WinningNumbers winningNumbers, LottoNumber bonus) {
		validateBonusNumber(winningNumbers, bonus);
		this.winningNumbers = winningNumbers;
		this.bonus = bonus;
	}

	public Rank judge(LottoTicket lottoTicket) {
		int matchCount = countMatch(lottoTicket);
		boolean bonusMatch = containsBonus(lottoTicket);
		return Rank.fromMatchResult(matchCount, bonusMatch);
	}

	private int countMatch(LottoTicket lottoTicket) {
		return winningNumbers.countMatch(lottoTicket);
	}

	private boolean containsBonus(LottoTicket lottoTicket) {
		return lottoTicket.getNumbers().contains(bonus);
	}

	private void validateBonusNumber(WinningNumbers winningNumbers, LottoNumber bonus) {
		if (winningNumbers.contains(bonus))
			throw new IllegalArgumentException("보너스 번호는 당첨 번호와 중복될 수 없습니다.");
	}
}
