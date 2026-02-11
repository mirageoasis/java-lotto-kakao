package lotto;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class LottoTicket {
	private final Set<LottoNumber> numbers;

	public LottoTicket(Set<LottoNumber> numbers) {
		validateNumbers(numbers);
		this.numbers = new HashSet<>(numbers);
	}

	public Set<LottoNumber> getNumbers() {
		return Collections.unmodifiableSet(numbers);
	}

	public boolean contains(LottoNumber number){
		return numbers.contains(number);
	}

	private void validateNumbers(Set<LottoNumber> numbers) {
		if (numbers.size() != Const.LOTTO_NUMBER_COUNT)
			throw new IllegalArgumentException("로또 번호는 " + Const.LOTTO_NUMBER_COUNT + "개여야 합니다.");
	}
}
