package lotto;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LottoAnswerTest {

	@Test
	@DisplayName("당첨 번호가 6개가 아니면 예외가 발생한다")
	void create_fail_when_winning_numbers_count_is_not_six() {
		assertThatThrownBy(() -> winningNumbers(1, 2, 3, 4, 5))
			.isInstanceOf(IllegalArgumentException.class);
	}

	@Test
	@DisplayName("보너스 번호가 당첨 번호와 중복되면 예외가 발생한다")
	void create_fail_when_bonus_is_in_winning_numbers() {
		WinningNumbers winningNumbers = winningNumbers(1, 2, 3, 4, 5, 6);
		LottoNumber bonus = LottoNumber.of(6);

		assertThatThrownBy(() -> new LottoAnswer(winningNumbers, bonus))
			.isInstanceOf(IllegalArgumentException.class);
	}

	@Test
	@DisplayName("6개 일치하면 1등이다")
	void judge_first_rank() {
		LottoAnswer lottoAnswer = new LottoAnswer(winningNumbers(1, 2, 3, 4, 5, 6), LottoNumber.of(7));
		LottoTicket ticket = new LottoTicket(lottoNumbers(1, 2, 3, 4, 5, 6));

		assertThat(lottoAnswer.judge(ticket)).isEqualTo(Rank.FIRST);
	}

	@Test
	@DisplayName("5개 일치 + 보너스 일치면 2등이다")
	void judge_second_rank() {
		LottoAnswer lottoAnswer = new LottoAnswer(winningNumbers(1, 2, 3, 4, 5, 6), LottoNumber.of(7));
		LottoTicket ticket = new LottoTicket(lottoNumbers(1, 2, 3, 4, 5, 7));

		assertThat(lottoAnswer.judge(ticket)).isEqualTo(Rank.SECOND);
	}

	@Test
	@DisplayName("5개 일치 + 보너스 불일치면 3등이다")
	void judge_third_rank() {
		LottoAnswer lottoAnswer = new LottoAnswer(winningNumbers(1, 2, 3, 4, 5, 6), LottoNumber.of(7));
		LottoTicket ticket = new LottoTicket(lottoNumbers(1, 2, 3, 4, 5, 8));

		assertThat(lottoAnswer.judge(ticket)).isEqualTo(Rank.THIRD);
	}

	@Test
	@DisplayName("4개 일치하면 4등이다")
	void judge_fourth_rank() {
		LottoAnswer lottoAnswer = new LottoAnswer(winningNumbers(1, 2, 3, 4, 5, 6), LottoNumber.of(7));
		LottoTicket ticket = new LottoTicket(lottoNumbers(1, 2, 3, 4, 8, 9));

		assertThat(lottoAnswer.judge(ticket)).isEqualTo(Rank.FOURTH);
	}

	@Test
	@DisplayName("3개 일치하면 5등이다")
	void judge_fifth_rank() {
		LottoAnswer lottoAnswer = new LottoAnswer(winningNumbers(1, 2, 3, 4, 5, 6), LottoNumber.of(7));
		LottoTicket ticket = new LottoTicket(lottoNumbers(1, 2, 3, 8, 9, 10));

		assertThat(lottoAnswer.judge(ticket)).isEqualTo(Rank.FIFTH);
	}

	@Test
	@DisplayName("3개 미만 일치면 당첨이 아니다")
	void judge_not_winning() {
		LottoAnswer lottoAnswer = new LottoAnswer(winningNumbers(1, 2, 3, 4, 5, 6), LottoNumber.of(7));
		LottoTicket ticket = new LottoTicket(lottoNumbers(1, 2, 8, 9, 10, 11));

		assertThat(lottoAnswer.judge(ticket)).isEqualTo(Rank.OTHER);
	}

	private Set<LottoNumber> lottoNumbers(int... values) {
		return Arrays.stream(values)
			.mapToObj(LottoNumber::of)
			.collect(Collectors.toSet());
	}

	private WinningNumbers winningNumbers(int... values) {
		return new WinningNumbers(lottoNumbers(values));
	}
}
