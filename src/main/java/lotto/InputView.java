package lotto;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class InputView {
	private final Scanner scanner;

	public InputView() {
		this(new Scanner(System.in));
	}

	public InputView(Scanner scanner) {
		this.scanner = scanner;
	}

	public Money readMoney() {
		System.out.println("구입금액을 입력해 주세요.");
		return new Money(parseInteger(scanner.nextLine()));
	}

	public WinningNumbers readWinningNumbers() {
		System.out.println("지난 주 당첨 번호를 입력해 주세요.");
		String input = scanner.nextLine();
		return parseWinningNumbers(input);
	}

	public LottoNumber readBonusNumber() {
		System.out.println("보너스 볼을 입력해 주세요.");
		return LottoNumber.of(parseInteger(scanner.nextLine()));
	}

	private WinningNumbers parseWinningNumbers(String input) {
		List<String> splitNumbers = Arrays.stream(input.split(","))
			.map(String::trim)
			.toList();
		validateWinningNumbersCount(splitNumbers);
		return new WinningNumbers(toLottoNumbers(splitNumbers));
	}

	private void validateWinningNumbersCount(List<String> splitNumbers) {
		if (splitNumbers.size() != 6) {
			throw new IllegalArgumentException("당첨 번호는 6개를 입력해야 합니다.");
		}
	}

	private Set<LottoNumber> toLottoNumbers(List<String> splitNumbers) {
		Set<LottoNumber> numbers = new HashSet<>();
		for (String splitNumber : splitNumbers) {
			numbers.add(LottoNumber.of(parseInteger(splitNumber)));
		}
		validateUniqueNumbers(numbers);
		return numbers;
	}

	private void validateUniqueNumbers(Set<LottoNumber> numbers) {
		if (numbers.size() != 6) {
			throw new IllegalArgumentException("당첨 번호는 중복될 수 없습니다.");
		}
	}

	private int parseInteger(String input) {
		try {
			return Integer.parseInt(input.trim());
		} catch (NumberFormatException exception) {
			throw new IllegalArgumentException("숫자만 입력할 수 있습니다.");
		}
	}
}
