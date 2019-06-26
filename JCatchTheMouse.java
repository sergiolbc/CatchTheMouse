/**********************************************************************************************************
Sergio Gonzalez
CIS263AA - Java Programming: Level II
Class #31804
ser2149064

JCatchTheMouse.java
11/16/2018

Create a game that helps new mouse users improve their hand-eye coordination. Within a JFrame, 
display an array of 48 JPanels in a GridLayout using eight rows and six columns. Randomly display an 
X on one of the panels. When the user clicks the correct panel (the one displaying the X), remove 
the X and display it on a different panel. After the user has successfully "hit" the correct panel 
10 times, display a congratulatory message that invludes the user's percentage (hits divided by 
clicks.) Save the file as JCatchTheMouse.java

Chapter 15, Game Zone 2 (Part A only) on page 856:
• Use 48 JButtons instead of an array of 48 JPanels.
• Once the user clicks on the tenth correct button, display your name, course, section number,
and MEID by using a JOptionPane message dialog.
**********************************************************************************************************/

import java.util.Random;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.Color;
import javax.swing.Timer;
import java.text.DecimalFormat;

public class JCatchTheMouse extends JFrame implements ActionListener
{
   //create the layout buttons   
   private final int ROWS = 8;
   private final int COLS = 6;
   private final int NUMS = ROWS * COLS;
   private final int GAP = 3;
   private JPanel pane = new JPanel(new GridLayout(ROWS, COLS, GAP, GAP));
   
   JButton[] buttons = new JButton[NUMS];
   
   //delcare variables
   Random random = new Random();
   Timer timer = new Timer(2000, this); //make it fun, user has 1 second to "hit" mouse
   
   int mouses = 0;
   int correct = 0;
   int randomNum = 0;
   int hits = 0;
   
   public JCatchTheMouse()
   {
      //window features
      super("Catch The Mouse");
      setSize(600, 800);
      setVisible(true);
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      
      setLayout(new BorderLayout());
      add(pane, BorderLayout.CENTER);
      
      //for loop to create the buttons
      for(int x = 0; x < NUMS; x++)
      {
         buttons[x] = new JButton();
         pane.add(buttons[x]);
         
         buttons[x].addActionListener(this);

      
      } 
      
      //when the game opens there is already a mouse on the board
      randomButton();
        
   }
      
   @Override
   public void actionPerformed(ActionEvent event)
   {       
      timer.stop(); //stops the timer so it doesn't immediately change
      
      if(event.getSource() == buttons[randomNum])
      {
         correct++;
         hits++;         
         
      }
      
      else
         correct = 0;
      
      //reset the button, even if a miss  
      buttons[randomNum].setText("");
      buttons[randomNum].setBackground(null);
               
      if (correct == 10)
      {
         double percent = ((double) hits / (double) mouses) * 100; 
         
         //new decimal format so we only get two decimal places
         JOptionPane.showMessageDialog(null, "Nice aim, cowboy! Your aim precentage was " + new DecimalFormat("0.00").format(percent) + "%!");
         JOptionPane.showMessageDialog(null, "Sergio Gonzalez, CIS263AA - Java Programming: Level II #31804, SER2149064");
         
         //resets the game if user keeps clicking
         correct = 0;
         hits = 0;
         mouses = 0;
      
      }
      
      else
         randomButton();

   
   }
   
   public void randomButton()
   {
      randomNum = random.nextInt(NUMS); //we don't do +1 since we want to include 0
      
      buttons[randomNum].setText("X");
      buttons[randomNum].setBackground(Color.RED);
      timer.start(); //resets everytime a new button
      
      mouses++;
   
   }
    
   public static void main(String[]args)
   {
      JCatchTheMouse myFrame = new JCatchTheMouse();
   
   }

}