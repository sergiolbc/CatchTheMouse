/******************************************************************************
Sergio Gonzalez
Calculater.java
8/03/2018

Create a class Calculator.java that has two JTextFields, multiply JButton, 
divide JButton, equals JButton, and a JLabel that present the user with the 
results.
******************************************************************************/

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Calculator extends JFrame implements ActionListener
{
   //variables
   final int WIDTH = 425;
   final int HEIGHT  = 200;
   String operation = "";
   double answer;
   
   //questions
   JLabel num1 = new JLabel("First num: ");
   JTextField num1Answer = new JTextField(10);
   JLabel num2 = new JLabel("Second num: ");
   JTextField num2Answer = new JTextField(10);
   
   //layout 
   Font bigFont = new Font("Arial", Font.BOLD, 16);
   JButton enterB = new JButton ("Enter");
   JButton multB = new JButton ("x");
   JButton divB = new JButton ("/");
   JLabel ans = new JLabel("");
   
   public Calculator()
   {
      //create caluclator
      super("Calculator");
      setSize(WIDTH, HEIGHT);
      setLayout(new FlowLayout());
      
      add(num1);
      add(num1Answer);
      add(num2);
      add(num2Answer);
      add(multB);
      add(divB);
      add(enterB);
      add(ans);
      ans.setFont(bigFont);
      
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      setVisible(true);
      
      multB.addActionListener(this);
      divB.addActionListener(this);
      enterB.addActionListener(this);
      
   }
   
   @Override
   
   public void actionPerformed(ActionEvent e)
   {
      String operationCheck = e.getActionCommand();
      
      if(operationCheck.equals("x"))
      {
         operation = "x";
         answer = Double.parseDouble(num1Answer.getText()) * Double.parseDouble(num2Answer.getText());
      }
         
      else if (operationCheck.equals("/"))
      {
         operation = "/";
         answer = Double.parseDouble(num1Answer.getText()) / Double.parseDouble(num2Answer.getText());
         
      }
      
      else 
         ans.setText(num1Answer.getText() + " " + operation + " " + num2Answer.getText() + " = " + answer);
      
   }
   
   public static void main(String[]args)
   { 
      new Calculator();
   
   }
   
}