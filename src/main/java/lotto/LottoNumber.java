package lotto;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class LottoNumber {
	private static final int MIN_NUMBER = 1;
	private static final int MAX_NUMBER = 45;
	private static final List<LottoNumber> CACHE = createCache();

	private final int value;

	private LottoNumber(int value) {
		this.value = value;
	}

	public static LottoNumber of(int value) {
		validateNumber(value);
		return CACHE.get(value - MIN_NUMBER);
	}

	private static List<LottoNumber> createCache() {
		List<LottoNumber> cache = new ArrayList<>();
		for (int number = MIN_NUMBER; number <= MAX_NUMBER; number++) {
			cache.add(new LottoNumber(number));
		}
		return List.copyOf(cache);
	}

	private static void validateNumber(int value) {
		if (!(MIN_NUMBER <= value && value <= MAX_NUMBER))
			throw new IllegalArgumentException();
	}

	public int getValue() {
		return this.value;
	}

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}
		if (!(object instanceof LottoNumber lottoNumber)) {
			return false;
		}
		return value == lottoNumber.value;
	}

	@Override
	public int hashCode() {
		return Objects.hash(value);
	}
}
