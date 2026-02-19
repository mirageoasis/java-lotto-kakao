package lotto;

public class Money {
	private final int value;

	public Money(int value) {
		validateAmount(value);
		this.value = value;
	}

	public int toPurchaseCount() {
		return value / Const.TICKET_PRICE;
	}

	public void validateManualCount(int manualCount) {
		if (manualCount < 0) {
			throw new IllegalArgumentException("수동 구매 수량은 0 이상이어야 합니다.");
		}
		if (manualCount > toPurchaseCount()) {
			throw new IllegalArgumentException("수동 구매 수량이 전체 구매 수량을 초과할 수 없습니다.");
		}
	}

	private void validateAmount(int value) {
		if (value < Const.TICKET_PRICE)
			throw new IllegalArgumentException("구매 금액이 " + Const.TICKET_PRICE + "원 이상이어야 합니다.");
	}
}
