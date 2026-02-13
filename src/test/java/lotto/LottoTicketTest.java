package lotto;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LottoTicketTest {

	// 로또 티켓에는 LottoNumber가 6개 들어있다.
	// 로또 티켓에는 전부 다른 숫자가 들어있다.

	@Test
	@DisplayName("로또 티켓 생성 확인")
	public void init_lotto_ticket() {
		Set<LottoNumber> inputNumbers = IntStream.rangeClosed(1, 6)
			.mapToObj(LottoNumber::of)
			.collect(Collectors.toSet());
		LottoTicket lottoTicket = new LottoTicket(inputNumbers);

		Assertions.assertThat(lottoTicket.sortedNumbers())
			.hasSize(6)
			.containsExactly(1, 2, 3, 4, 5, 6);
	}

	@Test
	@DisplayName("로또 티켓에 포함된 번호면 true를 반환한다")
	void contains_true_when_number_exists() {
		List<LottoNumber> inputNumbers = Stream.of(1, 2, 3, 4, 7, 31)
			.map(LottoNumber::of)
			.collect(Collectors.toList());
		LottoTicket lottoTicket = new LottoTicket(inputNumbers);

		Assertions.assertThat(lottoTicket.contains(LottoNumber.of(3))).isTrue();
	}

	@Test
	@DisplayName("로또 티켓에 없는 번호면 false를 반환한다")
	void contains_false_when_number_not_exists() {
		List<LottoNumber> inputNumbers = Stream.of(1, 2, 43, 4, 8, 31)
			.map(LottoNumber::of)
			.collect(Collectors.toList());
		LottoTicket lottoTicket = new LottoTicket(inputNumbers);

		Assertions.assertThat(lottoTicket.contains(LottoNumber.of(7))).isFalse();
	}

	@Test
	@DisplayName("로또 티켓 번호를 오름차순으로 반환한다")
	void sorted_numbers() {
		Set<LottoNumber> inputNumbers = Set.of(
			LottoNumber.of(45),
			LottoNumber.of(1),
			LottoNumber.of(23),
			LottoNumber.of(7),
			LottoNumber.of(3),
			LottoNumber.of(14)
		);
		LottoTicket lottoTicket = new LottoTicket(inputNumbers);

		Assertions.assertThat(lottoTicket.sortedNumbers())
			.isEqualTo(List.of(1, 3, 7, 14, 23, 45));
	}

}
