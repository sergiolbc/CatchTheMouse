/********************************************************************************
Sergio Gonzalez

BobBurger.java
9/22/2018

Create an application for Bob's Burger that allows a user to order food. The 
base price for a burger is $5. The base price for a double burger is $7. Include 
mutually exclusive check boxes for lettuce, cheese, and pickles. Provide a 
selection for a drink with the following options: small, medium, and large. 
Then compute the total. Save the file as BobBurger.java.
********************************************************************************/

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class BobBurger extends JFrame implements ActionListener, ItemListener
{
   //food prices
   final double BURGER_SINGLE = 5;
   final double BURGER_DOUBLE = 7;
   final double LETTUCE = .05;
   final double CHEESE = .25;
   final double PICKLES = .1;
   final double SMALL_DRINK = 1.75;
   final double MEDIUM_DRINK = 2.25;
   final double LARGE_DRINK = 2.75;
   
   //other variables needed
   double total = 0;
   double burgerDrink = 0; //separate in case of order change
   double addOns = 0;
   
   //burger selection
   String[] burgers = {"Burger: $5.00", "Double: $7.00"};
   double[] burgersPrice = {BURGER_SINGLE, BURGER_DOUBLE};
   JComboBox burgerMenu = new JComboBox(burgers);
   
   //condiments 
   JLabel condimentsLabel = new JLabel("Choose Condiments");
   JCheckBox cheese = new JCheckBox("Cheese: $" + CHEESE, false);
   JCheckBox pickles = new JCheckBox("Pickle: $" + PICKLES, false);
   JCheckBox lettuce = new JCheckBox("Lettuce: $" + LETTUCE, false);
   
   //drinks selection
   String[] drinks = {"No Drink","Small: $1.75", "Medium: $2.25", "Large: $2.75"};
   double[] drinksPrice = {0, SMALL_DRINK, MEDIUM_DRINK, LARGE_DRINK};
   JComboBox drinkMenu = new JComboBox(drinks);
   
   //DISPLAYS
   JLabel menuLabel = new JLabel("Select your meal: ");
   JLabel priceDisplay = new JLabel("Price: ");
   JTextField totalField = new JTextField(5);
   
   public BobBurger()
   {
      //create the window
      super("Bob's Burgers");
      setSize(270, 250);
      setVisible(true);
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      setLayout(new FlowLayout());
      
      //display heading
      add(menuLabel);
      menuLabel.setFont(new Font("Arial",Font.BOLD, 16));
      
      //burger
      add(burgerMenu);
      
      //condiments
      add(cheese);
      add(pickles);
      add(lettuce);
      
      //drinks
      add(drinkMenu);
      
      //price
      add(priceDisplay);
      add(totalField);
      
      //add to window
      burgerMenu.addActionListener(this);
      drinkMenu.addActionListener(this);
      
      cheese.addItemListener(this);
      pickles.addItemListener(this);
      lettuce.addItemListener(this);
   
   }
   
   
   @Override
   public void actionPerformed(ActionEvent e)
   {
      //ADD TO TOTAL FOR SELECTION
      burgerDrink = (burgersPrice[burgerMenu.getSelectedIndex()]) + (drinksPrice[drinkMenu.getSelectedIndex()]);
      
      total = burgerDrink + addOns;
      totalField.setText("$" + total);
   
   }
   
   @Override
   public void itemStateChanged(ItemEvent event)
   {
      Object source = event.getSource();
      int select = event.getStateChange();
      
      //ADD CHEESE PLS
      if (source == cheese)
      {
         if (select == ItemEvent.SELECTED)
            addOns += CHEESE;
            
         else
            addOns -= CHEESE;
            
      }
      
      //PICKLES YUMMM
      else if (source == pickles)
      {
         if (select == ItemEvent.SELECTED)
            addOns += PICKLES;
            
         else
            addOns -= PICKLES;
            
      }
      
      //LETTUCE ASK YOU A QUESTION
      else if (source == lettuce)
      {
         if (select == ItemEvent.SELECTED)
            addOns += LETTUCE;
            
         else
            addOns -= LETTUCE;
      }
      
      //RESUM THE TOTAL
      total = burgerDrink + addOns;
      totalField.setText("$" + total);
   
   }
   
   public static void main(String[]args)
   {
      BobBurger myFrame = new BobBurger();
   
   }
   
}