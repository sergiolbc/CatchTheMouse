/*************************************************************************************
Sergio Gonzalez

Blackjack.java
10/12/2018

Blackjack is a card game where the goal is to reach a score of 21. Create a java 
version of this game with the following requirements. Extend the JPanel class and 
create an array of 52 cards. Add four sets of numbers from 1 to 10. Use J for Jack, 
Q for queen, K for king, and A for ace. Jack through king will have a value of 10, 
and the ace will have a value of 11. Deal two cards to the user and two cards to the 
computer. Do not show the value of the computer's first two cards to the user. Add 
buttons Hit, Stay, and Reset. If the user selects the "Hit" button, randomly select 
one of the cards from the array, and give it to the user. (After selecting a card, 
do not reuse this index during the rest of the game.) If the dealer has less than 21, 
"hit" the dealer, too. When the user selects the "Stay" button, add up the card 
values. The winner is the person who is closest to 21 without going over. Name the 
class Blackjack.java.
*************************************************************************************/

import java.util.*;  
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Blackjack extends JPanel implements ActionListener
{
   //create the buttons   
   private JButton hit = new JButton("Hit");
   private JButton stay = new JButton("Stay");
   private JButton reset = new JButton("Reset");
   
   private String[] deck = new String[56]; //56 since include both 1 and Ace as separate cards
   private String[] num = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King", "Ace"};
   
   //create players hands
   private ArrayList<String> playerHand = new ArrayList<String>();
   private ArrayList<String> dealerHand = new ArrayList<String>();
   
   //variables
   private int cardsDealt = 0;
   private boolean userStay = false;
      
   public Blackjack()
   {  
      //call function to create deck, shuffle and deal
      createDeck();
      
      //add the buttons
      add(hit, BorderLayout.NORTH);
      add(stay, BorderLayout.NORTH);
      add(reset, BorderLayout.NORTH);
         
      hit.addActionListener(this);
      stay.addActionListener(this);
      reset.addActionListener(this);
      
         
   }
   
   public void actionPerformed(ActionEvent e)
   {
      Object source = e.getSource();
      
      if(source == hit)
      {        
         cardsDealt++; //add one first to make sure goes to next index when adding to hand
         playerHand.add(deck[cardsDealt]);
                       
      }
      
      else if(source == stay)
      {
         userStay = true;
                    
         while(handValue(dealerHand) < 21) //"If the dealer has less than 21, 'hit' the dealer, too." <--- that's the rule I'd like to play with when in Vegas haha!
         {            
            cardsDealt++; //add one first to make sure goes to next index when adding to hand
            dealerHand.add(deck[cardsDealt]);
            
         }
         
      }
      
      else
      {
         //clear the current hands
         playerHand.clear();
         dealerHand.clear();
         
         //recreate deck, shuffle, and deal
         createDeck();
         
         //hides dealer's new hand
         userStay = false;
      
      }
      
      //repain the panel after selection
      repaint();
      
      //check if either wins
      checkPlayerWinLoss();
      checkDealerWinLoss(); 
            
   }
   
   @Override
   public void paintComponent(Graphics g)
   {     
      super.paintComponent(g);
      
       g.drawString("Your hand: " + handValue(playerHand), 20, 55);
      
      for(int x = 0; x < playerHand.size(); x++)
      {
         g.drawString(playerHand.get(x), 20, (80 + (x * 15))); //x * 15 so each time a card is listed, it's not printed over
      
      }
      
      //only until the user hits stay will this display
      if(userStay == true)
      {
         g.drawString("Dealer hand: " + handValue(dealerHand), 200, 55);
         
         for(int x = 0; x < dealerHand.size(); x++)
         {
            g.drawString(dealerHand.get(x), 200, (80 + (x * 15))); //x * 15 so each time a card is listed, it's not printed over
         
         }
         
      }
   
   }
      
   //FUNCTION TO CREATE DECK AND DEAL FIRST CARDS
   public void createDeck()
   {
      //create deck
      for (int x = 0; x < num.length; x++)
      {
         for (int y = 0; y < 4; y++) //4 for number of suits
         {
             deck[(4 * x) + y] = num[x];
             
         }
      }
      
      // shuffle
      for (int x = 0; x < deck.length; x++) 
      {
         int r = x + (int) (Math.random() * (deck.length - x));
         String temp = deck[r];
         deck[r] = deck[x];
         deck[x] = temp;
         
      }
      
      //deals out hands
      playerHand.add(deck[0]);
      playerHand.add(deck[1]);
      dealerHand.add(deck[2]);
      dealerHand.add(deck[3]);
      cardsDealt = 4;
   
   }
   
   //FUNCTION TO DECLARE HAND VALUE
   public int handValue(ArrayList<String> hand)
   {
      int value = 0;
      
      for(int x = 0; x < hand.size(); x++)
      {
         switch (hand.get(x))
         {
            case "Jack":
            case "Queen":
            case "King":
               value += 10;
               break;
               
            case "Ace":
               value += 11;
               break;
               
            default:
               value += Integer.parseInt(hand.get(x));
         
         }
      
      }
      
      return value;
   
   }
   
   //CHECK HAND
   public int checkHand(ArrayList<String> hand)
   {      
      int value = handValue(hand); //calls value finctions
      int val;
        
      //1 means equals 21, -1 means greater than 21
      if (value < 21)
         val = 0;
        
      else if(value == 21)
         val = 1;
        
      else
         val = -1;
      
      return val;
   
   }
   
   //PRINT PLAYER IF WIN OR LOSS
   public void checkPlayerWinLoss()
   {
      //1 means equals 21, -1 means greater than 21
      if(checkHand(playerHand) == -1)
         JOptionPane.showMessageDialog(null, "HA! You bust! You lose.");
      
      else if (checkHand(playerHand) == 1)
         JOptionPane.showMessageDialog(null, "21! Right on, you win.");

   }
   
   //PRINT DEALER IF WIN OR LOSS
   public void checkDealerWinLoss()
   {
      //0 means less than 21, 1 means equals 21, -1 means greater than 21
      if(checkHand(dealerHand) == -1)
         JOptionPane.showMessageDialog(null, "Dealer bust. What luck! You win!");

      
      else if (checkHand(dealerHand) == 1)
         JOptionPane.showMessageDialog(null, "Dealer 21! Ha! You lose.");
   
   }
      
}