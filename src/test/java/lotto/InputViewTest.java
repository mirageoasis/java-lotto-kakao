package lotto;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class InputViewTest {

	// 사용자의 입력을 시뮬레이션하는 편의 메서드
	private InputView inputView(String input) {
		ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes(StandardCharsets.UTF_8));
		return new InputView(new Scanner(in));
	}

	@DisplayName("당첨 번호 6개를 정상적으로 입력받는다.")
	@Test
	void readWinningNumbersSuccess() {
		// given
		InputView inputView = inputView("1, 2, 3, 4, 5, 6");

		// when
		WinningNumbers winningNumbers = inputView.readWinningNumbers();

		// then
		assertThat(winningNumbers).isNotNull();
	}

	@DisplayName("당첨 번호가 6개가 아니면 예외가 발생한다.")
	@Test
	void readWinningNumbersSizeError() {
		// given
		InputView inputView = inputView("1, 2, 3, 4, 5"); // 5개만 입력

		// when & then
		assertThatThrownBy(() -> inputView.readWinningNumbers())
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessageContaining("당첨 번호는 6개를 입력해야 합니다.");
	}

	@DisplayName("당첨 번호에 중복이 있으면 예외가 발생한다.")
	@Test
	void readWinningNumbersDuplicateError() {
		// given
		InputView inputView = inputView("1, 2, 3, 4, 5, 5"); // '5' 중복

		// when & then
		assertThatThrownBy(() -> inputView.readWinningNumbers())
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessageContaining("당첨 번호는 중복될 수 없습니다.");
	}

	@DisplayName("당첨 번호에 숫자가 아닌 값이 들어오면 예외가 발생한다.")
	@Test
	void readWinningNumbersNotIntegerError() {
		// given
		InputView inputView = inputView("1, 2, 3, a, 5, 6"); // 'a' 포함

		// when & then
		assertThatThrownBy(() -> inputView.readWinningNumbers())
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessageContaining("숫자만 입력할 수 있습니다.");
	}

	@DisplayName("수동 구매 개수를 정상적으로 입력받는다.")
	@Test
	void readManualCountSuccess() {
		InputView inputView = inputView("3");

		int manualCount = inputView.readManualCount();

		assertThat(manualCount).isEqualTo(3);
	}

	@DisplayName("수동 구매 개수에 숫자가 아닌 값이 들어오면 예외가 발생한다.")
	@Test
	void readManualCountNotIntegerError() {
		InputView inputView = inputView("abc");

		assertThatThrownBy(inputView::readManualCount)
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessageContaining("숫자만 입력할 수 있습니다.");
	}

	@DisplayName("수동 번호를 장수만큼 입력받는다.")
	@Test
	void readManualTicketsSuccess() {
		InputView inputView = inputView(
			"1, 2, 3, 4, 5, 6\n" +
				"7, 8, 9, 10, 11, 12"
		);

		LottoTickets manualTickets = inputView.readManualTickets(2);

		assertThat(manualTickets.size()).isEqualTo(2);
	}

	@DisplayName("수동 번호에 중복이 있으면 예외가 발생한다.")
	@Test
	void readManualTicketsDuplicateError() {
		InputView inputView = inputView("1, 2, 3, 4, 5, 5");

		assertThatThrownBy(() -> inputView.readManualTickets(1))
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessageContaining("로또 번호는 중복될 수 없습니다.");
	}
}
