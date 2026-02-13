package lotto;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class LottoTicket {
	private final Set<LottoNumber> numbers;

	public LottoTicket(List<LottoNumber> numbers) {
		this(new HashSet<>(numbers));
	}

	public LottoTicket(Set<LottoNumber> numbers) {
		validateNumbers(numbers);
		this.numbers = new HashSet<>(numbers);
	}

	public boolean contains(LottoNumber number) {
		return numbers.contains(number);
	}

	public List<Integer> sortedNumbers() {
		return numbers.stream().map(LottoNumber::getValue)
			.sorted(Comparator.naturalOrder())
			.collect(Collectors.toList());
	}

	private void validateNumbers(Set<LottoNumber> numbers) {
		if (numbers.size() != Const.LOTTO_NUMBER_COUNT)
			throw new IllegalArgumentException("로또 번호는 " + Const.LOTTO_NUMBER_COUNT + "개여야 합니다.");
	}
}
