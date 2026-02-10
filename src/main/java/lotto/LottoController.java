package lotto;

import java.util.Set;

public class LottoController {
	private final InputView inputView;
	private final OutputView outputView;

	public LottoController(InputView inputView, OutputView outputView) {
		this.inputView = inputView;
		this.outputView = outputView;
	}

	public void run() {
		LottoTickets lottoTickets = readLottoTickets();
		outputView.printPurchaseResult(lottoTickets);

		Set<LottoNumber> winningNumbers = readWinningNumbers();
		LottoAnswer lottoAnswer = readLottoAnswer(winningNumbers);
		LottoStatistics lottoStatistics = buildStatistics(lottoAnswer, lottoTickets);
		outputView.printStatistics(lottoStatistics);
	}

	private LottoTickets readLottoTickets() {
		while (true) {
			try {
				return LottoTicketGenerator.generate(readMoney().toPurchaseCount());
			} catch (IllegalArgumentException exception) {
				outputView.printError(exception.getMessage());
			}
		}
	}

	private Money readMoney() {
		while (true) {
			try {
				return inputView.readMoney();
			} catch (IllegalArgumentException exception) {
				outputView.printError(exception.getMessage());
			}
		}
	}

	private Set<LottoNumber> readWinningNumbers() {
		while (true) {
			try {
				return inputView.readWinningNumbers();
			} catch (IllegalArgumentException exception) {
				outputView.printError(exception.getMessage());
			}
		}
	}

	private LottoAnswer readLottoAnswer(Set<LottoNumber> winningNumbers) {
		while (true) {
			try {
				LottoNumber bonusNumber = inputView.readBonusNumber();
				return new LottoAnswer(winningNumbers, bonusNumber);
			} catch (IllegalArgumentException exception) {
				outputView.printError(exception.getMessage());
			}
		}
	}

	private LottoStatistics buildStatistics(LottoAnswer lottoAnswer, LottoTickets lottoTickets) {
		LottoStatistics lottoStatistics = new LottoStatistics();
		lottoTickets.forEach(lottoTicket -> lottoStatistics.add(lottoAnswer.judge(lottoTicket)));
		return lottoStatistics;
	}
}
