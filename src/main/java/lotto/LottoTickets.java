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

	public static LottoTickets empty() {
		return new LottoTickets(List.of());
	}

	public LottoTickets concat(LottoTickets other) {
		List<LottoTicket> merged = new ArrayList<>(tickets);
		merged.addAll(other.tickets);
		return new LottoTickets(merged);
	}

	public void forEach(Consumer<LottoTicket> action) {
		tickets.forEach(action);
	}
}
