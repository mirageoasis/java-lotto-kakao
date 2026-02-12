package lotto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

// 현재는 생성 정책이 1개이기 때문에 단순히 Picker라고 네이밍함.
// 생성정책 추가시 이름 변경 에정
public class LottoNumberPicker {
	private static final int MIN_NUMBER = 1;
	private static final int MAX_NUMBER = 45;

	public Set<LottoNumber> pick() {
		List<Integer> numbers = new ArrayList<>();
		for (int i = MIN_NUMBER; i <= MAX_NUMBER; i++) {
			numbers.add(i);
		}
		Collections.shuffle(numbers);

		Set<LottoNumber> lottoNumbers = new HashSet<>();
		for (Integer number : numbers.subList(0, Const.LOTTO_NUMBER_COUNT)) {
			lottoNumbers.add(LottoNumber.of(number));
		}
		return lottoNumbers;
	}
}
