//import java.util.ArrayList;
//import java.util.Collections;
//import java.util.List;
//
//import javax.swing.JOptionPane;
//
//public class Deck {
//	
//	private List<Card> deckOfCards;
//	private int numCards;
//	private int[] count = new int[11];
//	private int numDecks;
//	
//	public Deck() {
//		for (int i = 0; i < count.length; i++) {
//			if (i == 0) {
//				count[i] = this.getNumCards();
//			}
//			else {
//				count[i] = 0;
//			}
//		}
//		this.numDecks = 8;
//		this.numCards = 52 * 8;
//		deckOfCards = new ArrayList<Card>();
//		for (int i = 0; i < 8; i++) {
//			for (int m = 0; m < 4; m++) {
//				for (int j = 1; j < 14; j++) {
//					deckOfCards.add(new Card(j));
//				}
//			}
//		}
//		Collections.shuffle(deckOfCards);
//	}
//	
//	public void dealGame(Player p, Player house) {
//		Card dealtCard = deckOfCards.remove(0);
//		Card dealtCard2 = deckOfCards.remove(0);
//		Card dealtCard3 = deckOfCards.remove(0);
//		this.setCountArr(dealtCard.getCardValue());
//		this.setCountArr(dealtCard2.getCardValue());
//		this.setCountArr(dealtCard3.getCardValue());
//		p.getHand()[0] = dealtCard;
//		p.getHand()[1] = dealtCard2;
//		house.getHand()[0] = dealtCard3;
//		numCards = numCards - 3;
//	}
//	
//	public Card dealNextCard(Player p) {
//		Card dealtCard = deckOfCards.remove(0);
//		numCards--;
//		return dealtCard;
//	}
//	
//	public int[] getCountArr() {
//		return this.count;
//	}
//	
//	public void setCountArr(int n) {
//		for (int i = 0; i < count.length; i++) {
//			if (n == i) {
//				count[i] = count[i] + 1;
//			}
//			else { 
//				continue;
//			}
//		}
//	}
//	
//	public int getNumDecks() {
//		if (numCards <= 52 * 7 && numCards > 52 * 6) {
//			this.numDecks = 7;
//			return numDecks;
//		}
//		else if (numCards <= 52 * 6 && numCards > 52 * 5) {
//			this.numDecks = 6;
//			return numDecks;
//		}
//		else if (numCards <= 52 * 5 && numCards > 52 * 4) {
//			this.numDecks = 5;
//			return numDecks;
//		}
//		else if (numCards <= 52 * 4 && numCards > 52 * 3) {
//			this.numDecks = 4;
//			return numDecks;
//		}
//		else if (numCards <= 52 * 3 && numCards > 52 * 2) {
//			this.numDecks = 3;
//			return numDecks;
//		}
//		else if (numCards <= 52 * 2 && numCards > 52 * 1) {
//			this.numDecks = 2;
//			return numDecks;
//		}
//		else if (numCards <= 52) {
//			this.numDecks = 1;
//			return numDecks;
//		}
//		return this.numDecks;
//	}
//	
//	public int getNumCards() {
//		return this.numCards;
//	}
//	
//	public double countRemainingCards(int n, int countOfDistinctCards) {
//		double total = 0;
//		if (countOfDistinctCards == 10) {
//			countOfDistinctCards = 4;
//		}
//		else {
//			countOfDistinctCards = 14 - countOfDistinctCards;
//		}
//		countOfDistinctCards = countOfDistinctCards * 32;
//		for (int i = 1; i < count.length; i++) {
//			if (i < n) {
//				continue;
//			}
//			else {
//				total = total + count[i];
//			}
//		}
//		return (countOfDistinctCards - total) / this.getNumCards();
//	}
//	
//	public double countLessRemainingCards(int n, int countOfDistinctCards) {
//		double total = 0;
//		if (countOfDistinctCards == 10) {
//			countOfDistinctCards = 14;
//		}
////		else {
////			countOfDistinctCards = 1;
////		}
//		countOfDistinctCards = countOfDistinctCards * 32;
//		for (int i = 1; i < count.length; i++) {
//			if (i > n) {
//				continue;
//			}
//			else {
//				total = total + count[i];
//			}
//		}
//		return (countOfDistinctCards - total) / this.getNumCards();
//	}
//	
//	public double getTrueCount() {
//		double lowTotal = 0;
//		double highTotal = 0;
//		for (int i = 0; i < 3; i++) {
//			highTotal = highTotal + this.count[i + 8];
//		}
//		highTotal = highTotal + this.count[1];
//		for (int i = 2; i < 6; i++) {
//			lowTotal = lowTotal + count[i];
//		}	
//		return (lowTotal - highTotal) / this.getNumDecks();
//	}
//	
//
//}
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.JOptionPane;

public class Deck {
	
	private List<Card> deckOfCards;
	private int numCards;
	private int[] count = new int[11];
	private int numDecks;
	
	public Deck() {
		for (int i = 0; i < count.length; i++) {
			if (i == 0) {
				count[i] = this.getNumCards();
			}
			else {
				count[i] = 0;
			}
		}
		this.numDecks = 8;
		this.numCards = 52 * 8;
		deckOfCards = new ArrayList<Card>();
		for (int i = 0; i < 8; i++) {
			for (int m = 0; m < 4; m++) {
				for (int j = 1; j < 14; j++) {
					deckOfCards.add(new Card(j));
				}
			}
		}
		Collections.shuffle(deckOfCards);
	}
	
	public void dealGame(Player p, Player house) {
		Card dealtCard = deckOfCards.remove(0);
		Card dealtCard2 = deckOfCards.remove(0);
		Card dealtCard3 = deckOfCards.remove(0);
		this.setCountArr(dealtCard.getCardValue());
		this.setCountArr(dealtCard2.getCardValue());
		this.setCountArr(dealtCard3.getCardValue());
		p.getHand()[0] = dealtCard;
		p.getHand()[1] = dealtCard2;
		house.getHand()[0] = dealtCard3;
		numCards = numCards - 3;
	}
	
	public Card dealNextCard(Player p) {
		Card dealtCard = deckOfCards.remove(0);
		numCards--;
		return dealtCard;
	}
	
	public int[] getCountArr() {
		return this.count;
	}
	
	public void setCountArr(int n) {
		for (int i = 0; i < count.length; i++) {
			if (n == i) {
				count[i] = count[i] + 1;
			}
			else { 
				continue;
			}
		}
	}
	
	public int getNumDecks() {
		if (numCards <= 52 * 7 && numCards > 52 * 6) {
			this.numDecks = 7;
			return numDecks;
		}
		else if (numCards <= 52 * 6 && numCards > 52 * 5) {
			this.numDecks = 6;
			return numDecks;
		}
		else if (numCards <= 52 * 5 && numCards > 52 * 4) {
			this.numDecks = 5;
			return numDecks;
		}
		else if (numCards <= 52 * 4 && numCards > 52 * 3) {
			this.numDecks = 4;
			return numDecks;
		}
		else if (numCards <= 52 * 3 && numCards > 52 * 2) {
			this.numDecks = 3;
			return numDecks;
		}
		else if (numCards <= 52 * 2 && numCards > 52 * 1) {
			this.numDecks = 2;
			return numDecks;
		}
		else if (numCards <= 52) {
			this.numDecks = 1;
			return numDecks;
		}
		return this.numDecks;
	}
	
	public int getNumCards() {
		return this.numCards;
	}
	
	public double countRemainingCards(int n, int countOfDistinctCards) {
		double total = 0;
		countOfDistinctCards = countOfDistinctCards * 32;
		for (int i = 1; i < count.length; i++) {
			if (i < n) {
				continue;
			}
			else {
				total = total + count[i];
			}
		}
		return (countOfDistinctCards - total) / this.getNumCards();
	}
	
	public double countLessRemainingCards(int n, int countOfDistinctCards) {
		double total = 0;
		countOfDistinctCards = countOfDistinctCards * 32;
		for (int i = 1; i < count.length; i++) {
			if (i > n) {
				continue;
			}
			else {
				total = total + count[i];
			}
		}
		return (countOfDistinctCards - total) / this.getNumCards();
	}
	
	public double getTrueCount() {
		double lowTotal = 0;
		double highTotal = 0;
		for (int i = 0; i < 3; i++) {
			highTotal = highTotal + this.count[i + 8];
		}
		highTotal = highTotal + this.count[1];
		for (int i = 2; i < 6; i++) {
			lowTotal = lowTotal + count[i];
		}	
		return (lowTotal - highTotal) / this.getNumDecks();
	}
	

}

