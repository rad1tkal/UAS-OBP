
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
import java.sql.ResultSet;
import java.sql.SQLException;

public class Login extends JFrame {
    public static String idLoggedIn;
    ImagePanel imp1 = new ImagePanel();
    InputPanel ip1 =  new InputPanel();
    ButtonPanel bp1 = new ButtonPanel();
    ImagePanel2 imp2 = new ImagePanel2();
    InputPanel2 ip2 =  new InputPanel2();
    ButtonPanel2 bp2 = new ButtonPanel2();
    public Login() {
        setTitle("Login");
        setSize(500, 300);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        //setLayout(new BoxLayout(getContentPane(),BoxLayout.Y_AXIS));
        JTabbedPane tabbedPane = new JTabbedPane();


        JPanel panel1 = new JPanel();
        panel1.setLayout(new BoxLayout(panel1, BoxLayout.Y_AXIS));
        JPanel panel2 = new JPanel();
        panel2.setLayout(new BoxLayout(panel2, BoxLayout.Y_AXIS));
        tabbedPane.add("Pengguna", panel1);
        tabbedPane.add("Admin", panel2);
        panel1.add(imp1);
        panel1.add(ip1);
        panel1.add(bp1);
        panel2.add(imp2);
        panel2.add(ip2);
        panel2.add(bp2);
        add(tabbedPane);
        setVisible(true);
    }

    class InputPanel extends JPanel {
        JTextField usernameField = new JTextField();
        JPasswordField passwordField = new JPasswordField();

        public InputPanel() {
            setLayout(new GridLayout(2, 2));

            add(new JLabel("Username"));
            add(usernameField);
            add(new JLabel("Password"));
            add(passwordField);


            Border etchedBorder = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);
            TitledBorder titledBorder = BorderFactory.createTitledBorder(etchedBorder, "Login Info");
            titledBorder.setTitleFont(titledBorder.getTitleFont().deriveFont(Font.BOLD));
            setBorder(BorderFactory.createCompoundBorder(new EmptyBorder(10, 10, 10, 10), titledBorder));


        }
    }

    class InputPanel2 extends JPanel {
        JTextField usernameField = new JTextField();
        JPasswordField passwordField = new JPasswordField();

        public InputPanel2() {
            setLayout(new GridLayout(2, 2));

            add(new JLabel("Username"));
            add(usernameField);
            add(new JLabel("Password"));
            add(passwordField);


            Border etchedBorder = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);
            TitledBorder titledBorder = BorderFactory.createTitledBorder(etchedBorder, "Login Info");
            titledBorder.setTitleFont(titledBorder.getTitleFont().deriveFont(Font.BOLD));
            setBorder(BorderFactory.createCompoundBorder(new EmptyBorder(10, 10, 10, 10), titledBorder));


        }
    }

    class ImagePanel extends JPanel{

        private BufferedImage image;

        public ImagePanel() {
            setPreferredSize(new Dimension(500,80));
            try {

                image = ImageIO.read(new File("images/puscangtip.png"));

            } catch (IOException ex) {
                ex.printStackTrace();
            }

        }
        public ImagePanel(String foto) {

            try {

                image = ImageIO.read(new File("images/"+foto));
                image=resize(image,200,200);
            } catch (IOException ex) {
                ex.printStackTrace();
            }

        }
        public BufferedImage resize(BufferedImage img, int newW, int newH) {
            Image tmp = img.getScaledInstance(newW, newH, Image.SCALE_SMOOTH);
            BufferedImage dimg = new BufferedImage(newW, newH, BufferedImage.TYPE_INT_ARGB);

            Graphics2D g2d = dimg.createGraphics();
            g2d.drawImage(tmp, 0, 0, null);
            g2d.dispose();

            return dimg;
        }
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawImage(image, 0, 0, this.getWidth(),this.getHeight(),this); // see javadoc for more info on the parameters
        }


    }

    class ImagePanel2 extends JPanel{

        private BufferedImage image;

        public ImagePanel2() {
            setPreferredSize(new Dimension(500,80));
            try {

                image = ImageIO.read(new File("images/puscangtip.png"));
                image=resize(image,1024,250);
            } catch (IOException ex) {
                ex.printStackTrace();
            }

        }
        public ImagePanel2(String foto) {

            try {

                image = ImageIO.read(new File("images/"+foto));
                image=resize(image,200,200);
            } catch (IOException ex) {
                ex.printStackTrace();
            }

        }
        public BufferedImage resize(BufferedImage img, int newW, int newH) {
            Image tmp = img.getScaledInstance(newW, newH, Image.SCALE_SMOOTH);
            BufferedImage dimg = new BufferedImage(newW, newH, BufferedImage.TYPE_INT_ARGB);

            Graphics2D g2d = dimg.createGraphics();
            g2d.drawImage(tmp, 0, 0, null);
            g2d.dispose();

            return dimg;
        }
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawImage(image, 0, 0, this.getWidth(),this.getHeight(),this); // see javadoc for more info on the parameters
        }


    }


    class ButtonPanel extends JPanel {
        JButton btnLogin = new JButton("Login");
        JButton btnCreate = new JButton("Create Account");

        public ButtonPanel() {
            setLayout(new GridLayout(1, 1));
            add(btnLogin);
            btnLogin.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try{
                        Database db = new Database();
                        ResultSet rs = db.selectPengguna(ip1.usernameField.getText(),ip1.passwordField.getText());
                        if (rs.next()){
                            idLoggedIn = rs.getString("nimNidn");
                            new MenuPengguna();
                            dispose();
                        }
                        else {
                            JOptionPane.showMessageDialog(getContentPane(),"Invalid username/password");
                        }
                    }
                    catch(ClassNotFoundException exception){
                        exception.printStackTrace();
                    }
                    catch(SQLException exception){
                        exception.printStackTrace();
                    }
                }
            });
            add(btnCreate);
            btnCreate.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    new CreateAccount();
                    dispose();
                }
            });
        }

    }

    class ButtonPanel2 extends JPanel {
        JButton btnLogin = new JButton("Login");


        public ButtonPanel2() {
            setLayout(new GridLayout(1, 1));
            add(btnLogin);
            btnLogin.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try{
                        Database db = new Database();
                        ResultSet rs = db.selectAdmin(ip2.usernameField.getText(),ip2.passwordField.getText());
                        if (rs.next()){
                            idLoggedIn = rs.getString("StaffId");
                            new MenuAdmin();
                            dispose();
                        }
                        else {
                            JOptionPane.showMessageDialog(getContentPane(),"Invalid username/password");
                        }
                    }
                    catch(ClassNotFoundException exception){
                        exception.printStackTrace();
                    }
                    catch(SQLException exception){
                        exception.printStackTrace();
                    }
                }
            });

        }

    }
}

