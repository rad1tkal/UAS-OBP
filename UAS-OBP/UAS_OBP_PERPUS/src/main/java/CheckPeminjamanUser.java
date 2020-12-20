import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CheckPeminjamanUser extends JFrame {
    public CheckPeminjamanUser() {
        setTitle("Status Peminjaman");
        setSize(600,500 );
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BoxLayout(getContentPane(),BoxLayout.Y_AXIS));

        add(new DataPanel1());

        add(new DataPanel2());
        add(new buttonPanel());
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
                    new MenuPengguna();
                    dispose();
                }
            });
        }

    }
    class DataPanel1 extends JPanel {
        JTable table;
        ResultSet rs;

        public void tampilkanData() {
            DefaultTableModel model = new DefaultTableModel();
            model.addColumn("ISBN");
            model.addColumn("Nama Buku");
            model.addColumn("Peminjam");
            model.addColumn("Tgl.Peminjaman");
            model.addColumn("Tgl.Pengembalian");

            try {
                Database db = new Database();
                rs = db.selectBukuDibookUser(Login.idLoggedIn);
                while (rs.next()) {
                    model.addRow(new Object[]{rs.getString("isbn"), rs.getString("namabuku"), rs.getString("namaPeminjam"), rs.getString("tanggalPeminjaman"), rs.getString("tanggalPengembalian")});
                }
                table.setModel(model);
            } catch (ClassNotFoundException exception) {
                exception.printStackTrace();

            } catch (SQLException exception) {
                exception.printStackTrace();
            }
        }

        String[] columnName = {"ISBN", "Nama Buku", "Peminjam", "Tgl.Peminjaman", "Tgl.Pengembalian"};

        Object[][] data = {


        };

        public DataPanel1() {
            table = new JTable(data, columnName);
            JScrollPane scrollPane = new JScrollPane(table);
            table.setFillsViewportHeight(true);
            table.setEnabled(false);
            setLayout(new GridLayout(1, 1));
            Border etchedBorder = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);
            TitledBorder titledBorder = BorderFactory.createTitledBorder(etchedBorder, "Menunggu Pengambilan");
            titledBorder.setTitleFont(titledBorder.getTitleFont().deriveFont(Font.BOLD));
            setBorder(BorderFactory.createCompoundBorder(new EmptyBorder(10, 10, 10, 10), titledBorder));

            add(scrollPane);
            tampilkanData();
        }
    }
    class DataPanel2 extends JPanel {
        JTable table;
        ResultSet rs;

        public void tampilkanData() {
            DefaultTableModel model = new DefaultTableModel();
            model.addColumn("ISBN");
            model.addColumn("Nama Buku");
            model.addColumn("Peminjam");
            model.addColumn("Tgl.Peminjaman");
            model.addColumn("Tgl.Pengembalian");

            try {
                Database db = new Database();
                rs = db.selectBukuDipinjamUser(Login.idLoggedIn);
                while (rs.next()) {
                    model.addRow(new Object[]{rs.getString("isbn"), rs.getString("namabuku"), rs.getString("namaPeminjam"), rs.getString("tanggalPeminjaman"), rs.getString("tanggalPengembalian")});
                }
                table.setModel(model);
            } catch (ClassNotFoundException exception) {
                exception.printStackTrace();

            } catch (SQLException exception) {
                exception.printStackTrace();
            }
        }

        String[] columnName = {"ISBN", "Nama Buku", "Peminjam", "Tgl.Peminjaman", "Tgl.Pengembalian"};

        Object[][] data = {


        };

        public DataPanel2() {
            table = new JTable(data, columnName);
            JScrollPane scrollPane = new JScrollPane(table);
            table.setFillsViewportHeight(true);
            table.setEnabled(false);

            setLayout(new GridLayout(1, 1));
            Border etchedBorder = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);
            TitledBorder titledBorder = BorderFactory.createTitledBorder(etchedBorder, "Menunggu Pengembalian");
            titledBorder.setTitleFont(titledBorder.getTitleFont().deriveFont(Font.BOLD));
            setBorder(BorderFactory.createCompoundBorder(new EmptyBorder(10, 10, 10, 10), titledBorder));

            add(scrollPane);
            tampilkanData();
        }
    }
}
