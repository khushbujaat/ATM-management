
package bank.manegment.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.Date;

public class FastCash extends JFrame implements ActionListener{
       
     JButton deposit, withdrawl, ministatement, pinchange, fastcash, balanceenquiry, exit;
     String pinnumber;
     
     FastCash(String pinnumber) {
         this.pinnumber = pinnumber;
         setLayout(null);
         
         ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/atm.jpg"));
         Image i2 = i1.getImage().getScaledInstance(770, 770, Image.SCALE_DEFAULT);
         ImageIcon i3 = new ImageIcon(i2);
         JLabel image = new JLabel(i3);
         image.setBounds(0, 0, 700, 700);
         add(image);
         
         JLabel text = new JLabel("SELECTE WITHDRAWL AMOUNT");
         text.setBounds(150, 220, 650, 35);
         text.setForeground(Color.WHITE);
         text.setFont(new Font("System", Font.BOLD, 15));
         image.add(text);
                
         deposit = new JButton("Rs 100");
         deposit.setBounds(110, 315, 120, 30);
         deposit.addActionListener(this);
         image.add(deposit);
         
         withdrawl = new JButton("Rs 500");
         withdrawl.setBounds(275, 315, 120, 30);
         withdrawl.addActionListener(this);
         image.add(withdrawl);
         
         fastcash = new JButton("Rs 1000");
         fastcash.setBounds(110, 346, 120, 30);
         fastcash.addActionListener(this);
         image.add(fastcash);
         
         ministatement = new JButton("Rs 2000");
         ministatement.setBounds(275, 346, 120, 30);
         ministatement.addActionListener(this);
         image.add(ministatement);
         
         pinchange = new JButton("Rs 5000");
         pinchange.setBounds(110, 376, 120, 30);
         pinchange.addActionListener(this);
         image.add(pinchange);
         
         balanceenquiry = new JButton("Rs 10000");
         balanceenquiry.setBounds(276, 376, 120, 30);
         balanceenquiry.addActionListener(this);
         image.add(balanceenquiry);
         
         exit = new JButton("BACK");
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
             setVisible(false);
             new Transactions(pinnumber).setVisible(true);
         } else {
             String amount = ((JButton)ae.getSource()).getText().substring(3);
             Conn c = new Conn();
             try{
                 ResultSet  rs = c.s.executeQuery("select * from bank where pin = '"+pinnumber+"' ");
                 int balance = 0;
                 while (rs.next()) {
                     if(rs.getString("type").equals("Deposit")) {
                         balance += Integer.parseInt(rs.getString("amount"));
                     } else {
                         balance -= Integer.parseInt(rs.getString("amount"));
                     }
                 }
                 if(ae.getSource() != exit && balance < Integer.parseInt(amount)) {
                     JOptionPane.showMessageDialog(null, "Insufficient Balance");
                     return;
                 }
                 
                 Date date = new Date();
                 String query = "insert into bank values('"+pinnumber+"', '"+date+"', 'Withdrawl', '"+amount+"')";
                 c.s.executeUpdate(query);
                 JOptionPane.showMessageDialog(null, "Rs "+amount+"  Debited Sucessfully");
                 
                 setVisible(false);
                 new Transactions(pinnumber).setVisible(true);
             } catch (Exception e) {
                 System.out.println(e);
             }
         } 
     }
    
     public static void main(String args[]) {
         new FastCash("");
     }
}

