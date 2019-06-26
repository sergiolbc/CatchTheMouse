/**********************************************************************************************************
Sergio Gonzalez
CIS263AA - Java Programming: Level II
Class #31804
ser2149064

JLuckySeven.java
11/17/2018

Create an application that plays a card game named Lucky Seven. In real life, the
game can be played with seven cards, each containing a number from 1 through 7,
that are shuffled and dealt number-side down. To start the game, a player turns
over any card. The exposed number on the card determines the position (reading
from left to right) of the next card that must be turned over. For example, if the
player turns over the first card and its number is 7, the next card turned must be
the seventh card (counting from left to right). If the player turns over a card whose
number denotes a position that was already turned, the player loses the game. If the
player succeeds in turning over all seven cards, the player wins.

Instead of cards, you will use seven buttons labeled 1 through 7 from left to right.
Randomly associate one of the seven values 1 through 7 with each button. (In other
words, the associated value might or might not be equivalent to the button’s
labeled value.) When the player clicks a button, reveal the associated hidden
value. If the value represents the position of a button already clicked, the player
loses. If the revealed number represents an available button, force the user to click
it; that is, do not take any action until the user clicks the correct button. After a
player clicks a button, remove the button from play. (After you remove a button,
you can call repaint() to ensure that the image of the button is removed.)

For example, a player might click Button 7, revealing a 4. Then the player clicks
Button 4, revealing a 2. Then the player clicks Button 2, revealing a 7. The player
loses because Button 7 was already used. Save the game as JLuckySeven.java.

Chapter 17, Game Zone 2 on page 917:
• Keep a tally that shows the number of games that the player has won, tied, or lost.
• Use the drawString() method to display your name, course, section number, and MEID in the
lower left-hand corner of the applet.
**********************************************************************************************************/

import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.Color;

public class JLuckySeven extends JFrame implements ActionListener
{
   //variables
   JButton currentCard = new JButton();
   int numberText = 0;
   int nextCardInt = 0;
   int cardCount = 0;
   int wins = 0;
   int losses = 0;

   //create the 7 card deck
   private final int MAX = 7;
   private JButton[] deck = new JButton[MAX];
   private ArrayList<Integer> numbers = new ArrayList<Integer>(); 
   
   private Container con = getContentPane();
   
   //visual guidance for user
   private JLabel nextCardDisplay = new JLabel("");
   
   private JLabel record = new JLabel("Wins: " + wins + " Losses: " + losses);
   
   public JLuckySeven()
   {
      super("Lucky Seven");
      setSize(600, 400);
      setVisible(true);
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      con.setLayout(new FlowLayout());
      
      for(int x = 0; x < MAX; x++)
      {
         numbers.add(x + 1);
         deck[x] = new JButton(Integer.toString(x + 1));
         deck[x].setPreferredSize(new Dimension(60, 100));
         con.add(deck[x], BorderLayout.NORTH);
         
         deck[x].addActionListener(this);
      
      }
      
      Collections.shuffle(numbers);
      
      //add our labels to the panel
      con.add(record);
      con.add(nextCardDisplay);
         
   }
   
   @Override
   public void paint(Graphics g)
   {
      super.paint(g);
      
      //make it a little fancy
      Font myFont = new Font("Arial", Font.BOLD, 14);
      g.setColor(Color.BLUE);
      g.setFont(myFont);
      
      g.drawString("Sergio Gonzalez, CIS263AA - Java Programming: Level II #31804, SER2149064", 30, 275);
   
   }
   
   @Override
   public void actionPerformed(ActionEvent event)
   {  
      currentCard = (JButton) event.getSource();
         
      //first inital card
      if(cardCount == 0)
      {
         numberText = Integer.parseInt(currentCard.getText());
         cardCount++;
         disableCard();
         
      }
      
      //rest of the game to ensure follows rules
      else
      {
         if(Integer.parseInt(currentCard.getText()) == nextCardInt)
         {
            numberText = Integer.parseInt(currentCard.getText());
            cardCount++;
            disableCard();
            
         }
      
      }  
      
      //CHECK IF WINNER
      if(cardCount == 7)
      {
         JOptionPane.showMessageDialog(null, "You Win!");
         wins++;
         resetGame();
         
      }
      
      //CHECK IF LOSS   
      else if(deck[nextCardInt - 1].isEnabled() == false) 
      {  
         JOptionPane.showMessageDialog(null, "You lose.");
         losses++;
         resetGame();
         
      }
      
      //ELSE KEEP PLAYING
   
   }
   
   //DISABLES SELECTED CARD && SETS NEXT
   public void disableCard()
   {
      nextCardDisplay.setText("The card #" + numberText + " reveals the number " + numbers.get(numberText - 1));
      nextCardInt = numbers.get(numberText - 1);
      
      currentCard.setEnabled(false);
      currentCard.setBackground(null);
      currentCard.setText(Integer.toString(numbers.get(numberText - 1)));
      
      if(deck[nextCardInt - 1].isEnabled() == true) //easier to see which button to click next
         deck[nextCardInt - 1].setBackground(Color.CYAN);
         
      else if (deck[nextCardInt - 1].isEnabled() == false && cardCount != 7) //so at the end if user won, there isn't a random red tile
         deck[nextCardInt - 1].setBackground(Color.RED);
   
   }
   
   // RESET EVERYTHING, SHUFFLE CARDS, && KEEPS WIN/LOSS RECORD
   public void resetGame()
   {
      for(int x = 0; x < MAX; x++)
      {
         deck[x].setEnabled(true);
         deck[x].setText(Integer.toString(x + 1));
         deck[x].setBackground(null);
      
      }
      
      Collections.shuffle(numbers);
      nextCardDisplay.setText("");
      cardCount = 0;
      
      record.setText("Wins: " + wins + " Losses: " + losses);
   
   }
   
   public static void main(String[]args)
   {
      JLuckySeven myFrame = new JLuckySeven();
   
   }

}