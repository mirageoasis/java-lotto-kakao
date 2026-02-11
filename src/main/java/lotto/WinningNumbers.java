package lotto;

import java.util.HashSet;
import java.util.Set;

public class WinningNumbers {
	private final Set<LottoNumber> numbers;

	public WinningNumbers(Set<LottoNumber> numbers) {
		validateSize(numbers);
		this.numbers = new HashSet<>(numbers);
	}

	public boolean contains(LottoNumber lottoNumber) {
		return numbers.contains(lottoNumber);
	}

	public int countMatch(LottoTicket lottoTicket) {
		return (int) numbers.stream()
			.filter(lottoTicket::contains)
			.count();
	}

	private void validateSize(Set<LottoNumber> numbers) {
		if (numbers.size() != 6) {
			throw new IllegalArgumentException("당첨 번호는 6개여야 합니다.");
		}
	}
}
