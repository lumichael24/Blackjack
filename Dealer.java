import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Dealer extends Player {
	@SuppressWarnings("unused")
	private Card[] hand = new Card[21];
	
	public Dealer(String house) {
		super(house);
	}
	
	@Override
	public void hit(Deck d, Player house, JFrame frame) {
		Card newCard = d.dealNextCard(house);
		house.getHand()[house.getNumCardsInHand()] = newCard;
	}
	
	@Override
	public void win(JFrame frame) {
		//do nothing
	}
	
	@Override
	public void win2(JFrame frame)	{
		//do nothing
	}
	
	@Override
	public void winBlackjack(JFrame frame) {
		//do nothing
	}
	
	@Override
	public void lose(JFrame frame) {
		//do nothing
	}
	
	@Override
	public void lose2(JFrame frame) {
		//do nothing
	}
	
	@Override
	public void push(JFrame frame) {
		//do nothing
	}
	
	public void checkBlackjack(Player p, JFrame frame) {
			JOptionPane.showMessageDialog(frame, "Dealer has a Blackjack!");
			p.lose(frame);
		
	}
	

}
