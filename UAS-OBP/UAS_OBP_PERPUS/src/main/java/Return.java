import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Return extends JFrame{
    labelPanel lp = new labelPanel();
    DataPanel1 datapanel1 = new DataPanel1();

    ButtonPanel2 bp = new ButtonPanel2();
    String noorder;
    String isbn;
    public Return (){
        setTitle("Return Book");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(600, 600);
        setLocationRelativeTo(null);
        setLayout(new BoxLayout(getContentPane(),BoxLayout.Y_AXIS));
        add(datapanel1);
        add(lp);
        add(bp);
        setVisible(true);
    }

    class DataPanel1 extends JPanel implements TableCellRenderer {
        JTable table;
        ResultSet rs;

        public void tampilkanData() {
            DefaultTableModel model = new DefaultTableModel();
            model.addColumn("OrderNo");
            model.addColumn("ISBN");
            model.addColumn("NimNIDN");
            model.addColumn("Tgl.Peminjaman");
            model.addColumn("Tgl.Pengembalian");

            try {
                Database db = new Database();
                rs = db.returndata();
                while (rs.next()) {
                    model.addRow(new Object[]{rs.getString("orderNo"), rs.getString("isbn"), rs.getString("nimNidn"), rs.getString("tanggalPeminjaman"), rs.getString("tanggalPengembalian")});
                }
                table.setModel(model);
            } catch (ClassNotFoundException exception) {
                exception.printStackTrace();

            } catch (SQLException exception) {
                exception.printStackTrace();
            }
        }

        String[] columnName = {"Order No", "ISBN", "NIM/NIDN", "Tgl.Peminjaman", "Tgl.Pengembalian"};

        Object[][] data = {


        };

        public DataPanel1() {
            table = new JTable(data, columnName);
            JScrollPane scrollPane = new JScrollPane(table);
            table.setFillsViewportHeight(true);
            table.setEnabled(false);
            table.setRowSelectionAllowed(true);
            setLayout(new GridLayout(1, 1));
            Border etchedBorder = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);
            TitledBorder titledBorder = BorderFactory.createTitledBorder(etchedBorder, "Return");
            titledBorder.setTitleFont(titledBorder.getTitleFont().deriveFont(Font.BOLD));
            setBorder(BorderFactory.createCompoundBorder(new EmptyBorder(10, 10, 10, 10), titledBorder));

            add(scrollPane);
            tampilkanData();

            table.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    int baris = table.rowAtPoint(e.getPoint());
                    noorder = table.getValueAt(baris, 0).toString();

                    isbn = table.getValueAt(baris, 1).toString();
                    lp.jTextField1.setText(noorder);
                }
            });
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            if (isSelected){
                this.setBackground(Color.RED);
            }
            table.setSelectionBackground(Color.blue);
            return this;
        }
    }
    class ButtonPanel2 extends JPanel{

        public ButtonPanel2(){
            setLayout(new GridLayout(2,1));

            JButton jButton1 = new JButton("Return");
            JButton jButton2 = new JButton("Back");
            add(jButton1);
            add(jButton2);
            jButton1.addActionListener(new ButtonListener1());
            jButton2.addActionListener(new ButtonListener2());




        }
    }
    class labelPanel extends JPanel{
        JTextField jTextField1 = new JTextField();
        public labelPanel(){

            setLayout(new GridLayout(1,2));
            JLabel jLabel1 = new JLabel ("No Order : ");
            add(jLabel1);
            jTextField1.setEditable(false
            );
            add(jTextField1);
            Border etchedBorder = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);
            TitledBorder titledBorder = BorderFactory.createTitledBorder(etchedBorder, "Info");
            titledBorder.setTitleFont(titledBorder.getTitleFont().deriveFont(Font.BOLD));
            setBorder(BorderFactory.createCompoundBorder(new EmptyBorder(10, 10, 10, 10), titledBorder));

        }
    }
    class ButtonListener1 implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            Database db = null;
            try {
                db = new Database();
                db.adminreturn(Integer.parseInt(noorder.trim()), isbn);
                datapanel1.tampilkanData();

            } catch (ClassNotFoundException exception) {
                exception.printStackTrace();
            } catch (SQLException exception) {
                exception.printStackTrace();
            }
        }
    }
    class ButtonListener2 implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            new MenuAdmin();
            dispose();
        }
    }
}