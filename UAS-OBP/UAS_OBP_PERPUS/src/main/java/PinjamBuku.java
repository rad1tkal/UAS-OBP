
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.Buffer;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PinjamBuku extends JFrame {
    DataPanel dp = new DataPanel();
    ResultSet rs;
    ButtonPanel bp = new ButtonPanel();
    ketgambar kg = new ketgambar();
    String foto="sampelcover.jpg";

    public PinjamBuku() {
        setTitle("Peminjaman Buku");
        setSize(600, 400);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        add(dp);
        add(kg);
        add(bp);



        setVisible(true);


    }
    class ketgambar extends JPanel{
        ImagePanel ip = new ImagePanel();
        KeteranganBukuPanel kbp = new KeteranganBukuPanel();
        public ketgambar(){
            setLayout(new GridLayout(1,2));
            add(kbp);
            add(ip);
            dp.table.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    int baris = dp.table.rowAtPoint(e.getPoint());

                    String sisbn = dp.table.getValueAt(baris, 0).toString();
                    kbp.isbn.setText(sisbn);

                    String nama = dp.table.getValueAt(baris, 1).toString();
                    kbp.nb.setText(nama);

                    String pengarang = dp.table.getValueAt(baris, 2).toString();
                    kbp.pgr.setText(pengarang);

                    String tahun = dp.table.getValueAt(baris, 3).toString();
                    kbp.tt.setText(tahun);

                    String status = dp.table.getValueAt(baris, 4).toString();
                    kbp.sts.setText(status);

                    foto = dp.table.getValueAt(baris, 5).toString();
                    kbp.ft.setText(foto);

                    ip.revalidate();
                    ip.repaint();

                }
            });

        }


    }

    class ImagePanel extends JPanel{

        private BufferedImage image;

        public ImagePanel() {
            try {

                image = ImageIO.read(new File("images/sampelcover.jpg"));
                image = resize(image, 280, 150);



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
            g.drawImage(image, 0, 0, this); // see javadoc for more info on the parameters
        }


    }

    class DataPanel extends JPanel {
        JTable table;


        public void tampilkanData() {
            DefaultTableModel model = new DefaultTableModel();
            model.addColumn("ISBN");
            model.addColumn("Nama Buku");
            model.addColumn("Pengarang");
            model.addColumn("Tahun Terbit");
            model.addColumn("Status");
            model.addColumn("Foto");


            try {
                Database db = new Database();
                rs = db.selectBuku();
                while (rs.next()) {
                    model.addRow(new Object[]{rs.getString("isbn"), rs.getString("namaBuku"), rs.getString("pengarang"), rs.getString("tahunTerbit"), rs.getString("statusPinjam"),rs.getString("fotobuku")});
                }
                table.setModel(model);
            } catch (ClassNotFoundException exception) {
                exception.printStackTrace();

            } catch (SQLException exception) {
                exception.printStackTrace();
            }
        }

        String[] columnName = {"ISBN", "Nama Buku", "Pengarang", "Tahun Terbit", "Status","fotobuku"};

        Object[][] data = {


        };

        public DataPanel() {
            table = new JTable(data, columnName);
            JScrollPane scrollPane = new JScrollPane(table);
            table.setFillsViewportHeight(true);
            table.setEnabled(false);

            setLayout(new GridLayout(1, 1));
            setBorder(new EmptyBorder(10, 10, 0, 10));

            add(scrollPane);
            tampilkanData();
        }
    }



    class ButtonPanel extends JPanel {


        JButton btnPinjam = new JButton("Pinjam");
        JButton btnBack = new JButton("Back");

        public ButtonPanel() {

            setLayout(new GridLayout(2, 1));
            add(btnPinjam);
            add(btnBack);
            btnBack.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                    dispose();
                }
            });



        }
    }

    class KeteranganBukuPanel extends JPanel {
        JTextField isbn = new JTextField();
        JTextField nb = new JTextField();
        JTextField pgr = new JTextField();
        JTextField tt = new JTextField();
        JTextField sts  = new JTextField();
        JTextField ft  = new JTextField();
        public KeteranganBukuPanel() {
            setPreferredSize(new Dimension(400,400));
            setLayout(new GridLayout(6,2));

            add(new JLabel("ISBN:"));
            add(isbn);
            isbn.setEditable(false);
            add(new JLabel("Buku:"));
            add(nb);
            nb.setEditable(false);
            add(new JLabel("Pengarang:"));
            add(pgr);
            pgr.setEditable(false);
            add(new JLabel("Tahun:"));
            add(tt);
            tt.setEditable(false);
            add(new JLabel("Status:"));
            add(sts);
            sts.setEditable(false);
            add(new JLabel("Foto:"));
            add(ft);
            ft.setEditable(false);


            Border etchedBorder = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);
            TitledBorder titledBorder = BorderFactory.createTitledBorder(etchedBorder, "Buku:");
            titledBorder.setTitleFont(titledBorder.getTitleFont().deriveFont(Font.BOLD));

            setBorder(BorderFactory.createCompoundBorder(new EmptyBorder(10, 10, 10, 10), titledBorder));


        }
    }
}







