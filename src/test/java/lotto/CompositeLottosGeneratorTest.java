package lotto;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CompositeLottosGeneratorTest {

	@Test
	@DisplayName("컴포지트 생성기는 하위 생성기 결과를 모두 합친다")
	void generate() {
		LottoTicket manual = new LottoTicket(Set.of(
			LottoNumber.of(1), LottoNumber.of(2), LottoNumber.of(3),
			LottoNumber.of(4), LottoNumber.of(5), LottoNumber.of(6)
		));
		LottoTickets manualTickets = new LottoTickets(List.of(manual));

		LottosGenerator composite = new CompositeLottosGenerator(List.of(
			new ManualLottosGenerator(manualTickets),
			new AutoLottosGenerator(2)
		));

		LottoTickets generated = composite.generate();

		assertThat(generated.size()).isEqualTo(3);
	}
}
