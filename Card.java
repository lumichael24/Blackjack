public class Card {

	private int val;
	private int val2;
	private String cardName;
	private String[] cardDeck = {"ace", "two", "three", "four", "five", "six", "seven", "eight", 
			"nine", "ten", "jack", "queen", "king"};
	
	public Card(int num) {
		if (num == 12 || num == 13) {
			this.val = 10;
			this.val2 = 10;
		}
		else if (num == 11 || num == 1) {
			this.val = 1;
			this.val2 = 11;
		}
		else {
			this.val = num;
			this.val2 = num;
		}
		if (num == 11 || num == 1) {
			this.cardName = cardDeck[0];
		}
		else {
			this.cardName = cardDeck[num - 1];
		}
		
	}
	public String getCardName() {
		return this.cardName;
	}
	
	public int getCardValue() {
		return this.val;
	}
	
	public int getCardValue2() {
		return this.val2;
	}
	
}
