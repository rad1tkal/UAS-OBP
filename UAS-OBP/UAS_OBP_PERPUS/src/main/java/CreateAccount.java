import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

public class CreateAccount extends JFrame {
    JLabel nimNidnLabel = new JLabel("NIM/NIDN: ");
    JLabel namaLabel = new JLabel("Nama: ");
    JLabel passLabel = new JLabel("Password: ");
    JLabel emailLabel = new JLabel("Email: ");
    JLabel phoneLabel = new JLabel("Phone: ");
    JLabel facultyLabel = new JLabel("Faculty: ");
    JTextField nimNidnField = new JTextField();
    JTextField namaField = new JTextField();
    JTextField facultyField = new JTextField();
    JTextField passField = new JTextField();
    JTextField emailField = new JTextField();
    JTextField phoneField = new JTextField();
    public CreateAccount(){
        setTitle("Create Account");
        setSize(400, 350);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BoxLayout(getContentPane(),BoxLayout.Y_AXIS));
        add(new ImagePanel());
        add(new inputPanel());
        add(new buttonPanel());
        setVisible(true);
    }
    class ImagePanel extends JPanel{

        private BufferedImage image;

        public ImagePanel() {
            setPreferredSize(new Dimension(400,80));
            try {

                image = ImageIO.read(new File("images/puscangtip.png"));

            } catch (IOException ex) {
                ex.printStackTrace();
            }

        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawImage(image, 0, 0, this.getWidth(),this.getHeight(),this); // see javadoc for more info on the parameters
        }


    }
    class inputPanel extends JPanel{

        public inputPanel(){
            setLayout(new GridLayout(6,2));
            add(nimNidnLabel);
            add(nimNidnField);
            add(namaLabel);
            add(namaField);
            add(emailLabel);
            add(emailField);
            add(passLabel);
            add(passField);
            add(phoneLabel);
            add(phoneField);
            add(facultyLabel);
            add(facultyField);
            Border etchedBorder = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);
            TitledBorder titledBorder = BorderFactory.createTitledBorder(etchedBorder, "Masukan Data: ");
            titledBorder.setTitleFont(titledBorder.getTitleFont().deriveFont(Font.BOLD));
            setBorder(BorderFactory.createCompoundBorder(new EmptyBorder(10, 10, 10, 10), titledBorder));

        }
    }
    class buttonPanel extends JPanel{
        JButton create = new JButton("Create");
        JButton back = new JButton("Back");
        public buttonPanel(){
            setLayout(new GridLayout(2,1));
            add(create);
            add(back);
            create.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try{
                        Database db = new Database();
                        db.insertAccount(nimNidnField.getText(),namaField.getText(),passField.getText(),emailField.getText(),phoneField.getText(),facultyField.getText());
                        JOptionPane.showMessageDialog(null, "SUCCESS");
                        new Login();
                        dispose();
                    }
                    catch (SQLException ex){
                        ex.printStackTrace();
                    }
                    catch(ClassNotFoundException ex){
                        ex.printStackTrace();
                    }
                }
            });
            back.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    new Login();
                    dispose();
                }
            });
        }
    }
}
