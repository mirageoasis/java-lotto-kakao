package lotto;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.ArrayList;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LottoNumberTest {
	@Test
	@DisplayName("1 ~ 45까지 로또 번호를 생성할 수 있다")
	void generate_lotto_number_from_1_to_45() {
		List<LottoNumber> lottoNumbers = new ArrayList<>();

		for (int i = 1; i <= 45; i++) {
			lottoNumbers.add(LottoNumber.of(i));
		}

		Assertions.assertThat(lottoNumbers)
			.hasSize(45)
			.extracting(LottoNumber::getValue)
			.containsExactlyInAnyOrderElementsOf(generateExpectedNumbers());
	}

	@Test
	@DisplayName("1 ~ 45까지가 아닌 번호는 생성할 수 없다")
	public void cannot_create_invalid_lotto_number() {
		// 1. 범위보다 작은 값 (0) 테스트
		assertThatThrownBy(() -> LottoNumber.of(0))
			.isInstanceOf(IllegalArgumentException.class);

		// 2. 범위보다 큰 값 (46) 테스트
		assertThatThrownBy(() -> LottoNumber.of(46))
			.isInstanceOf(IllegalArgumentException.class);
	}

	@Test
	@DisplayName("같은 숫자는 동일한 LottoNumber 인스턴스를 반환한다")
	void cache_lotto_number() {
		LottoNumber first = LottoNumber.of(7);
		LottoNumber second = LottoNumber.of(7);

		assertThat(first).isSameAs(second);
	}

	private List<Integer> generateExpectedNumbers() {
		List<Integer> expectedNumbers = new ArrayList<>();
		for (int i = 1; i <= 45; i++) {
			expectedNumbers.add(i);
		}
		return expectedNumbers;
	}
}
