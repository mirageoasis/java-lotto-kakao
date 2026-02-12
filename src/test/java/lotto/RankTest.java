package lotto;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class RankTest {
	private final Map<String, Long> mapper = new HashMap<>() {{
		put("FIRST", 2000000000L);
		put("SECOND", 30000000L);
		put("THIRD", 1500000L);
		put("FOURTH", 50000L);
		put("FIFTH", 5000L);
		put("OTHER", 0L);
	}};

	@Test
	@DisplayName("ENUM 필드와 값이 일치하는지 확인")
	public void test_enum() {
		for (Map.Entry<String, Long> map : mapper.entrySet()) {
			assertThat(Rank.valueOf(map.getKey()).prizeMoney()).isEqualTo(map.getValue());
		}
	}

	@Test
	@DisplayName("6개 일치면 FIRST를 반환한다")
	void from_match_result_first() {
		assertThat(Rank.fromMatchResult(6, false)).isEqualTo(Rank.FIRST);
	}

	@Test
	@DisplayName("5개 일치 + 보너스 일치면 SECOND를 반환한다")
	void from_match_result_second() {
		assertThat(Rank.fromMatchResult(5, true)).isEqualTo(Rank.SECOND);
	}

	@Test
	@DisplayName("5개 일치 + 보너스 불일치면 THIRD를 반환한다")
	void from_match_result_third() {
		assertThat(Rank.fromMatchResult(5, false)).isEqualTo(Rank.THIRD);
	}

	@Test
	@DisplayName("4개 일치면 FOURTH를 반환한다")
	void from_match_result_fourth() {
		assertThat(Rank.fromMatchResult(4, false)).isEqualTo(Rank.FOURTH);
	}

	@Test
	@DisplayName("3개 일치면 FIFTH를 반환한다")
	void from_match_result_fifth() {
		assertThat(Rank.fromMatchResult(3, false)).isEqualTo(Rank.FIFTH);
	}

	@Test
	@DisplayName("3개 미만 일치면 OTHER를 반환한다")
	void from_match_result_other() {
		assertThat(Rank.fromMatchResult(2, true)).isEqualTo(Rank.OTHER);
	}

}
