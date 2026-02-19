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

	@Test
	@DisplayName("컴포지트 생성기는 중첩 컴포지트도 동일하게 처리한다")
	void generate_nested_composite() {
		LottoTicket manual = new LottoTicket(Set.of(
			LottoNumber.of(1), LottoNumber.of(2), LottoNumber.of(3),
			LottoNumber.of(4), LottoNumber.of(5), LottoNumber.of(6)
		));
		LottoTickets manualTickets = new LottoTickets(List.of(manual));

		LottosGenerator nested = new CompositeLottosGenerator(List.of(
			new AutoLottosGenerator(2),
			new ManualLottosGenerator(manualTickets)
		));

		LottosGenerator root = new CompositeLottosGenerator(List.of(
			new AutoLottosGenerator(1),
			nested
		));

		LottoTickets generated = root.generate();

		assertThat(generated.size()).isEqualTo(4);
	}

	@Test
	@DisplayName("하위 생성기가 없으면 비어있는 로또 목록을 반환한다")
	void generate_empty_children() {
		LottosGenerator composite = new CompositeLottosGenerator(List.of());

		LottoTickets generated = composite.generate();

		assertThat(generated.size()).isZero();
	}
}
