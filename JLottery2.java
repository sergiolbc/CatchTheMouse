/***************************************************************************************************
Sergio Gonzalez
CIS263AA - Java Programming: Level II
Class #31804
ser2149064

JLottery2.java
11/15/2018

In Chapter 5, you created a lottery game application. Create a similar game using checkboxes. For 
this game, generate six check boxes to play the game. (Do not allow the user to choose more than 
six boxes.) Afterthe player has choseen six numbers, display the randomly selected numbers, the 
player's numbers, and the amount of money the user has won as follows:

Three matches = $100
Four matches = $10,000
Five matches = $50,000
Six matches = $1,000,000
Zero, one, or two matches = $0

Chapter 14, Game Zone 2 on page 788:
• Add a menu bar to the program with a File menu.
• In the File menu, add a submenu (JMenuItem) called About.
• When the user clicks on the About menu item, display a JOptionPane message dialog that
  contains your name, your course, the section number, and MEID
***************************************************************************************************/

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;
import java.util.Arrays; 

public class JLottery2 extends JFrame implements ActionListener
{
   //create the boxes  
   private final int NUMS = 30;
   JCheckBox[] boxes = new JCheckBox[NUMS];
   
   JPanel pane = new JPanel();
   
   //panel displays
   JLabel title = new JLabel("Choose your lucky 6: ");
   JButton results = new JButton("Results");
   
   //menubar
   JMenuBar mainBar = new JMenuBar();
   JMenu file = new JMenu("File");
   JMenuItem about = new JMenuItem("About");
   
   //declare variables
   Random random = new Random();
   private final int MAXSIX = 6; //so we don't hard code the num 
   int[] numsPicked = new int[MAXSIX];
   int[] winningNumbers = new int[MAXSIX];
   String numString = "";
   String winString = "";
   int selected = 0; //temp number constantly being reset through code, instead of creating more temp variables
   int matches = 0;
   int money = 0;
   
   
   public JLottery2()
   {
      //window features
      super("Lottery");
      setSize(515, 260);
      setVisible(true);
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      
      setLayout(new BorderLayout());
      add(pane);
      pane.add(title);
      
      //add menu components
      setJMenuBar(mainBar);
      mainBar.add(file);
      file.add(about);
      about.addActionListener(this);
            
      //for loop to create the boxes
      for(int x = 0; x < NUMS; x++)
      {
         boxes[x] = new JCheckBox(Integer.toString(x + 1), false);
         pane.add(boxes[x]);
      
      }
      
      //add results after the boxes
      pane.add(results);
      
      //ACTION LISTENERS
      results.addActionListener(this);
      //no action listeners need for checkboxees, will count once results is selected
         
   }
   
   @Override
   public void actionPerformed(ActionEvent e)
   {
      // CLICKS RESULTS         
      if (e.getSource() == results)
      {
         selected = 0;
         
         for(int x = 0; x < NUMS; x++)
         {
            if(boxes[x].isSelected())
               selected++;
         
         }
      
         //checks if 6 choices
         if(selected > MAXSIX)
            JOptionPane.showMessageDialog(null, "You entered MORE than " + MAXSIX + " numbers. Correct your mistakes.");
         
         //checks less than 6  
         else if(selected < MAXSIX)
            JOptionPane.showMessageDialog(null, "You entered LESS than " + MAXSIX + " numbers. Correct your mistakes.");
         
         //only runs if correct amount entered  
         else
         {
            //PLACE SELECTED NUMBERS IN ARRAY
            selected = 0; //reusing a previous variable that wasn't needed again
            for(int x = 0; x < NUMS; x++)
            {
               if(boxes[x].isSelected())
               {
                  numsPicked[selected] = x + 1;
                  numString += ((x + 1) + " ");
                  selected++;
                  
               }
                  
            }
                            
            //GENERATE THE WINNING NUMBERS INTO AN ARRAY
            boolean duplicate;
            selected = 0; //reusing a previous variable that wasn't needed again
            int randomNum;
                  
            do
            {
               duplicate = false;

               randomNum = random.nextInt(NUMS) + 1;

               for(int x = 0; x < winningNumbers.length; x++)
               {
                  if(winningNumbers[x] == randomNum)
                     duplicate = true;
               
               }
               
               if(duplicate == false) //only adds if not duplicate
               {
                  winningNumbers[selected] = randomNum; 
                  selected++;
                  
               }          
            
            } while (selected < MAXSIX); 
            
            //put the winning numbers in numerical order
            Arrays.sort(winningNumbers);
            
            //COMPARE USERS NUMBERS AND WINNERS
            for (int x = 0; x < MAXSIX; x++)
            {  
               winString += (winningNumbers[x] + " "); //create the winning numbers string
               for (int y = 0; y < MAXSIX; y++)
               {  
                  if(numsPicked[x] == winningNumbers[y])
                     matches++;
               
               }
            }

            //deecide winnings ammount based on matchees
            switch(matches)
            {
               case 3:
                  money = 100;
                  break;
                  
               case 4: 
                  money = 10000;
                  break;
                  
               case 5:
                  money = 50000;
                  break;
                  
               case 6:
                  money = 1000000;
                  break;
                  
               //money is already to 0 so everything else is already set
            
            }
            
            //PRINTS THE RESULTS 
            JOptionPane.showMessageDialog(null, "<html><body>You chose: " + numString + "<br>Winning numbers: " + winString + "<br>Matches: " + matches + "<br>Earnings $" + money + ".</body></html>");
            clearBoard();

         }
            
      }
      
      //only other action listener button is about
      else
         JOptionPane.showMessageDialog(null, "Sergio Gonzalez, CIS263AA - Java Programming: Level II #31804, SER2149064");
   
   } 
   
   //deselects checkboxes and resets variables
   public void clearBoard()
   {
      for(int x = 0; x < NUMS; x++)
      {
         boxes[x].setSelected(false);
      
      }
      
      numString = "";
      winString = "";
      winningNumbers = new int[MAXSIX];
      numsPicked = new int[MAXSIX];
      matches = 0;

   }
   
   public static void main(String[]args)
   {
      JLottery2 myFrame = new JLottery2();
   
   }

}