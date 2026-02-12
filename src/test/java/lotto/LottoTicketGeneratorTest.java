package lotto;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LottoTicketGeneratorTest {
	@Test
	@DisplayName("양의 정수 n개의 티켓을 생성해서 LottoTickets로 반환한다")
	public void generate() {
		final int TICKET_NUMBER = 17;
		LottoTickets lottoTickets = LottoTicketGenerator.generate(TICKET_NUMBER);
		assertThat(lottoTickets.size()).isEqualTo(TICKET_NUMBER);
	}

	@Test
	@DisplayName("양이 아닌 정수 n개의 티켓을 생성하려고 하면 에러를 발생")
	public void generate_not_positive() {
		final int TICKET_NUMBER = 0;
		assertThatThrownBy(() -> LottoTicketGenerator.generate(TICKET_NUMBER))
			.isInstanceOf(IllegalArgumentException.class);
	}
}
