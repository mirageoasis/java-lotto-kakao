package lotto;

import java.util.ArrayList;
import java.util.List;

public class AutoLottosGenerator implements LottosGenerator {
	private static final LottoNumberPicker PICKER = new LottoNumberPicker();

	private final int count;

	public AutoLottosGenerator(int count) {
		if (count < 0) {
			throw new IllegalArgumentException("자동 로또 수는 0 이상이어야 합니다.");
		}
		this.count = count;
	}

	@Override
	public LottoTickets generate() {
		List<LottoTicket> tickets = new ArrayList<>();
		for (int i = 0; i < count; i++) {
			tickets.add(new LottoTicket(PICKER.pick()));
		}
		return new LottoTickets(tickets);
	}
}
