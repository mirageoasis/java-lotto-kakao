package lotto;

import java.util.List;

public class CompositeLottosGenerator implements LottosGenerator {
	private final List<LottosGenerator> generators;

	public CompositeLottosGenerator(List<LottosGenerator> generators) {
		this.generators = List.copyOf(generators);
	}

	@Override
	public LottoTickets generate() {
		LottoTickets result = LottoTickets.empty();
		for (LottosGenerator generator : generators) {
			result = result.concat(generator.generate());
		}
		return result;
	}
}
