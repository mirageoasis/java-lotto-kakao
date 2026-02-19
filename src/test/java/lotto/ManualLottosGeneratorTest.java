package lotto;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ManualLottosGeneratorTest {

	@Test
	@DisplayName("수동 생성기는 전달받은 로또를 그대로 반환한다")
	void generate() {
		LottoTicket first = new LottoTicket(Set.of(
			LottoNumber.of(1), LottoNumber.of(2), LottoNumber.of(3),
			LottoNumber.of(4), LottoNumber.of(5), LottoNumber.of(6)
		));
		LottoTicket second = new LottoTicket(Set.of(
			LottoNumber.of(7), LottoNumber.of(8), LottoNumber.of(9),
			LottoNumber.of(10), LottoNumber.of(11), LottoNumber.of(12)
		));
		LottoTickets manualTickets = new LottoTickets(List.of(first, second));

		ManualLottosGenerator generator = new ManualLottosGenerator(manualTickets);

		LottoTickets generated = generator.generate();

		assertThat(generated.size()).isEqualTo(2);
	}
}
