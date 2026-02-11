package lotto;

public class Money {
	private final int value;

	public Money(int value) {
		validateAmount(value);
		this.value = value;
	}

	public int toPurchaseCount(){
		return value / Const.TICKET_PRICE;
	}

	private void validateAmount(int value){
		if (value < Const.TICKET_PRICE)
			throw new IllegalArgumentException("구매 금액이 " + Const.TICKET_PRICE + "원 이상이어야 합니다.");
	}
}
