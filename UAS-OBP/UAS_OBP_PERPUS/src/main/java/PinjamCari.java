
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PinjamCari extends JFrame{
    private Container contain;
    container con = new container();
    ImagePanel ip = new ImagePanel();
    Login login;
    buttonPanel bp = new buttonPanel();


    public PinjamCari() {


        setTitle("Peminjaman Buku");
        setSize(1024, 768);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BoxLayout(getContentPane(),BoxLayout.Y_AXIS));


        add(ip);

        //add(con);
        JScrollPane scrollPane = new JScrollPane(con,ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        add(new JScrollPane());
        add(scrollPane);
        add(bp);
        bp.setPreferredSize(new Dimension(200,100));
        setVisible(true);


    }


    class buttonPanel extends JPanel{
        public buttonPanel(){
            setLayout(new FlowLayout(FlowLayout.LEFT));
            JButton back = new JButton("Back");
            add(back);
            back.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    new Pinjam();
                    dispose();
                }
            });
        }

    }
    public class Picture extends JPanel {

        private ImageIcon _image1;
        private JLabel _mainLabel;


        public Picture(String foto){
            setPreferredSize(new Dimension(200,270));
            _image1 = new ImageIcon("images/"+foto);
            Image image = _image1.getImage(); // transform it
            Image newimg = image.getScaledInstance(200, 270,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
            _image1 = new ImageIcon(newimg);  // transform it back
            _mainLabel = new JLabel(_image1);
            add(_mainLabel);


        }
        public Picture(int w, int h){

            _image1 = new ImageIcon("images/puscangtip.png");
            Image image = _image1.getImage(); // transform it
            Image newimg = image.getScaledInstance(w, h,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
            _image1 = new ImageIcon(newimg);  // transform it back
            _mainLabel = new JLabel(_image1);
            add(_mainLabel);


        }

    }

    class ImagePanel extends JPanel{

        private BufferedImage image;

        public ImagePanel() {
            setPreferredSize(new Dimension(1024,200));
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
            }
            catch (IOException ex) {
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

    class container extends JPanel{
        ResultSet rs ;

        public container(){
            //setPreferredSize(new Dimension(1024,500));

            setLayout(new GridLayout(3,2));
            Border loweredBevel = BorderFactory.createLoweredBevelBorder();
            setBorder(BorderFactory.createCompoundBorder(new EmptyBorder(10, 10, 10, 10), loweredBevel));
            tampilkanBuku2();


        }


        public void tampilkanBuku(){
            try{
                Database db = new Database();
                rs = db.selectFoto();
                while(rs.next()){
                    add(new buku(rs.getString("isbn"), rs.getString("fotobuku"), rs.getString("namaBuku"), rs.getString("pengarang"), rs.getString("penerbit"), rs.getString("tahunTerbit"), rs.getString("statusPinjam")));

                }

            }
            catch (SQLException e){
                e.printStackTrace();
            }
            catch(ClassNotFoundException e){
                e.printStackTrace();
            }
            catch(NullPointerException e){
                e.printStackTrace();
            }
        }
        public void tampilkanBuku2(){
            try{
                Database db = new Database();
                rs = db.selectFoto2(Pinjam.cari);
                while(rs.next()){
                    add(new buku(rs.getString("isbn"), rs.getString("fotobuku"), rs.getString("namaBuku"), rs.getString("pengarang"), rs.getString("penerbit"), rs.getString("tahunTerbit"), rs.getString("statusPinjam")));

                }

            }
            catch (SQLException e){
                e.printStackTrace();
            }
            catch(ClassNotFoundException e){
                e.printStackTrace();
            }
            catch(NullPointerException e){
                e.printStackTrace();
            }
        }


    }

    class ketbuku extends JPanel {
        JLabel nm = new JLabel();
        JLabel pgr = new JLabel();
        JLabel pnr = new JLabel();
        JLabel thn = new JLabel();
        JLabel sts = new JLabel();

        public ketbuku(String namabuku, String pengarang, String penerbit, String tahun, String status){
            setLayout(new GridLayout(5,1));
            nm.setText("Buku: "+namabuku);
            pgr.setText("Pengarang: "+pengarang);
            pnr.setText("Penerbit: "+penerbit);
            thn.setText("Tahun: "+tahun);
            sts.setText(status);
            add(nm);
            add(pgr);
            add(pnr);
            add(thn);
            add(sts);
            Border etchedBorder = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);
            TitledBorder titledBorder = BorderFactory.createTitledBorder(etchedBorder, "Keterangan");
            titledBorder.setTitleFont(titledBorder.getTitleFont().deriveFont(Font.BOLD));
            setBorder(BorderFactory.createCompoundBorder(new EmptyBorder(5, 5, 5, 5), titledBorder));


        }
    }
    class buku extends JPanel{

        public buku(String isbn,String foto, String namabuku, String pengarang,String penerbit, String tahun,String status ){
            setPreferredSize(new Dimension(400,300));
            setLayout(new GridLayout(1,2));
            Border raisedbevel = BorderFactory.createRaisedBevelBorder();
            setBorder(BorderFactory.createCompoundBorder(new EmptyBorder(10, 10, 10, 10), raisedbevel));
            add(new Picture(foto));
            add(new ketbuku(namabuku,pengarang,penerbit,tahun,status));
            final String nama;
            nama = namabuku;
            final String isbnbuku = isbn;
            addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    int input = JOptionPane.showConfirmDialog(null,
                            "Anda ingin meminjam buku ini?", nama,JOptionPane.YES_NO_OPTION);
                    if (input == JOptionPane.YES_OPTION){
                        try{

                            Database db = new Database();

                            db.userPinjam(isbnbuku);
                            db.tmpInsertlog(isbnbuku, login.idLoggedIn);
                            JOptionPane.showMessageDialog(null, "Berhasil, silakan ambil buku pada perpustakaan");
                            new MenuPengguna();
                            dispose();


                        }
                        catch(SQLException ex){
                            ex.printStackTrace();
                        }
                        catch(ClassNotFoundException ex){
                            ex.printStackTrace();
                        }
                    }
                }
            });



        }


    }
}




