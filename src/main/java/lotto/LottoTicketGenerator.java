package lotto;

import java.util.ArrayList;
import java.util.List;

public class LottoTicketGenerator {
	private static final LottoNumberPicker lottoNumberPicker = new LottoNumberPicker();

	public static LottoTickets generate(int ticketNumber) {
		if (ticketNumber <= 0)
			throw new IllegalArgumentException("티켓의 개수는 1이상이어야 합니다");

		List<LottoTicket> lottoTicketList = new ArrayList<>();
		for (int i = 0; i < ticketNumber; i++) {
			lottoTicketList.add(new LottoTicket(lottoNumberPicker.pick()));
		}
		return new LottoTickets(lottoTicketList);
	}
}
