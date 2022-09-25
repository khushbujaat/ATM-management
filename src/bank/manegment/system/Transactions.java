
package bank.manegment.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Transactions extends JFrame implements ActionListener{
       
     JButton deposit, withdrawl, ministatement, pinchange, fastcash, balanceenquiry, exit;
     String pinnumber;
     Transactions(String pinnumber) {
         this.pinnumber = pinnumber;
         setLayout(null);
         
         ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/atm.jpg"));
         Image i2 = i1.getImage().getScaledInstance(770, 770, Image.SCALE_DEFAULT);
         ImageIcon i3 = new ImageIcon(i2);
         JLabel image = new JLabel(i3);
         image.setBounds(0, 0, 700, 700);
         add(image);
         
         JLabel text = new JLabel("Please select your Transaction");
         text.setBounds(150, 220, 650, 35);
         text.setForeground(Color.WHITE);
         text.setFont(new Font("System", Font.BOLD, 15));
         image.add(text);
                
         deposit = new JButton("Deposit");
         deposit.setBounds(110, 315, 120, 30);
         deposit.addActionListener(this);
         image.add(deposit);
         
         withdrawl = new JButton("CashWithdraw");
         withdrawl.setBounds(275, 315, 120, 30);
         withdrawl.addActionListener(this);
         image.add(withdrawl);
         
         fastcash = new JButton("Fast Cash");
         fastcash.setBounds(110, 346, 120, 30);
         fastcash.addActionListener(this);
         image.add(fastcash);
         
         ministatement = new JButton("MiniStatement");
         ministatement.setBounds(275, 346, 120, 30);
         ministatement.addActionListener(this);
         image.add(ministatement);
         
         pinchange = new JButton("Pin Change");
         pinchange.setBounds(110, 376, 120, 30);
         pinchange.addActionListener(this);
         image.add(pinchange);
         
         balanceenquiry = new JButton("BalanceEnquiry");
         balanceenquiry.setBounds(276, 376, 120, 30);
         balanceenquiry.addActionListener(this);
         image.add(balanceenquiry);
         
         exit = new JButton("Exit");
         exit.setBounds(276, 406, 120, 30);
         exit.addActionListener(this);
         image.add(exit);
         
         setSize(900, 900);
         setLocation(300, 0);
         setUndecorated(true);
         setVisible(true);
         
     }
      
     public void actionPerformed(ActionEvent ae){
         if (ae.getSource() == exit){
             System.exit(0);
         } else if (ae.getSource() == deposit) {
             setVisible(false);
             new Deposit(pinnumber).setVisible(true);
         } else if(ae.getSource() == withdrawl) {
             setVisible(false);
             new Withdrawl(pinnumber).setVisible(true);
         } else if (ae.getSource() == fastcash) {
             setVisible(false);
             new FastCash(pinnumber).setVisible(true);
         } else if (ae.getSource() == pinchange) {
             setVisible(false);
             new PinChange(pinnumber).setVisible(true);
         } else if (ae.getSource() == balanceenquiry) {
             setVisible(false);
             new BalanceEnquiry(pinnumber).setVisible(true);
         } else if (ae.getSource() == ministatement) {
             
             new MiniStatement(pinnumber).setVisible(true);
         } 
     }
    
     public static void main(String args[]) {
         new Transactions("");
     }
}
