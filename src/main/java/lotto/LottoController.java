package lotto;

import java.util.List;

public class LottoController {
	private final InputView inputView;
	private final OutputView outputView;

	public LottoController(InputView inputView, OutputView outputView) {
		this.inputView = inputView;
		this.outputView = outputView;
	}

	public void run() {
		LottoTickets lottoTickets = readLottoTickets();

		WinningNumbers winningNumbers = readWinningNumbers();
		LottoAnswer lottoAnswer = readLottoAnswer(winningNumbers);
		LottoStatistics lottoStatistics = buildStatistics(lottoAnswer, lottoTickets);
		outputView.printStatistics(lottoStatistics);
	}

	private LottoTickets readLottoTickets() {
		while (true) {
			try {
				Money money = readMoney();
				int manualCount = inputView.readManualCount();
				money.validateManualCount(manualCount);

				LottoTickets manualTickets = inputView.readManualTickets(manualCount);
				int autoCount = money.toPurchaseCount() - manualCount;

				LottosGenerator generator = new CompositeLottosGenerator(List.of(
					new ManualLottosGenerator(manualTickets),
					new AutoLottosGenerator(autoCount)
				));
				LottoTickets lottoTickets = generator.generate();
				outputView.printPurchaseResult(manualCount, autoCount, lottoTickets);
				return lottoTickets;
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

	private WinningNumbers readWinningNumbers() {
		while (true) {
			try {
				return inputView.readWinningNumbers();
			} catch (IllegalArgumentException exception) {
				outputView.printError(exception.getMessage());
			}
		}
	}

	private LottoAnswer readLottoAnswer(WinningNumbers winningNumbers) {
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
