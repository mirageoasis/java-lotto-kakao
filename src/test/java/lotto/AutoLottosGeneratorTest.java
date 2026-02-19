package lotto;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class AutoLottosGeneratorTest {

	@Test
	@DisplayName("자동 생성기로 지정한 수량만큼 로또를 생성한다")
	void generate() {
		AutoLottosGenerator generator = new AutoLottosGenerator(5);

		LottoTickets lottoTickets = generator.generate();

		assertThat(lottoTickets.size()).isEqualTo(5);
	}

	@Test
	@DisplayName("자동 생성 수량이 음수면 예외가 발생한다")
	void generate_negative_count() {
		assertThatThrownBy(() -> new AutoLottosGenerator(-1))
			.isInstanceOf(IllegalArgumentException.class);
	}
}
