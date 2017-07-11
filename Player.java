import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Player {
	private String name;
	private int bet;
	@SuppressWarnings("unused")
	private int handTotal;
	private int money;
	//max possibility of 21 cards (all aces)
	private Card[] hand = new Card[21];
	
	public Player(String name) {
		this.name = name;
	}
	
	public String getName() {
		return this.name;
	}
	
	public int getBet() {
		return this.bet;
	}
	
	public void setBet(int bet) {
		this.bet = bet;
	}
	
	public int getMoney() {
		if (money <= 0) {
			System.out.println("Sorry, " + this.getName() + " you have ran out of money!");
		}
		return this.money;
	}
	
	public int setMoney() {
		this.money = 100;
		return money;
	}
	
	public Card[] getHand() {
		if (this.hand == null) {
			return null;
		}
		return this.hand;
	}
	
	public void setHand(Card[] hand) {
		this.hand = hand;
	}
	
	public int getHandTotal() {
		int handTotal = 0;
		for (int i = 0; i < hand.length; i++) {
			if (hand[i] == null) {
				continue;
			}
			handTotal = handTotal + hand[i].getCardValue();
		}
		this.handTotal = handTotal;
		return handTotal;
	}
	
	public int getHandTotalWithAce() {
		int handTotal = 0;
		for (int i = 0; i < hand.length; i++) {
			if (hand[i] == null) {
				continue;
			}
			handTotal = handTotal + hand[i].getCardValue2();
		}
		this.handTotal = handTotal;
		return handTotal;
	}
	
	public void setHandTotal(int handTotal) {
		this.handTotal = handTotal;
	}
	
	public boolean contains(Card[] c) {
		for (int i = 0; i < c.length; i++) {
			if (c[i] == null) {
				return false;
			}
			if (c[i].getCardValue2() == 11 || c[i].getCardValue() == 1) {
				return true;
			}
		}
		return false;
	}
	
	public int getNumCardsInHand() {
		int count = 0;
		for (int i = 0; i < hand.length; i++) {
			if (hand[i] != null) {
				count++;
			}
		}
		return count;
	}
	public void win(JFrame frame) {
		this.money = this.money + this.bet;
		JOptionPane.showMessageDialog(frame, "You won this round! Your balance is now "
				+ this.money);
	}
	
	public void win2(JFrame frame)	{
		this.money = this.money + 2 * this.bet;
		JOptionPane.showMessageDialog(frame, "You won this round! Your balance is now "
				+ this.money);
	}
	
	public void winBlackjack(JFrame frame) {
		this.money = this.money + 3 * this.bet;
		JOptionPane.showMessageDialog(frame, "You won this round! Your balance is now "
				+ this.money);
	}
	
	public void lose(JFrame frame) {
		this.money = this.money - this.bet;
		JOptionPane.showMessageDialog(frame, "You lost this round! Your balance is now "
				+ this.money);
	}
	
	public void lose2(JFrame frame) {
		this.money = this.money - 2 * this.bet;
		JOptionPane.showMessageDialog(frame, "You lost this round! Your balance is now "
				+ this.money);
	}
	
	public void push(JFrame frame) {
		JOptionPane.showMessageDialog(frame, "You pushed this round! Your balance is still "
				+ this.money);
	}
	
	public void hit(Deck d, Player p, JFrame frame) {
		if (p.getHandTotal() > 21) {
			JOptionPane.showMessageDialog(frame, "You have already busted at " + p.getHandTotal() 
			+ "!");
		}
		else {
			Card newCard = d.dealNextCard(p);
			hand[p.getNumCardsInHand()] = newCard;
		}	
	}
	
	public Card getLastCard() {
		return this.getHand()[this.getNumCardsInHand() - 1];
	}
	
	public Card getTwoToLastCard() {
		return this.getHand()[this.getNumCardsInHand() - 2];
	}
	
	public Card getLastCardWithAce() {
		return this.getHand()[this.getNumCardsInHand() - 1];
	}
}
