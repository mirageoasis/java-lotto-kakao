package lotto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Consumer;

public class LottoTickets {
	private final List<LottoTicket> tickets;

	public LottoTickets(List<LottoTicket> tickets) {
		this.tickets = Collections.unmodifiableList(new ArrayList<>(tickets));
	}

	public int size() {
		return tickets.size();
	}

	public void forEach(Consumer<LottoTicket> action) {
		tickets.forEach(action);
	}
}
