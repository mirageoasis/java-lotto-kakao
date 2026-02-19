package lotto;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.Assertions.assertThatCode;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class MoneyTest {
	@Test
	@DisplayName("구매 가능한 티켓 수량을 반환한다 (1,000의 배수)")
	public void getTicketCount_when_amount_is_multiple_of_1000() {
		int amount = 22000;
		int answerCnt = amount / Const.TICKET_PRICE;

		Money money = new Money(amount);
		Assertions.assertThat(money.toPurchaseCount()).isEqualTo(
			answerCnt
		);
	}

	@Test
	@DisplayName("구매 가능한 티켓 수량을 반환한다 (1,000의 배수가 아닌 금액)")
	public void getTicketCount_when_amount_has_remainder() {
		int amount = 22500;
		int answerCnt = amount / Const.TICKET_PRICE;

		Money money = new Money(amount);
		Assertions.assertThat(money.toPurchaseCount()).isEqualTo(
			answerCnt
		);
	}

	@Test
	@DisplayName("지불할 금액이 TICKET_PRICE원 미만인 경우")
	public void error_ticket_price() {
		int amount = Const.TICKET_PRICE - 1;

		assertThatThrownBy(() -> new Money(amount))
			.isInstanceOf(IllegalArgumentException.class);
	}

	@Test
	@DisplayName("수동 구매 수량이 음수면 예외가 발생한다")
	void validate_manual_count_negative() {
		Money money = new Money(5000);

		assertThatThrownBy(() -> money.validateManualCount(-1))
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessageContaining("수동 구매 수량은 0 이상");
	}

	@Test
	@DisplayName("수동 구매 수량이 전체 구매 수량을 초과하면 예외가 발생한다")
	void validate_manual_count_exceed_total() {
		Money money = new Money(5000);

		assertThatThrownBy(() -> money.validateManualCount(6))
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessageContaining("전체 구매 수량을 초과");
	}

	@Test
	@DisplayName("수동 구매 수량이 전체 구매 수량 이하면 통과한다")
	void validate_manual_count_success() {
		Money money = new Money(5000);

		assertThatCode(() -> money.validateManualCount(5))
			.doesNotThrowAnyException();
	}
}
