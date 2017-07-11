/**
 * CIS 120 Game HW
 * (c) University of Pennsylvania
 * @version 2.1, Apr 2017
 */

// imports necessary libraries for Java swing
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

/**
 * Game Main class that specifies the frame and widgets of the GUI
 */
public class Game implements Runnable {
    public void run() {
        // NOTE : recall that the 'final' keyword notes immutability even for local variables.
    	
        // Top-level frame in which game components live
        // Be sure to change "TOP LEVEL FRAME" to the name of your game
        final JFrame frame = new JFrame("University of Pennsylvania Blackjack");
        frame.setLocation(300, 300);
        frame.setSize(new Dimension(1700,1000));
        
        UIManager.put("OptionPane.minimumSize",new Dimension(500, 500)); 
        JOptionPane.showMessageDialog(frame, 
        
        "Welcome to Blackjack! Instructions: You will given 100 chips to begin the game.  \n"
        + "Regular casino rules apply. In this version, there will be no splitting, no insurance,"
        + "and if a player possesses more than one ace,\nthey will be counted as ones. The control"
        + "panel is located above, with buttons of your use."
        + "\nYour goal is to beat the dealer by getting as close "
        + "to 21 as possible without busting! \nTo begin: In order to begin playing, you must "
        + "specify a bet, which ranges between 1 chip and your total balance. \nClick Start Round "
        + "to begin the game. Click deal to obtain your hand. \n"
        + "Your hand and the dealer's hand will "
        + "both be shown on the bottom of the interactive screen. \nBlackjack Terms: " 
	    + "Double Down: Pay double your bet and obtain exactly one card from the dealer "
	    + "in a case of a win, also doubles your winnings) \n"
	    + "Hit: Retrieve another card from the deck \n"
	    + "Stand: Stay with your current hand -- dealer begins to hit \n"
	    + "Reset: Resets the table for a new round \n"
	    + "Advice: Gives advice on the current hand situation and what the the next move should be"
	    + "\nStart Game: Begins the round \n"
	    + "Deal: Retrieve two cards from the deck, Blackjack if the cards add up to 21 \n"
	    + "Blackjack: Pays 3 to 1 \n"
	    + "After a round ends: click reset to begin a new round. \n"
	    + "If you run out of money: you have lost and are forced out of the casino. You may "
	    + "reload the game to play again.", "University of Pennsylvania Blackjack Instructions", 
	    JOptionPane.PLAIN_MESSAGE);
        
        UIManager.put("OptionPane.minimumSize",new Dimension(150, 150));
    	Player p = new Player(JOptionPane.showInputDialog
    			(null, "Please enter your name", "Blackjack", JOptionPane.PLAIN_MESSAGE));
    	Player house = new Dealer("House");
        Deck d = new Deck();
        p.setMoney();

        // Status panel
        final JPanel status_panel = new JPanel();
        frame.add(status_panel, BorderLayout.SOUTH);
        final JLabel status = new JLabel("Running...");
        status.setFont(new Font("Verdana", Font.BOLD, 20));
        status_panel.add(status);
        
        final Board board = new Board(status);
        frame.add(board, BorderLayout.CENTER);
        
        final JPanel control_panel = new JPanel();
        frame.add(control_panel, BorderLayout.NORTH);
        
        final JPanel score_panel = new JPanel();
        frame.add(score_panel, BorderLayout.SOUTH);
        
        final JLabel totalHand = new JLabel("Total Hand: 0");
        totalHand.setFont(new Font("Verdana", Font.BOLD, 30));
        score_panel.add(totalHand);
        score_panel.add(Box.createRigidArea(new Dimension(100,0)));
        
        final JLabel balance = new JLabel("Balance: 100");
        balance.setFont(new Font("Verdana", Font.BOLD, 30));
        score_panel.add(balance);
        score_panel.add(Box.createRigidArea(new Dimension(100,0)));
        
        final JLabel dealerHand = new JLabel("Dealer Hand: 0");
        dealerHand.setFont(new Font("Verdana", Font.BOLD, 30));
        score_panel.add(dealerHand);
        
        final JButton hit = new JButton("Hit");
        final JButton stand = new JButton("Stand");
        final JButton deal = new JButton("Deal");
        final JButton dd = new JButton("Double Down");
        final JButton start = new JButton("Start Round");
        final JButton reset = new JButton("Reset Table");
        final JButton advice = new JButton("Advice");
        advice.setEnabled(false);
        hit.setEnabled(false);
        stand.setEnabled(false);
        dd.setEnabled(false);
        hit.setEnabled(false);
        stand.setEnabled(false);
        deal.setEnabled(false);
        reset.setEnabled(false);
        
        advice.setFont(new Font("Verdana", Font.BOLD, 20));
        advice.setPreferredSize(new Dimension(200, 50));;
        advice.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	UIManager.put("OptionPane.minimumSize",new Dimension(500, 500)); 
            	double trueCount = d.getTrueCount();
            	//both showing aces
            	if (p.contains(p.getHand()) && house.contains(house.getHand())) {
            		if (p.getHandTotalWithAce() >= 13 && p.getHandTotalWithAce() <= 17 && 
            				trueCount >= 0.5) {
                		JOptionPane.showMessageDialog(frame, "You are showing a " + 
                			p.getHandTotal() + " / " + p.getHandTotalWithAce() + ", and "
                			+ "we see that the dealer's face card is an 11." + " \nThe probability"
                    			+ " that you receive a card greater than or equal to a four is " +
                    			d.countRemainingCards(4, 10) +
                    			". " + "\nWe "
                    			+ "recommend that you should hit. Note that the true count"
                    			+ " of the deck is " + d.getTrueCount() + ".\nA positive true "
                    					+ "count"
                    					+ " indicates that you have a good chance of retrieving "
                    					+ "a high face card.");
            		}
            		else if (p.getHandTotalWithAce() >= 18) {
            			JOptionPane.showMessageDialog(frame, "You are showing a " + 
                    			p.getHandTotal() + " / " + p.getHandTotalWithAce() + ", and "
                    			+ "we see that the dealer's face card is an 11." + 
                    			" \nThe probability"
                        			+ " that you receive a card less than or equal to a three is " 
                        			+ d.countRemainingCards(3, 2) +
                        			". " + "\nWe "
                        			+ "recommend that you should stand. Note that the true count"
                        			+ " of the deck is " + d.getTrueCount() + ".\nA positive true "
                        					+ "count"
                        					+ " indicates that you have a good chance of retrieving"
                        					+ " a high face card.");
            		}
            		else {
            			JOptionPane.showMessageDialog(frame, "You are showing a " + 
                    			p.getHandTotal() + " / " + p.getHandTotalWithAce() + ", and "
                    			+ "we see that the dealer's face card is an 11."
                    			+ " \nThe probability"
                        			+ " that you receive a card greater than or equal to a four is"
                        			+ " " +
                        			d.countRemainingCards(4, 10) +
                        			". " + "\nWe "
                        			+ "recommend that you should hit. Note that the true count"
                        			+ " of the deck is " + d.getTrueCount() + ".\nA positive true "
                        					+ "count"
                        					+ " indicates that you have a good chance of retrieving"
                        					+ " a high face card.");
            		}
            	}
            	//player has an ace
            	else if (p.contains(p.getHand()) && !house.contains(house.getHand())) {
            		if (p.getHandTotalWithAce() >= 18 && p.getHandTotalWithAce() <= 21) {
            			JOptionPane.showMessageDialog(frame, "You are showing a " + 
                    			p.getHandTotal() + " / " + p.getHandTotalWithAce() + ", and "
                    			+ "we see that the dealer's face card is a " + house.getHandTotal() 
                    			+ "." + "\nThe probability"
                    			+ " that you receive a card equal to a ten is " +
                    			d.countRemainingCards(10, 4) +
                    			". " + "\nWe "
                    			+ "recommend that you should stand. Note that the true count"
                    			+ " of the deck is " + d.getTrueCount() + ".\nA positive true "
                    					+ "count"
                    					+ " indicates that you have a good chance of retrieving "
                    					+ "a high face card.");
            		}
            		else if (p.getHandTotalWithAce() >= 13 && p.getHandTotalWithAce()
            				<= 17) {
            			JOptionPane.showMessageDialog(frame, "You are showing a " + 
                    			p.getHandTotal() + " / " + p.getHandTotalWithAce() + ", and "
                    			+ "we see that the dealer's face card is a " + house.getHandTotal() 
                    			+ "." + "\nThe probability"
                    			+ " that you receive a card greater than or equal to an eight is " +
                    			d.countRemainingCards(8, 6) +
                    			". " + "\nWe "
                    			+ "recommend that you should hit. Note that the true count"
                    			+ " of the deck is " + d.getTrueCount() + ".\nA positive true "
                    					+ "count"
                    					+ " indicates that you have a good chance of retrieving "
                    					+ "a high face card.");
            		}
            		else {
            			if (p.getHandTotal() >= 16) {
            				JOptionPane.showMessageDialog(frame, "You are showing a " + 
                        			p.getHandTotal() + " / " + p.getHandTotalWithAce() + ", and "
                        			+ "we see that the dealer's face card is a " + house.getHandTotal() 
                        			+ "." + "\nThe probability"
                        			+ " that you receive a card less than or equal to a five "
                        			+ "is " +
                        			d.countLessRemainingCards(5, 4) +
                        			". " + "\nWe "
                        			+ "recommend that you should stand. Note that the true count"
                        			+ " of the deck is " + d.getTrueCount() + ".\nA positive true "
                        			+ "count"
                        			+ " indicates that you have a good chance of retrieving"
                       				+ " a high face card.");
            			}
            			else {
            				JOptionPane.showMessageDialog(frame, "You are showing a " + 
                        			p.getHandTotal() + " / " + p.getHandTotalWithAce() + ", and "
                        			+ "we see that the dealer's face card is a " + 
                        			house.getHandTotal() 
                        			+ "." + "\nThe probability"
                        			+ " that you receive a card greater than or equal to an eight "
                        			+ "is " +
                        			d.countRemainingCards(8, 6) +
                        			". " + "\nWe "
                        			+ "recommend that you should hit. Note that the true count"
                        			+ " of the deck is " + d.getTrueCount() + ".\nA positive true "
                        					+ "count"
                        					+ " indicates that you have a good chance of retrieving"
                        					+ " a high face card.");
            			}
            		}
            	}
            	//house showing an ace
            	else if (house.contains(house.getHand()) && !p.contains(p.getHand())) {
            		if (p.getHandTotal() >= 5 && p.getHandTotal() <= 16) {
            			JOptionPane.showMessageDialog(frame, "You are showing a " + 
                    			p.getHandTotal() + ", and "
                    			+ "we see that the dealer's face card is an eleven." + 
                    			"\nThe probability"
                    			+ " that you receive a card greater than or equal to a ten is " +
                    			d.countRemainingCards(10, 4) +
                    			". " + "\nWe "
                    			+ "recommend that you should hit. Note that the true count"
                    			+ " of the deck is " + d.getTrueCount() + ".\nA positive true "
                    					+ "count"
                    					+ " indicates that you have a good chance of retrieving "
                    					+ "a high face card.");
            		}
            		else if (p.getHandTotal() >= 17) {
            			JOptionPane.showMessageDialog(frame, "You are showing a " + 
                    			p.getHandTotal() + ", and "
                    			+ "we see that the dealer's face card is an eleven" 
                    			+ "." + "\nThe probability"
                    			+ " that you receive a card less than or equal to a four is " +
                    			d.countLessRemainingCards(4, 3) +
                    			". " + "\nWe "
                    			+ "recommend that you should stand. Note that the true count"
                    			+ " of the deck is " + d.getTrueCount() + ".\nA positive true "
                    					+ "count"
                    					+ " indicates that you have a good chance of retrieving "
                    					+ "a high face card.");
            		}
            		else {
            			JOptionPane.showMessageDialog(frame, "You are showing a " + 
                    			p.getHandTotal() + ", and "
                    			+ "we see that the dealer's face card is an eleven." + 
                    			"\nThe probability"
                    			+ " that you receive a card greater than or equal to a ten is " +
                    			d.countRemainingCards(10, 4) +
                    			". " + "\nWe "
                    			+ "recommend that you should hit. Note that the true count"
                    			+ " of the deck is " + d.getTrueCount() + ".\nA positive true "
                    					+ "count"
                    					+ " indicates that you have a good chance of retrieving "
                    					+ "a high face card.");
            		}
            		
            	}
            	//neither showing aces
            	else {
            		if (p.getHandTotal() >= 13 && house.getHandTotal() <= 6) {
            			JOptionPane.showMessageDialog(frame, "You are showing a " + 
                    			p.getHandTotal() + ", and "
                    			+ "we see that the dealer's face card is a " + house.getHandTotal() 
                    			+  ".\n" + "The probability"
                    			+ " that you receive a card greater than an eight is " +
                    			d.countRemainingCards(8, 6) + ".\n" + "We"
                    			+ "recommend that you should stand. \nNote that the true count"
                    			+ " of the deck is " + d.getTrueCount() + ".");
            		}
            		else if (p.getHandTotal() == 11) {
            			JOptionPane.showMessageDialog(frame, "You are showing a " + 
                    			p.getHandTotal() + ", and "
                    			+ "we see that the dealer's face card is a " + house.getHandTotal() 
                    			+  ".\n" + "The probability"
                    			+ " that you receive a card greater than an eight is " +
                    			d.countRemainingCards(8, 6) + ".\n" + "We "
                    			+ "recommend that you should double down. \nNote that the true "
                    			+ "count"
                    			+ " of the deck is " + d.getTrueCount() + ".");
            		}
            		else if (p.getHandTotal() >= 12 && p.getHandTotal() <= 16 && 
            				house.getHandTotal() >= 7) {
            			JOptionPane.showMessageDialog(frame, "You are showing a " + 
                    			p.getHandTotal() + ", and "
                    			+ "we see that the dealer's face card is a " + house.getHandTotal() 
                    			+ ".\n" + "The probability"
                    			+ " that you receive a card greater than or equal to a six is " +
                    			d.countRemainingCards(6, 8) + ". " + "\nWe "
                    			+ "recommend that you should hit. \nNote that the true count"
                    			+ " of the deck is " + d.getTrueCount() + ".");
            		}
            		else if (p.getHandTotal() >= 17 && house.getHandTotal() >= 7 && 
            				d.getTrueCount() >= 0) {
            			JOptionPane.showMessageDialog(frame, "You are showing a " + 
                    			p.getHandTotal() + ", and "
                    			+ "we see that the dealer's face card is a " + house.getHandTotal() 
                    			+ "." + "\nThe probability"
                    			+ " that you receive a card greater than or equal to a five is " +
                    			d.countRemainingCards(5, 9) +
                    			". " + "\nWe "
                    			+ "recommend that you should stand. Note that the true count"
                    			+ " of the deck is " + d.getTrueCount() + ".");
            		}
            		else if (p.getHandTotal() >= 9 && p.getHandTotal() <= 11 && 
            				house.getHandTotal() >= 2 && house.getHandTotal() <= 9 &&
            				d.countRemainingCards(10, 4) >= 0.35) {
            			JOptionPane.showMessageDialog(frame, "You are showing a " + 
                    			p.getHandTotal() + ", and "
                    			+ "we see that the dealer's face card is a " + house.getHandTotal() 
                    			+ "." + "\nThe probability"
                    			+ " that you receive a card greater than or equal to a nine is " +
                    			d.countRemainingCards(9, 5) +
                    			". " + "\nWe "
                    			+ "recommend that you should double down. Note that the true count"
                    			+ " of the deck is " + d.getTrueCount() + ".\nA positive true count"
                    					+ " indicates that you have a good chance of retrieving "
                    					+ "a high face card.");
            		}
            		else if (p.getHandTotal() >= 5 && p.getHandTotal() <= 8 && 
            				house.getHandTotal() >= 2 && house.getHandTotal() <= 11 &&
            				d.countRemainingCards(10, 4) >= 0.20) {
            			JOptionPane.showMessageDialog(frame, "You are showing a " + 
                    			p.getHandTotal() + ", and "
                    			+ "we see that the dealer's face card is a " + house.getHandTotal() 
                    			+ "." + "\nThe probability"
                    			+ " that you receive a card equal to a ten is " +
                    			d.countRemainingCards(10, 4) +
                    			". " + "\nWe "
                    			+ "recommend that you should hit. Note that the true count"
                    			+ " of the deck is " + d.getTrueCount() + ".\nA positive true "
                    					+ "count"
                    					+ " indicates that you have a good chance of retrieving "
                    					+ "a high face card.");
            		}
            		else if (p.getHandTotal() <= 15){
            			JOptionPane.showMessageDialog(frame, "You are showing a " + 
                    			p.getHandTotal() + ", and "
                    			+ "we see that the dealer's face card is a " + house.getHandTotal() 
                    			+ "." + "\nThe probability"
                    			+ " that you receive a card equal to a ten is " +
                    			d.countRemainingCards(10, 4) +
                    			". " + "\nWe "
                    			+ "recommend that you should hit. Note that the true count"
                    			+ " of the deck is " + d.getTrueCount() + ".\nA positive true "
                    					+ "count"
                    					+ " indicates that you have a good chance of retrieving "
                    					+ "a high face card.");
            		}
            		else {
            			JOptionPane.showMessageDialog(frame, "You are showing a " + 
                    			p.getHandTotal() + ", and "
                    			+ "we see that the dealer's face card is a " + house.getHandTotal() 
                    			+ "." + "\nThe probability"
                    			+ " that you receive a card equal to a ten is " +
                    			d.countRemainingCards(10, 4) +
                    			". " + "\nWe "
                    			+ "recommend that you should stand. Note that the true count"
                    			+ " of the deck is " + d.getTrueCount() + ".\nA positive true "
                    					+ "count"
                    					+ " indicates that you have a good chance of retrieving "
                    					+ "a high face card.");
            		}
            	}
            	UIManager.put("OptionPane.minimumSize",new Dimension(150, 150));
            }
            
        });
        control_panel.add(advice);
        
        dd.setFont(new Font("Verdana", Font.BOLD, 20));
        dd.setPreferredSize(new Dimension(200, 50));;
        dd.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	if (p.getNumCardsInHand() == 2) {
            		d.dealNextCard(p);
            	}
            	dd.setEnabled(false);
                hit.setEnabled(false);
                advice.setEnabled(false);
                checkAcesForPlayer(p, totalHand);
                p.hit(d, p, frame);
                d.setCountArr(p.getLastCard().getCardValue());
                board.addCards(p.getLastCard());
            	board.forceRepaint();
            	frame.repaint();
            	if (p.contains(p.getHand())) {
            		if (p.getHandTotalWithAce() > 21 && p.getHandTotal() > 21) {
            			p.lose2(frame);
            			hit.setEnabled(false);
                    	dd.setEnabled(false);
                    	stand.setEnabled(false);
                    	advice.setEnabled(false);
                    	reset.setEnabled(true);
                    	deal.setEnabled(false);
                    	start.setEnabled(false); 
            		}
            	}
            	if (p.getHandTotal() > 21) {
            		hit.setEnabled(false);
            		p.lose2(frame);
                	dd.setEnabled(false);
                	stand.setEnabled(false);
                	advice.setEnabled(false);
                	reset.setEnabled(true);
                	deal.setEnabled(false);
                	start.setEnabled(false);
            	}
                if (p.contains(p.getHand())) {
            		checkAcesForPlayer(p, totalHand);
            	}
            	else {
            		totalHand.setText("Total Hand: " + p.getHandTotal());
            	}
            	while (house.getHandTotal() <= 16) {
            		house.hit(d, house, frame);
            		d.setCountArr(house.getLastCard().getCardValue());
            		board.addDealerCards(house.getLastCard());
                	board.forceRepaint();
                	frame.repaint();
            		dealerHand.setText("Dealer Hand: " + house.getHandTotal());
            	}
                if (house.getHandTotal() > 21) {				
        			JOptionPane.showMessageDialog(frame, "The dealer busted at " 
        					+ house.getHandTotal() + "!");
        			p.win2(frame);
        		}
            	else if (p.getHandTotal() > house.getHandTotal() && p.getHandTotal() <= 21 &&
            			house.getHandTotal() <= 21) {
            		p.win2(frame);
            	}
            	else if (p.getHandTotal() < house.getHandTotal() && p.getHandTotal() <= 21
            			&& house.getHandTotal() <= 21) {
            		p.lose2(frame);
            	}
            	else if (p.getHandTotal() == house.getHandTotal()) {
            		p.push(frame);
            	}
                balance.setText("Balance: " + p.getMoney());
            	hit.setEnabled(false);
            	dd.setEnabled(false);
            	advice.setEnabled(false);
            	stand.setEnabled(false);
            	reset.setEnabled(true);
            	deal.setEnabled(false);
            	start.setEnabled(false);
            }
        });
        control_panel.add(dd);
        
        hit.setFont(new Font("Verdana", Font.BOLD, 20));
        hit.setPreferredSize(new Dimension(200, 50));;
        hit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	dd.setEnabled(false);
            	checkAcesForPlayer(p, totalHand);
            	p.hit(d, p, frame);
            	d.setCountArr(p.getLastCard().getCardValue());
            	checkAcesForPlayer(p, totalHand);
            	board.addCards(p.getLastCard());
            	board.forceRepaint();
            	frame.repaint();
            	
            	if (p.contains(p.getHand())) {
            		checkAcesForPlayer(p, totalHand);
            	}
            	else {
            		totalHand.setText("Total Hand: " + p.getHandTotal());
            	}
            	checkAcesForPlayer(p, totalHand);
            	if (p.getHandTotal() > 21) {
    				JOptionPane.showMessageDialog(frame, "You busted at " + 
    						p.getHandTotal() + "!");
    				hit.setEnabled(false);
	            	dd.setEnabled(false);
	            	stand.setEnabled(false);
	            	deal.setEnabled(false);
	            	advice.setEnabled(false);
	            	start.setEnabled(false);
	            	reset.setEnabled(true);
    				p.lose(frame);
    				balance.setText("Balance: " + p.getMoney());
    			}
            }
        });
        control_panel.add(hit);
        
        start.setFont(new Font("Verdana", Font.BOLD, 20));
        start.setPreferredSize(new Dimension(200, 50));;
        start.setEnabled(true);
        start.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		int bet = 0;
        		reset.setEnabled(false);
        		while (bet <= 0 || bet > p.getMoney()) {
        			try {
            			bet = Integer.parseInt(JOptionPane.showInputDialog(null, 
                				"Please place your bet", "Blackjack", JOptionPane.PLAIN_MESSAGE));
            			if (bet > p.getMoney()) {
                			JOptionPane.showMessageDialog(frame,
                					"You don't have enough money for this bet!");
                		}
            		}
            		catch(Exception ex) {
            			JOptionPane.showMessageDialog(frame, "Please enter a valid bet!");
            		}
        		}
        		p.setBet(bet);
        		balance.setText("Balance: " + (p.getMoney() - p.getBet()));
        		if (p.getMoney() - p.getBet() < p.getBet()) {
        			dd.setEnabled(false);
        		}
        		deal.setEnabled(true);
        		start.setEnabled(false);
        	}
        });
        control_panel.add(start);

        // Note here that when we add an action listener to the reset button, we define it as an
        // anonymous inner class that is an instance of ActionListener with its actionPerformed()
        // method overridden. When the button is pressed, actionPerformed() will be called.
     
        deal.setFont(new Font("Verdana", Font.BOLD, 20));
        deal.setPreferredSize(new Dimension(200, 50));;
        deal.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	reset.setEnabled(false);
            	advice.setEnabled(true);
            	d.dealGame(p, house);
            	board.addDealerCards(house.getLastCard());
            	board.addCards(p.getLastCard());
            	board.addCards(p.getTwoToLastCard());
            	board.forceRepaint();
            	frame.repaint();
            	checkAcesForDealer(house, dealerHand);
            	checkAcesForPlayer(p, totalHand);
            	if (p.getHandTotalWithAce() == 21 && p.contains(p.getHand()) &&
            			house.getHandTotalWithAce() != 11) {
           			JOptionPane.showMessageDialog(frame, "BLACKJACK!");
           			p.winBlackjack(frame);
           			balance.setText("Balance: " + p.getMoney());
           			hit.setEnabled(false);
	            	dd.setEnabled(false);
	            	advice.setEnabled(false);
	            	stand.setEnabled(false);
	            	deal.setEnabled(false);
	            	start.setEnabled(false);
	            	reset.setEnabled(true);
           		}
            	else if (house.getHandTotalWithAce() == 11 && p.getHandTotalWithAce() != 21) {
            		house.hit(d, house, frame);
            		if (house.getHandTotalWithAce() == 21 && p.getHandTotalWithAce() != 21
            				&& house.getHand()[1].getCardValue2() == 10) {
            			board.addDealerCards(house.getLastCard());
            			board.forceRepaint();
                    	frame.repaint();
            			dealerHand.setText("Dealer Hand: " + (house.getHandTotal() + 10));
            			JOptionPane.showMessageDialog(frame, "Dealer has a Blackjack!");
            			p.lose(frame);
            			balance.setText("Balance: " + p.getMoney());
            			hit.setEnabled(false);
    	            	dd.setEnabled(false);
    	            	advice.setEnabled(false);
    	            	stand.setEnabled(false);
    	            	deal.setEnabled(false);
    	            	start.setEnabled(false);
    	            	reset.setEnabled(true);
            		}
            		else {
	            		stand.setEnabled(true);
	                	hit.setEnabled(true);
	                	advice.setEnabled(true);
	                	dd.setEnabled(true);
	                	deal.setEnabled(false);
            		}
            	}
            	else if (house.getHandTotalWithAce() == 21 && p.getHandTotalWithAce() == 21) {
            		p.push(frame);
            		hit.setEnabled(false);
	            	dd.setEnabled(false);
	            	advice.setEnabled(false);
	            	stand.setEnabled(false);
	            	deal.setEnabled(false);
	            	start.setEnabled(false);
	            	reset.setEnabled(true);
            	}
            	else {
            		stand.setEnabled(true);
                	hit.setEnabled(true);
                	dd.setEnabled(true);
                	advice.setEnabled(true);
                	checkAcesForPlayer(p, totalHand);
                	if (p.contains(p.getHand())) {
	            		if (p.getHandTotalWithAce() > 21) {
	            			p.setHandTotal(p.getHandTotal());
		            		totalHand.setText("Total Hand: " + p.getHandTotal());
	            		}
	            		else {
	            			checkAcesForPlayer(p, totalHand);
	            		}
	            	}
                   	deal.setEnabled(false);
                   	stand.setEnabled(true);
                	hit.setEnabled(true);
                	if (p.getMoney() < 2 * p.getBet()) {
                		dd.setEnabled(false);
                	}
                	else {
                		dd.setEnabled(true);
                	}
            	}
           	}
            
        });
        control_panel.add(deal);
        
        stand.setFont(new Font("Verdana", Font.BOLD, 20));
        stand.setPreferredSize(new Dimension(200, 50));;
	        stand.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	            	checkAcesForPlayer(p, totalHand);
	            	checkAcesForDealer(house, dealerHand);
	            	while (house.getHandTotal() <= 16) {
	            		checkAcesForDealer(house, dealerHand);
	            		house.hit(d, house, frame);
	            		d.setCountArr(p.getLastCard().getCardValue());
	            		board.addDealerCards(house.getLastCard());
	            		board.forceRepaint();
	                	frame.repaint();
	            		if (house.contains(house.getHand())) {
	                		dealerHand.setText("Dealer Hand: " + house.getHandTotal() + " / " + 
	                				house.getHandTotalWithAce());
	                	}
	                	else {
	                		dealerHand.setText("Dealer Hand: " + house.getHandTotal());
	                	}
	            		if (house.getHandTotalWithAce() > 21) {
	            			dealerHand.setText("Dealer Hand: " + house.getHandTotal());
	            		}
	            		else if (house.getHandTotal() > 21) {
	            			dealerHand.setText("Dealer Hand: " + house.getHandTotalWithAce());
	            		}
	            	}
	            	checkAcesForDealer(house, dealerHand);
	            	checkAcesForPlayer(p, totalHand);
	            	getWinner(dealerHand, p, house, d, frame);
	            	balance.setText("Balance: " + p.getMoney());
	            	hit.setEnabled(false);
	            	dd.setEnabled(false);
	            	advice.setEnabled(false);
	            	reset.setEnabled(true);
	            	stand.setEnabled(false);
	            	deal.setEnabled(false);
	            	start.setEnabled(false);
	            }
        });
        control_panel.add(stand);
        
        reset.setFont(new Font("Verdana", Font.BOLD, 20));
        reset.setPreferredSize(new Dimension(200, 50));;
        reset.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	if (p.getMoney() <= 0 ) {
        			JOptionPane.showMessageDialog(frame, "You Lose! Game Exiting.");
        			frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
        		}
            	board.reset(p, house);
            	dd.setEnabled(false);
            	advice.setEnabled(false);
            	stand.setEnabled(false);
            	reset.setEnabled(false);
            	hit.setEnabled(false);
            	deal.setEnabled(false);
            	board.resetKeyboardActions();
            	totalHand.setText("Total Hand: 0");
            	dealerHand.setText("Dealer Hand: 0");
            	start.setEnabled(true);
            	board.setTable();
            	board.repaint();
            	frame.repaint();
            	
            }
        });
        control_panel.add(reset);

        // Put the frame on the screen
        //frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        
    }
    
    public void getWinner(JLabel dealerHand, Player p, Player house, Deck d, JFrame frame) {
    	if (!p.contains(p.getHand())) {
    		if (house.getHandTotal() > 21) {				
    			JOptionPane.showMessageDialog(frame, "The dealer busted at " 
    					+ house.getHandTotal() + "!");
    			p.win(frame);
    		}
        	else if (p.getHandTotal() > house.getHandTotal() && p.getHandTotal() <= 21 &&
        			house.getHandTotal() <= 21) {
        		p.win(frame);
        	}
        	else if (p.getHandTotal() < house.getHandTotal() && p.getHandTotal() <= 21
        			&& house.getHandTotal() <= 21) {
        		p.lose(frame);
        	}
        	else if (p.getHandTotal() == house.getHandTotal()) {
        		p.push(frame);
        	}
    	}
    	else {
    		if (house.getHandTotal() > 21) {				
    			JOptionPane.showMessageDialog(frame, "The dealer busted at " 
    					+ house.getHandTotal() + "!");
    			p.win(frame);
    		}
    		else if (p.getHandTotalWithAce() > house.getHandTotal() && 
    				p.getHandTotalWithAce() <= 21) {
    			p.win(frame);
    		}
    		else if (p.getHandTotalWithAce() < house.getHandTotal() 
        			&& house.getHandTotalWithAce() <= 21) {
        		p.lose(frame);
        	}
    		else if (p.getHandTotalWithAce() == house.getHandTotal()) {
        		p.push(frame);
        	}
    		else if (p.getHandTotalWithAce() < house.getHandTotalWithAce() ||
    				p.getHandTotal() < house.getHandTotal()) {
    			p.lose(frame);
    		}
    		else {
    			p.win(frame);
    		}
    		
    	}
    }
    
    public void checkAcesForPlayer(Player p, JLabel totalHand) {
    	if (p.contains(p.getHand())) {
    		if (p.getHandTotalWithAce() <= 21) {
    			p.setHandTotal(p.getHandTotal() + 10);
    		}
	    	if (p.getHandTotal() <= 21 && p.getHandTotalWithAce() <= 21) {
	    		totalHand.setText("Total Hand: " + 
	        			p.getHandTotal() + " / " + p.getHandTotalWithAce());
	    		p.setHandTotal(Math.max(p.getHandTotal(), p.getHandTotalWithAce()));
	    	}
	    	else if (p.getHandTotal() > 21 && p.getHandTotalWithAce() <= 21) {
	    		totalHand.setText("Total Hand: " + p.getHandTotalWithAce());
	    		p.setHandTotal(p.getHandTotalWithAce());
	    	}
	    	else if (p.getHandTotal() <= 21 && p.getHandTotalWithAce() > 21) {
	    		totalHand.setText("Total Hand: " + p.getHandTotal());
	    		p.setHandTotal(p.getHandTotal());
	    	}
	    	else if (p.getHandTotal() > 21 && p.getHandTotalWithAce() > 21) {
	    		totalHand.setText("Total Hand: " + 
	        			Math.min(p.getHandTotal(), p.getHandTotalWithAce()));
	    		p.setHandTotal(Math.min(p.getHandTotal(), p.getHandTotalWithAce()));
	    	}
	    }
    	else {
    		totalHand.setText("Total Hand: " + p.getHandTotal());
    		p.setHandTotal(p.getHandTotal());
    	}
    }
    
    public void checkAcesForDealer(Player house, JLabel dealerHand) {
    	if (house.contains(house.getHand())) {
	    	if (house.getHandTotal() <= 21 && house.getHandTotalWithAce() <= 21) {
	    		dealerHand.setText("Dealer Hand: " + 
	        			house.getHandTotal() + " / " + house.getHandTotalWithAce());
	    		house.setHandTotal(Math.max(house.getHandTotal(), house.getHandTotalWithAce()));
	    	}
	    	else if (house.getHandTotal() > 21 && house.getHandTotalWithAce() <= 21) {
	    		dealerHand.setText("Dealer Hand: " + house.getHandTotalWithAce());
	    		house.setHandTotal(house.getHandTotalWithAce());
	    	}
	    	else if (house.getHandTotal() <= 21 && house.getHandTotalWithAce() > 21) {
	    		dealerHand.setText("Dealer Hand: " + house.getHandTotal());
	    		house.setHandTotal(house.getHandTotal());
	    	}
	    	else if (house.getHandTotal() > 21 && house.getHandTotalWithAce() > 21) {
	    		dealerHand.setText("Dealer Hand: " + 
	        			Math.min(house.getHandTotal(), house.getHandTotalWithAce()));
	    		house.setHandTotal(Math.min(house.getHandTotal(), house.getHandTotalWithAce()));
	    	}
    	}
    	else {
    		dealerHand.setText("Dealer Hand: " + house.getHandTotal());
    		house.setHandTotal(house.getHandTotal());
    	}
    }

    /**
     * Main method run to start and run the game. Initializes the GUI elements specified in Game and
     * runs it. IMPORTANT: Do NOT delete! You MUST include this in your final submission.
     */
    public static void main(String[] args) {
    	UIManager.put("OptionPane.messageFont", new Font("Verdana", Font.BOLD, 30));
        SwingUtilities.invokeLater(new Game());
    }
}