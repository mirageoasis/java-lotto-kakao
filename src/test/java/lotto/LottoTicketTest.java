package lotto;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LottoTicketTest {

	// 로또 티켓에는 LottoNumber가 6개 들어있다.
	// 로또 티켓에는 전부 다른 숫자가 들어있다.

	@Test
	@DisplayName("로또 티켓 생성 확인")
	public void init_lotto_ticket(){
		Set<LottoNumber> inputNumbers = IntStream.rangeClosed(1, 6)
			.mapToObj(LottoNumber::of)
			.collect(Collectors.toSet());
		LottoTicket lottoTicket = new LottoTicket(inputNumbers);
		Set<LottoNumber> numbers = lottoTicket.getNumbers();
		Set <Integer> setNumbers = numbers.stream().map(LottoNumber::getValue).collect(Collectors.toSet());
		Assertions.assertThat(setNumbers.size()).isEqualTo(6);
	}

	@Test
	@DisplayName("로또 티켓에 포함된 번호면 true를 반환한다")
	void contains_true_when_number_exists() {
		Set<LottoNumber> inputNumbers = IntStream.rangeClosed(1, 6)
			.mapToObj(LottoNumber::of)
			.collect(Collectors.toSet());
		LottoTicket lottoTicket = new LottoTicket(inputNumbers);

		Assertions.assertThat(lottoTicket.contains(LottoNumber.of(3))).isTrue();
	}

	@Test
	@DisplayName("로또 티켓에 없는 번호면 false를 반환한다")
	void contains_false_when_number_not_exists() {
		Set<LottoNumber> inputNumbers = IntStream.rangeClosed(1, 6)
			.mapToObj(LottoNumber::of)
			.collect(Collectors.toSet());
		LottoTicket lottoTicket = new LottoTicket(inputNumbers);

		Assertions.assertThat(lottoTicket.contains(LottoNumber.of(7))).isFalse();
	}

}
