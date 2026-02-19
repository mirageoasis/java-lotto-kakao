package lotto;

import java.util.Arrays;
import java.util.ArrayList;
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
		return new WinningNumbers(parseLottoNumbers(scanner.nextLine(), "당첨 번호"));
	}

	public LottoNumber readBonusNumber() {
		System.out.println("보너스 볼을 입력해 주세요.");
		return LottoNumber.of(parseInteger(scanner.nextLine()));
	}

	public int readManualCount() {
		System.out.println("수동으로 구매할 로또 수를 입력해 주세요.");
		return parseInteger(scanner.nextLine());
	}

	public LottoTickets readManualTickets(int manualCount) {
		System.out.println();
		System.out.println("수동으로 구매할 번호를 입력해 주세요.");

		List<LottoTicket> tickets = new ArrayList<>();
		for (int i = 0; i < manualCount; i++) {
			tickets.add(new LottoTicket(parseLottoNumbers(scanner.nextLine(), "로또 번호")));
		}
		return new LottoTickets(tickets);
	}

	private Set<LottoNumber> parseLottoNumbers(String input, String label) {
		List<String> splitNumbers = Arrays.stream(input.split(","))
			.map(String::trim)
			.toList();
		validateNumbersCount(splitNumbers, label);

		Set<LottoNumber> numbers = new HashSet<>();
		for (String splitNumber : splitNumbers) {
			numbers.add(LottoNumber.of(parseInteger(splitNumber)));
		}
		validateUniqueNumbers(numbers, label);
		return numbers;
	}

	private void validateNumbersCount(List<String> splitNumbers, String label) {
		if (splitNumbers.size() != Const.LOTTO_NUMBER_COUNT) {
			throw new IllegalArgumentException(label + "는 6개를 입력해야 합니다.");
		}
	}

	private void validateUniqueNumbers(Set<LottoNumber> numbers, String label) {
		if (numbers.size() != Const.LOTTO_NUMBER_COUNT) {
			throw new IllegalArgumentException(label + "는 중복될 수 없습니다.");
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
