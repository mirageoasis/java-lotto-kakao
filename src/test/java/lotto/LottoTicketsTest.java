package lotto;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LottoTicketsTest {

	@Test
	@DisplayName("empty는 비어있는 로또 목록을 생성한다")
	void empty() {
		LottoTickets empty = LottoTickets.empty();

		assertThat(empty.size()).isZero();
	}

	@Test
	@DisplayName("concat은 두 로또 목록을 합쳐 새 인스턴스를 반환한다")
	void concat() {
		LottoTicket first = new LottoTicket(Set.of(
			LottoNumber.of(1), LottoNumber.of(2), LottoNumber.of(3),
			LottoNumber.of(4), LottoNumber.of(5), LottoNumber.of(6)
		));
		LottoTicket second = new LottoTicket(Set.of(
			LottoNumber.of(7), LottoNumber.of(8), LottoNumber.of(9),
			LottoNumber.of(10), LottoNumber.of(11), LottoNumber.of(12)
		));

		LottoTickets left = new LottoTickets(List.of(first));
		LottoTickets right = new LottoTickets(List.of(second));

		LottoTickets merged = left.concat(right);

		assertThat(left.size()).isEqualTo(1);
		assertThat(right.size()).isEqualTo(1);
		assertThat(merged.size()).isEqualTo(2);
	}
}
