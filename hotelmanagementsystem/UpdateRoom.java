 
package hotelmanagementsystem;
 
import java.awt.Choice;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class UpdateRoom extends JFrame implements ActionListener {

    Choice ccust;
    JTextField tfroom, tfavailabale, tfstatus;
     JButton check, update, back;
    public UpdateRoom() {
        setLayout(null);
        getContentPane().setBackground(Color.WHITE);
        
        JLabel text = new JLabel("Update Room Status");
        text.setFont(new Font("Tahoma", Font.PLAIN, 25));
        text.setBounds(30, 20, 250, 30);
        text.setForeground(Color.BLUE);
        add(text);
        
        JLabel lblid = new JLabel("Customer Id");
        lblid.setBounds(30, 80, 100, 20);
        add(lblid);
        
        ccust = new Choice();
        ccust.setBounds(200, 80, 150, 25);
        add(ccust);
        
        try{
            Conn conn = new Conn();
            ResultSet rs = conn.s.executeQuery("select *from customer");
            while(rs.next()){
                ccust.add(rs.getString("number"));
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        
        JLabel lblroom = new JLabel("Room Number");
        lblroom.setBounds(30, 130, 100, 20);
        add(lblroom);
        
        tfroom = new JTextField();
        tfroom.setBounds(200, 130, 150, 25);
        add(tfroom);
        
        JLabel lblname = new JLabel("Availability");
        lblname.setBounds(30, 180, 100, 20);
        add(lblname);
        
        tfavailabale = new JTextField();
        tfavailabale.setBounds(200, 180, 150, 25);
        add(tfavailabale);
        
        JLabel lblcheckin = new JLabel("Cleaning Status");
        lblcheckin.setBounds(30, 230, 100, 20);
        add(lblcheckin);
        
        tfstatus = new JTextField();
        tfstatus.setBounds(200, 230, 150, 25);
        add(tfstatus);
        
        check = new JButton("Check");
        check.setBackground(Color.BLACK);
        check.setForeground(Color.WHITE);
        check.setBounds(30, 300, 100, 30);
        check.addActionListener(this);
        add(check);
        
        update = new JButton("Update");
        update.setBackground(Color.BLACK);
        update.setForeground(Color.WHITE);
        update.setBounds(150, 300, 100, 30);
        update.addActionListener(this);
        add(update);
        
        back = new JButton("Back");
        back.setBackground(Color.BLACK);
        back.setForeground(Color.WHITE);
        back.setBounds(270, 300, 100, 30);
        back.addActionListener(this);
        add(back);
        
        ImageIcon i1 = (new ImageIcon(System.getProperty("user.dir")+ "\\src\\icons\\seventh.jpg"));
        Image i2 = i1.getImage().getScaledInstance(500, 300, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(400, 50, 500, 300);
        add(image);
        
        setBounds(300, 100, 980, 450);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if(ae.getSource() == check){
            String id = ccust.getSelectedItem();
            String query = "select *from customer where number = '"+id+"'";
            try{
                Conn c= new Conn();
                ResultSet rs = c.s.executeQuery(query);
                while(rs.next()){
                    tfroom.setText(rs.getString("room"));
                     
                     
                }
                
                ResultSet rs2 = c.s.executeQuery("select *from room where roomnumber = '"+tfroom.getText()+"'");
                while(rs2.next()){
                     tfavailabale.setText(rs2.getString("availability"));
                     tfstatus.setText(rs2.getString("clealing_status"));
                }
            }catch(Exception e){
                e.printStackTrace();
            }
        }else if(ae.getSource() == update){
             
            String room = tfroom.getText();
            String available =  tfavailabale.getText();
            String status =  tfstatus.getText();
             
            try{
                Conn c = new Conn();
                c.s.executeUpdate("update room set availability = '"+available+"', clealing_status = '"+status+"' where roomnumber = '"+room+"'");
                
                JOptionPane.showMessageDialog(null, "Data Updated Successfully");
                
                setVisible(false);
                new Reception();
                
            }catch(Exception e){
                e.printStackTrace();
            }
            
        }else if(ae.getSource() == back){
            setVisible(false);
            new Reception();
        }
    }
   
    
    public static void main(String[] args) {
        new UpdateRoom();
    }
}
