package lotto;

public class ManualLottosGenerator implements LottosGenerator {
	private final LottoTickets manualTickets;

	public ManualLottosGenerator(LottoTickets manualTickets) {
		this.manualTickets = manualTickets;
	}

	@Override
	public LottoTickets generate() {
		return manualTickets;
	}
}
