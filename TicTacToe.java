/*************************************************************************************
Sergio Gonzalez

TicTacToe.java
10/4/2018

Tic-Tac-Toe is a game played on a grid that contains three rows by three columns. 
When a player gets three of their values across, down, or diagonal, they have won 
the game. Create a java version of this game by using Gridlayout that is constructed 
with three columns and three rows. The user will go first. When the user selects a 
cell, put an "x" in that cell. Next, randomly add a 0 to a cell to mock the computer 
playing. Call this class TicTacToe.java.
*************************************************************************************/

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class TicTacToe extends JFrame implements ActionListener
{
   //create the buttons   
   private final int ROWS = 3;
   private final int COLS = 3;
   private final int NUM = ROWS * COLS;
   private final int GAP = 4;
   private JPanel pane = new JPanel(new GridLayout(ROWS, COLS, GAP, GAP));
      
   JButton[] buttons = new JButton[NUM];
   
   //random generator
   Random rand = new Random();
   
   //false for empty
   private char[] gridInput = {'e', 'e', 'e', 'e', 'e', 'e', 'e', 'e', 'e'}; //e for empty
   private int compMove = -1;
   private boolean again = true;
   private int userAttempts = 0;
   private boolean playerWin = false;
   private boolean compWin = false;
   private char playerMark = 'X';
   private char compMark = 'O';
   
   public TicTacToe()
   {
      //window features
      super("TicTacToe");
      setSize(500, 500);
      setVisible(true);
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      
      setLayout(new BorderLayout());
      add(pane, BorderLayout.CENTER);
      
      //for loop to create the buttons
      for(int x = 0; x < NUM; x++)
      {
         buttons[x] = new JButton();
         pane.add(buttons[x]);
         
         buttons[x].addActionListener(this);
         
      }
            
   }
   
   @Override
      public void actionPerformed(ActionEvent event)
      {
         //finds the button that was clicked
         int index = 0;
         
         for(int x = 0; x < NUM; x++)
         {
            if(buttons[x] == (JButton)event.getSource())
               index = x;

         }
         
         //user select
         if(gridInput[index] == 'e')
         {
            gridInput[index] = 'X';
            buttons[index].setText("X");
            userAttempts++;
                                    
         }
         
         //did player win?
         playerWin = checkWinner(playerMark);
         if(playerWin == true)
         {
            JOptionPane.showMessageDialog(null, "Winner Winner Chicken Dinner!");
            System.exit(0);
            
         }
         
         //once board filled
         if(userAttempts == 5)
         {
            JOptionPane.showMessageDialog(null, "Looks like we have a draw.");
            System.exit(0);
         
         }
         
         //random computer selection
         do
         {
            compMove = rand.nextInt(9);
                    
         
         } while (gridInput[compMove] != 'e');
         
         buttons[compMove].setText("O");
         gridInput[compMove] = 'O';
         
         //did comp win?
         compWin = checkWinner(compMark);
         if(compWin == true)
         {
            JOptionPane.showMessageDialog(null, "Haha! You lost!");
            System.exit(0);
         
         }
      }
      
      public boolean checkWinner(char playerChar)
      {
         char mark = playerChar;
         
         //checks top row
         if (gridInput[0] == mark && gridInput[1] == mark && gridInput[2] == mark)
            return true;
         
         //second row
         else if(gridInput[3] == mark && gridInput[4] == mark && gridInput[5] == mark)
            return true;
            
         //third row
         else if(gridInput[6] == mark && gridInput[7] == mark && gridInput[8] == mark)
            return true;
            
         //first column
         else if(gridInput[0] == mark && gridInput[3] == mark && gridInput[6] == mark)
            return true;
            
         //second column
         else if(gridInput[1] == mark && gridInput[4] == mark && gridInput[7] == mark)
            return true;
            
         //second column
         else if(gridInput[2] == mark && gridInput[5] == mark && gridInput[8] == mark)
            return true;   
            
         //top left diagonal
         else if(gridInput[0] == mark && gridInput[4] == mark && gridInput[8] == mark)
            return true;
            
         //top right diagonal
         else if(gridInput[2] == mark && gridInput[4] == mark && gridInput[6] == mark)
            return true;
            
         else
            return false;      
      
      }
      
    
   public static void main(String[]args)
   {
      TicTacToe myFrame = new TicTacToe();
   
   }

}