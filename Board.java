import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Board extends JPanel {
	
	public boolean playing;
	private JLabel status;
	private Card[][] table = new Card[2][21];
	public static final String IMG_FILE = "blackjack.png";
	private static BufferedImage img;
	public static final String IMG_FILE2 = "card.gif";
	private static BufferedImage img2;
	
	
	public Board(JLabel status) {
		this.status = status;
		try {
	        if (img == null) {
	        	img = ImageIO.read(new File(IMG_FILE));
	        }
	        if (img2 == null) {
	           	img2 = ImageIO.read(new File(IMG_FILE2));
	        }
	    } catch (IOException e) {
	    	System.out.println("Internal Error:" + e.getMessage());
	        }
	    }
	
	public void reset(Player p, Player house) {
		p.setHand(new Card[21]);
		house.setHand(new Card[21]);
		p.setHandTotal(0);
		house.setHandTotal(0);
		
	}
	
	public JLabel getStatus() {
		return this.status;
	}

	public Card[][] getTable() {
		return this.table;
	}
	
	public void setTable() {
		for (int i = 0; i < table.length; i++) {
			for (int j = 0; j < table[0].length; j++) {
				table[i][j] = null;
			}
		}
	}
	 
	 public boolean contains() {
		if (table[1][0] == null) {
			 return false;
		 }
		else {
			 return true;
		 }
		 
	 }
	 
	 public boolean dealerContains() {
		 if (table[0][0] == null) {
			 return false;
		 }
		 else {
			 return true;
		 }
	 }
	 
	 public void addCards(Card c) {
		 for (int j = 0; j < table[1].length; j++) {
			 if (table[1][j] != null) {
				 continue;
			 }
			 else {
				 table[1][j] = c;
				 break;
			 }
		 }
	 }
	 
	 public void addDealerCards(Card c) {
		 for (int j = 0; j < table[0].length; j++) {
			 if (table[0][j] != null) {
				 continue;
			 }
			 else {
				 table[0][j] = c;
				 break;
			 }
		 }
	 
	 }
	 
	 public Card getLastCard() {
		 for (int j = 0; j < table[1].length; j++) {
			 if (table[1][j] != null) {
				 continue;
			 }
			 else {
				 return table[1][j - 1];
			 }
		 }
		 return null;
	 }
	 
	 public Card getDealerLastCard() {
		 for (int j = 0; j < table[0].length; j++) {
			 if (table[0][j] != null) {
				 continue;
			 }
			 else {
				 return table[0][j - 1];
			 }
		 }
		 return null;
	 }
	 
	 public void forceRepaint() {
		 this.repaint();
	 }
	 
	 //1920 x 1080
	 @Override
	 public void paintComponent(Graphics g) {
		 g.drawImage(img, 680, 0, 1200, 1200, null);
		 for (int i = 0; i < 25; i++) {
			 g.drawImage(img2, 1500 + 10 * i, 250, 100, 100, null); 
		 }
		 if (!this.contains()) {
			 //do nothing
		 }
		 else {
			 for (int j = 0; j < 21; j++) {
					 if (table[1][j] == null) {
						 continue;
					 }
					 else {
						 g.drawImage(Picture.getImage(table[1][j].getCardName()), 
									900 + 150 * j, 600, 150, 150, null);
					 }
				 }
			 }
		 	 for (int j = 0; j < 21; j++) {
		 		 if (table[0][j] == null) {
		 			 continue;
		 		 }
		 		 else {
		 			g.drawImage(Picture.getImage(table[0][j].getCardName()), 
							900 + 150 * j, 200, 150, 150, null);
		 		 }
		 	 }
		
	 }
}
