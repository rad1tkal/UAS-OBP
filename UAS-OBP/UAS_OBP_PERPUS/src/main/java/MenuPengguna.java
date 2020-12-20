
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class MenuPengguna extends JFrame {


    public MenuPengguna() {

        setTitle("Menu Utama");
        setSize(500,500);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BoxLayout(getContentPane(),BoxLayout.Y_AXIS));
        add(new ImagePanel());
        add(new ButtonMenuPanel());
        add(new buttonPanel());
        setVisible(true);
    }
    class buttonPanel extends JPanel {
        public buttonPanel() {
            setLayout(new FlowLayout(FlowLayout.LEFT));
            JButton logout = new JButton("Logout");
            add(logout);
            logout.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    new Login();
                    dispose();
                }
            });
        }
    }
    class ImagePanel extends JPanel{

        private BufferedImage image;

        public ImagePanel() {
            setPreferredSize(new Dimension(500,200));
            try {

                image = ImageIO.read(new File("images/welcome.jpg"));
                image=resize(image,500,200);
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
            g.drawImage(image, 0, 0,this.getWidth(),this.getHeight(), this); // see javadoc for more info on the parameters
        }

    }


    class ButtonMenuPanel extends JPanel{
        ImageIcon icon1 = new ImageIcon("images/pinjambuku.png");
        ImageIcon icon2 = new ImageIcon("images/status.png");

        public ButtonMenuPanel(){
            setLayout(new GridLayout(1,2));

            Image img1 = icon1.getImage() ;
            Image newimg1 = img1.getScaledInstance(150, 150,  java.awt.Image.SCALE_SMOOTH ) ;
            icon1 = new ImageIcon( newimg1 );
            Image img2 = icon2.getImage() ;
            Image newimg2 = img2.getScaledInstance(150, 150,  java.awt.Image.SCALE_SMOOTH ) ;
            icon2 = new ImageIcon( newimg2 );
            JButton btnPinjam = new JButton(icon1);
            JButton btnStatus = new JButton(icon2);
            add(btnPinjam);
            add(btnStatus);
            btnPinjam.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    new Pinjam();
                    dispose();
                }
            });
            btnStatus.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    new CheckPeminjamanUser();
                    dispose();
                }
            });

        }

    }

}
