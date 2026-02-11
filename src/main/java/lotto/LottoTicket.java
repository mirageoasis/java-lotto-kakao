package lotto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class LottoTicket {

	private final Set<LottoNumber> numbers;

	public LottoTicket() {
		this(generateUniqueSixNumbers());
	}

	public LottoTicket(Set<LottoNumber> numbers) {
		validateNumbers(numbers);
		this.numbers = new HashSet<>(numbers);
	}

	public Set<LottoNumber> getNumbers() {
		return Collections.unmodifiableSet(numbers);
	}

	private void validateNumbers(Set<LottoNumber> numbers) {
		if (numbers.size() != 6)
			throw new IllegalArgumentException("로또 번호는 6개여야 합니다.");
	}

	private static Set<LottoNumber> generateUniqueSixNumbers() {
		List<Integer> numbers = new ArrayList<>();
		for (int i = 1; i <= 45; i++) {
			numbers.add(i);
		}
		Collections.shuffle(numbers);
		Set<LottoNumber> lottoNumbers = new HashSet<>();
		for (Integer number : numbers.subList(0, 6)) {
			lottoNumbers.add(LottoNumber.of(number));
		}
		return lottoNumbers;
	}
}
