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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;




public class Admin extends JFrame {
    DataPanel1 dp1 = new DataPanel1();
    InputPanel1 ip1 = new InputPanel1();
    ButtonPanel1 bp1 = new ButtonPanel1();
    DataPanel2 dp2 = new DataPanel2();
    InputPanel2 ip2 = new InputPanel2();
    ButtonPanel2 bp2 = new ButtonPanel2();
    DataPanel3 dp3 = new DataPanel3();
    InputPanel3 ip3 = new InputPanel3();
    ButtonPanel3 bp3 = new ButtonPanel3();
    DataPanel4 dp4 = new DataPanel4();
    InputPanel4 ip4 = new InputPanel4();
    ButtonPanel4 bp4 = new ButtonPanel4();
    public Admin() {
        setTitle("Admin");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(600, 600);
        setLocationRelativeTo(null);

        //setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));



        JLabel label1 = new JLabel();
        JLabel label2 = new JLabel();
        JLabel label3 = new JLabel();
        JLabel label4 = new JLabel();

        JPanel panel1 = new JPanel();
        JPanel panel2 = new JPanel();
        JPanel panel3 = new JPanel();
        JPanel panel4 = new JPanel();

        JTabbedPane pane = new JTabbedPane();
        panel1.setLayout(new BoxLayout(panel1, BoxLayout.Y_AXIS));
        panel2.setLayout(new BoxLayout(panel2,BoxLayout.Y_AXIS));
        panel3.setLayout(new BoxLayout(panel3,BoxLayout.Y_AXIS));
        panel4.setLayout(new BoxLayout(panel4,BoxLayout.Y_AXIS));
        panel1.add(label1);
        panel2.add(label2);
        panel3.add(label3);
        panel4.add(label4);


        pane.add(panel1, "Admin");
        pane.add(panel2, "Buku");
        pane.add(panel3, "Log Pinjam");
        pane.add(panel4, "Peminjam");

        add(pane);
        //admin
        panel1.add(dp1);
        panel1.add(ip1);
        panel1.add(bp1);
        //buku
        panel2.add(dp2);
        panel2.add(ip2);
        panel2.add(bp2);
        //log
        panel3.add(dp3);
        panel3.add(ip3);
        panel3.add(bp3);
        //peminjam
        panel4.add(dp4);
        panel4.add(ip4);
        panel4.add(bp4);

        setVisible(true);

    }


class DataPanel1 extends JPanel{
    JTable table;

    public void tampilkanData() {
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Staff ID");
        model.addColumn("Fullname");
        model.addColumn("Username");
        model.addColumn("Password");
        ResultSet rs;

        try {
            Database db = new Database();
            rs = db.adminData();
            while (rs.next()) {
                model.addRow(new Object[]{rs.getString("staffId"), rs.getString("fullName"), rs.getString("username"), rs.getString("adminPassword")});
            }
            table.setModel(model);
        } catch (ClassNotFoundException exception) {
            exception.printStackTrace();

        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }
    String[] columnName = {"Staff ID", "Fullname", "Username", "Password"};

    Object[][] data = {


    };
    public DataPanel1(){
        table = new JTable(data, columnName);
        JScrollPane scrollPane = new JScrollPane(table);
        table.setFillsViewportHeight(true);
        table.setEnabled(false);
        setLayout(new GridLayout(1, 1));
        setBorder(new EmptyBorder(10, 10, 0, 10));

        add(scrollPane);
        tampilkanData();
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int baris = table.rowAtPoint(e.getPoint());

                String idstaff = table.getValueAt(baris, 0).toString();
                ip1.idfield.setText(idstaff);

                String nama = table.getValueAt(baris, 1).toString();
                ip1.namefield.setText(nama);

                String user= table.getValueAt(baris, 2).toString();
                ip1.usernamefield.setText(user);

                String pass = table.getValueAt(baris, 3).toString();
                ip1.passwordfield.setText(pass);





            }
        });
    }
}
class InputPanel1 extends JPanel{
    JLabel idlabel = new JLabel("Staff ID");
    JLabel namelabel = new JLabel("Fullname");
    JLabel usernamelabel = new JLabel("Username");
    JLabel passwordlabel = new JLabel("Password");
    JTextField idfield =  new JTextField();
    JTextField namefield =  new JTextField();
    JTextField usernamefield =  new JTextField();
    JTextField passwordfield =  new JTextField();
    public InputPanel1(){
        setLayout(new GridLayout(4,2));
        add(idlabel);
        add(idfield);
        add(namelabel);
        add(namefield);
        add(usernamelabel);
        add(usernamefield);
        add(passwordlabel);
        add(passwordfield);
        Border etchedBorder = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);
        TitledBorder titledBorder = BorderFactory.createTitledBorder(etchedBorder, "Input");
        titledBorder.setTitleFont(titledBorder.getTitleFont().deriveFont(Font.BOLD));
        setBorder(BorderFactory.createCompoundBorder(new EmptyBorder(10, 10, 10, 10), titledBorder));

    }
}
    class ButtonPanel1 extends JPanel{
        JButton btnAdd = new JButton("Add");
        JButton btnUpdate = new JButton("Update");
        JButton btnDelete = new JButton("Delete");
        JButton btnClose = new JButton("Close");

        public ButtonPanel1(){
            setLayout(new GridLayout(1, 4));
            add(btnAdd);
            add(btnUpdate);
            add(btnDelete);
            add(btnClose);
            btnAdd.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try{
                        Database db = new Database();
                        db.admininsert(ip1.idfield.getText(),ip1.namefield.getText(),ip1.usernamefield.getText()
                                ,ip1.passwordfield.getText());
                        dp1.tampilkanData();

                    }
                    catch(SQLException ex){
                        ex.printStackTrace();
                    }
                    catch(ClassNotFoundException ex){
                        ex.printStackTrace();
                    }
                }
            });
            btnUpdate.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try{
                        Database db = new Database();
                        db.adminupdate(ip1.idfield.getText(),ip1.namefield.getText(),ip1.usernamefield.getText()
                                ,ip1.passwordfield.getText());
                        dp1.tampilkanData();
                    }
                    catch(SQLException ex ){
                        ex.printStackTrace();
                    }
                    catch(ClassNotFoundException ex){
                        ex.printStackTrace();
                    }
                }
            });
            btnDelete.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try{
                        Database db = new Database();
                        db.admindelete(ip1.idfield.getText());
                        dp1.tampilkanData();
                    }
                    catch(SQLException ex){
                        ex.printStackTrace();
                    }
                    catch(ClassNotFoundException ex){
                        ex.printStackTrace();
                    }
                }
            });
            btnClose.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    new MenuAdmin();
                    dispose();
                }
            });
        }
    }

class DataPanel2 extends JPanel{
    JTable table;

    public void tampilkanData() {
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("ISBN");
        model.addColumn("Nama Buku");
        model.addColumn("Penerbit");
        model.addColumn("Pengarang");
        model.addColumn("Tahun");
        model.addColumn("Status");
        model.addColumn("Foto");
        ResultSet rs;

        try {
            Database db = new Database();
            rs = db.bukuData();
            while (rs.next()) {
                model.addRow(new Object[]{rs.getString("isbn"), rs.getString("namaBuku"), rs.getString("penerbit"), rs.getString("pengarang"), rs.getString("tahunTerbit"), rs.getString("statusPinjam"), rs.getString("fotobuku")});
            }
            table.setModel(model);
        } catch (ClassNotFoundException exception) {
            exception.printStackTrace();

        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }
    String[] columnName = {"ISBN", "Nama Buku", "Penerbit", "Pengarang","Tahun","Status","Foto"};

    Object[][] data = {


    };
    public DataPanel2(){
        table = new JTable(data, columnName);
        JScrollPane scrollPane = new JScrollPane(table);
        table.setFillsViewportHeight(true);
        table.setEnabled(false);
        setLayout(new GridLayout(1, 1));
        setBorder(new EmptyBorder(10, 10, 0, 10));

        add(scrollPane);
        tampilkanData();
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int baris = table.rowAtPoint(e.getPoint());

                String isbn = table.getValueAt(baris, 0).toString();
                ip2.isbnfield.setText(isbn);

                String nama = table.getValueAt(baris, 1).toString();
                ip2.namafield.setText(nama);

                String pnr= table.getValueAt(baris, 2).toString();
                ip2.penerbitfield.setText(pnr);

                String pgr = table.getValueAt(baris, 3).toString();
                ip2.pengarangfield.setText(pgr);

                String thn = table.getValueAt(baris, 4).toString();
                ip2.tahunfield.setText(thn);

                String sts= table.getValueAt(baris, 5).toString();
                ip2.statusfield.setText(sts);

                String foto = table.getValueAt(baris, 6).toString();
                ip2.fotofield.setText(foto);





            }
        });
    }
}
    class InputPanel2 extends JPanel{
        JLabel isbnlabel = new JLabel("ISBN");
        JLabel namalabel = new JLabel("Nama Buku");
        JLabel penerbitlabel = new JLabel("Penerbit");
        JLabel pengaranglabel = new JLabel("Pengarang");
        JLabel tahunlabel = new JLabel("Tahun");
        JLabel statuslabel = new JLabel("Status");
        JLabel fotolabel = new JLabel("Foto");
        JTextField isbnfield =  new JTextField();
        JTextField namafield =  new JTextField();
        JTextField penerbitfield =  new JTextField();
        JTextField pengarangfield =  new JTextField();
        JTextField tahunfield =  new JTextField();
        JTextField statusfield =  new JTextField();
        JTextField fotofield =  new JTextField();
        public InputPanel2(){
            setLayout(new GridLayout(7,2));
            add(isbnlabel);
            add(isbnfield);
            add(namalabel);
            add(namafield);
            add(penerbitlabel);
            add(penerbitfield);
            add(pengaranglabel);
            add(pengarangfield);
            add(tahunlabel);
            add(tahunfield);
            add(statuslabel);
            add(statusfield);
            add(fotolabel);
            add(fotofield);
            Border etchedBorder = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);
            TitledBorder titledBorder = BorderFactory.createTitledBorder(etchedBorder, "Input");
            titledBorder.setTitleFont(titledBorder.getTitleFont().deriveFont(Font.BOLD));
            setBorder(BorderFactory.createCompoundBorder(new EmptyBorder(10, 10, 10, 10), titledBorder));

        }
    }
    class ButtonPanel2 extends JPanel{
        JButton btnAdd = new JButton("Add");
        JButton btnUpdate = new JButton("Update");
        JButton btnDelete = new JButton("Delete");
        JButton btnClose = new JButton("Close");

        public ButtonPanel2(){
            setLayout(new GridLayout(1, 4));
            add(btnAdd);
            add(btnUpdate);
            add(btnDelete);
            add(btnClose);
            btnAdd.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try{
                        Database db = new Database();
                        db.bukuinsert(ip2.isbnfield.getText(),ip2.namafield.getText(),ip2.penerbitfield.getText()
                                ,ip2.pengarangfield.getText(),Integer.parseInt(ip2.tahunfield.getText().trim()),
                                ip2.statusfield.getText(),ip2.fotofield.getText());
                        dp2.tampilkanData();

                    }
                    catch(SQLException ex){
                        ex.printStackTrace();
                    }
                    catch(ClassNotFoundException ex){
                        ex.printStackTrace();
                    }
                }
            });
            btnUpdate.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try{
                        Database db = new Database();
                        db.bukuupdate(ip2.isbnfield.getText(),ip2.namafield.getText(),ip2.penerbitfield.getText()
                                ,ip2.pengarangfield.getText(),Integer.parseInt(ip2.tahunfield.getText().trim()),
                                ip2.statusfield.getText(),ip2.fotofield.getText());
                        dp2.tampilkanData();
                    }
                    catch(SQLException ex ){
                        ex.printStackTrace();
                    }
                    catch(ClassNotFoundException ex){
                        ex.printStackTrace();
                    }
                }
            });
            btnDelete.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try{
                        Database db = new Database();
                        db.bukudelete(ip2.isbnfield.getText());
                        dp2.tampilkanData();
                    }
                    catch(SQLException ex){
                        ex.printStackTrace();
                    }
                    catch(ClassNotFoundException ex){
                        ex.printStackTrace();
                    }
                }
            });
            btnClose.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    new MenuAdmin();
                    dispose();
                }
            });
        }
    }
    class DataPanel3 extends JPanel{
        JTable table;

        public void tampilkanData() {
            DefaultTableModel model = new DefaultTableModel();
            model.addColumn("Order No");
            model.addColumn("ISBN");
            model.addColumn("NIM/NIDN");
            model.addColumn("Tgl.Peminjaman");
            model.addColumn("Tgl.Pengembalian");

            ResultSet rs;

            try {
                Database db = new Database();
                rs = db.logPinjamData();
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
        String[] columnName = {"Order No", "ISBN", "NIM/NIDN", "Tgl.Peminjaman","Tgl.Pengembalian"};

        Object[][] data = {


        };
        public DataPanel3(){
            table = new JTable(data, columnName);
            JScrollPane scrollPane = new JScrollPane(table);
            table.setFillsViewportHeight(true);
            table.setEnabled(false);
            setLayout(new GridLayout(1, 1));
            setBorder(new EmptyBorder(10, 10, 0, 10));

            add(scrollPane);
            tampilkanData();
            table.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    int baris = table.rowAtPoint(e.getPoint());

                    String noorder = table.getValueAt(baris, 0).toString();
                    ip3.ordernofield.setText(noorder);

                    String isbn = table.getValueAt(baris, 1).toString();
                    ip3.isbnfield.setText(isbn);

                    String nimnidn= table.getValueAt(baris, 2).toString();
                    ip3.nimnidnfield.setText(nimnidn);

                    String tglpnjm = table.getValueAt(baris, 3).toString();
                    ip3.tglpeminjamanfield.setText(tglpnjm);

                    String tglpgb = table.getValueAt(baris, 4).toString();
                    ip3.tglpengembalianfield.setText(tglpgb);









                }
            });
        }
    }
    class InputPanel3 extends JPanel{
        JLabel ordernolabel = new JLabel("Order No");
        JLabel isbnlabel = new JLabel("ISBN");
        JLabel nimnidnlabel = new JLabel("NIM/NIDN");
        JLabel tglpeminjamanlabel = new JLabel("Tgl.Peminjaman");
        JLabel tglpengembalianlabel = new JLabel("Tgl.Pengembalian");

        JTextField ordernofield =  new JTextField();
        JTextField isbnfield =  new JTextField();
        JTextField nimnidnfield =  new JTextField();
        JTextField tglpeminjamanfield =  new JTextField();
        JTextField tglpengembalianfield =  new JTextField();

        public InputPanel3(){
            setLayout(new GridLayout(5,2));
            add(ordernolabel);
            add(ordernofield);
            ordernofield.setEditable(false);
            add(isbnlabel);
            add(isbnfield);
            add(nimnidnlabel);
            add(nimnidnfield);
            add(tglpeminjamanlabel);
            add(tglpeminjamanfield);
            add(tglpengembalianlabel);
            add(tglpengembalianfield);

            Border etchedBorder = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);
            TitledBorder titledBorder = BorderFactory.createTitledBorder(etchedBorder, "Input");
            titledBorder.setTitleFont(titledBorder.getTitleFont().deriveFont(Font.BOLD));
            setBorder(BorderFactory.createCompoundBorder(new EmptyBorder(10, 10, 10, 10), titledBorder));

        }
    }
    class ButtonPanel3 extends JPanel{
        JButton btnAdd = new JButton("Add");
        JButton btnUpdate = new JButton("Update");
        JButton btnDelete = new JButton("Delete");
        JButton btnClose = new JButton("Close");

        public ButtonPanel3(){
            setLayout(new GridLayout(1, 4));
            add(btnAdd);
            add(btnUpdate);
            add(btnDelete);
            add(btnClose);
            btnAdd.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                    Date parsed1;
                    java.sql.Date dt1 = null;
                    Date parsed2;
                    java.sql.Date dt2 = null;
                    if(ip3.tglpengembalianfield.getText().equals("")){
                        dt1 = null;
                    }
                    if(ip3.tglpeminjamanfield.getText().equals("")){
                        dt2 = null;
                    }
                    else {
                        try {
                            parsed1 = format.parse(ip3.tglpeminjamanfield.getText());
                            dt1 = new java.sql.Date(parsed1.getTime());
                            parsed2 = format.parse(ip3.tglpengembalianfield.getText());
                            dt2 = new java.sql.Date(parsed2.getTime());
                        }
                        catch(ParseException pe){
                            pe.printStackTrace();
                        }
                    }
                    try{
                        Database db = new Database();
                        db.logpinjaminsert(ip3.isbnfield.getText(),ip3.nimnidnfield.getText(),dt1,dt2);
                        dp3.tampilkanData();

                    }
                    catch(SQLException ex){
                        ex.printStackTrace();
                    }
                    catch(ClassNotFoundException ex){
                        ex.printStackTrace();
                    }



                }
            });
            btnUpdate.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                    Date parsed1;
                    java.sql.Date dt1 = null;
                    Date parsed2;
                    java.sql.Date dt2 = null;
                    if(ip3.tglpengembalianfield.getText().equals("")){
                        dt1 = null;
                    }
                    if(ip3.tglpeminjamanfield.getText().equals("")){
                        dt2 = null;
                    }
                    else {
                        try {
                            parsed1 = format.parse(ip3.tglpeminjamanfield.getText());
                            dt1 = new java.sql.Date(parsed1.getTime());
                            parsed2 = format.parse(ip3.tglpengembalianfield.getText());
                            dt2 = new java.sql.Date(parsed2.getTime());
                        }
                        catch(ParseException pe){
                            pe.printStackTrace();
                        }
                    }
                    try{
                        Database db = new Database();
                        db.logpinjamupdate(Integer.parseInt(ip3.ordernofield.getText().trim()),ip3.isbnfield.getText(),ip3.nimnidnfield.getText(),dt1,dt2);
                        dp3.tampilkanData();
                    }
                    catch(SQLException ex ){
                        ex.printStackTrace();
                    }
                    catch(ClassNotFoundException ex){
                        ex.printStackTrace();
                    }
                }
            });
            btnDelete.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try{
                        Database db = new Database();
                        db.logpinjamdelete(Integer.parseInt(ip3.ordernofield.getText().trim()));
                        dp3.tampilkanData();
                    }
                    catch(SQLException ex){
                        ex.printStackTrace();
                    }
                    catch(ClassNotFoundException ex){
                        ex.printStackTrace();
                    }
                }
            });
            btnClose.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    new MenuAdmin();
                    dispose();
                }
            });
        }
    }

    class DataPanel4 extends JPanel{
        JTable table;

        public void tampilkanData() {
            DefaultTableModel model = new DefaultTableModel();
            model.addColumn("NIM/NIDN");
            model.addColumn("Nama");
            model.addColumn("Email");
            model.addColumn("Password");
            model.addColumn("Phone");
            model.addColumn("Faculty");
            ResultSet rs;

            try {
                Database db = new Database();
                rs = db.peminjamData();
                while (rs.next()) {
                    model.addRow(new Object[]{rs.getString("nimNidn"), rs.getString("namaPeminjam"), rs.getString("email"), rs.getString("peminjampassword"), rs.getString("phone"),rs.getString("faculty")});
                }
                table.setModel(model);
            } catch (ClassNotFoundException exception) {
                exception.printStackTrace();

            } catch (SQLException exception) {
                exception.printStackTrace();
            }
        }
        String[] columnName = {"NIM/NIDN", "Nama", "Email", "Password","Phone","Faculty"};

        Object[][] data = {


        };
        public DataPanel4(){
            table = new JTable(data, columnName);
            JScrollPane scrollPane = new JScrollPane(table);
            table.setFillsViewportHeight(true);
            table.setEnabled(false);
            setLayout(new GridLayout(1, 1));
            setBorder(new EmptyBorder(10, 10, 0, 10));

            add(scrollPane);
            tampilkanData();
            table.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    int baris = table.rowAtPoint(e.getPoint());

                    String nimnidn = table.getValueAt(baris, 0).toString();
                    ip4.nimnidnfield.setText(nimnidn);

                    String nama = table.getValueAt(baris, 1).toString();
                    ip4.namafield.setText(nama);

                    String email= table.getValueAt(baris, 2).toString();
                    ip4.emailfield.setText(email);

                    String pass = table.getValueAt(baris, 3).toString();
                    ip4.passwordfield.setText(pass);

                    String phone = table.getValueAt(baris, 4).toString();
                    ip4.phonefield.setText(phone);

                    String faculty = table.getValueAt(baris, 5).toString();
                    ip4.facultyfield.setText(faculty);










                }
            });
        }
    }
    class InputPanel4 extends JPanel{
        JLabel nimnidnlabel = new JLabel("NIM/NIDN");
        JLabel namalabel = new JLabel("Nama");
        JLabel emaillabel = new JLabel("Email");
        JLabel passwordlabel = new JLabel("Password");
        JLabel phonelabel = new JLabel("Phone");
        JLabel facultylabel = new JLabel("Faculty");

        JTextField nimnidnfield =  new JTextField();
        JTextField namafield =  new JTextField();
        JTextField emailfield =  new JTextField();
        JTextField passwordfield =  new JTextField();
        JTextField phonefield =  new JTextField();
        JTextField facultyfield =  new JTextField();
        public InputPanel4(){
            setLayout(new GridLayout(6,2));
            add(nimnidnlabel);
            add(nimnidnfield);
            add(namalabel);
            add(namafield);
            add(emaillabel);
            add(emailfield);
            add(passwordlabel);
            add(passwordfield);
            add(phonelabel);
            add(phonefield);
            add(facultylabel);
            add(facultyfield);

            Border etchedBorder = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);
            TitledBorder titledBorder = BorderFactory.createTitledBorder(etchedBorder, "Input");
            titledBorder.setTitleFont(titledBorder.getTitleFont().deriveFont(Font.BOLD));
            setBorder(BorderFactory.createCompoundBorder(new EmptyBorder(10, 10, 10, 10), titledBorder));

        }
    }
    class ButtonPanel4 extends JPanel{
        JButton btnAdd = new JButton("Add");
        JButton btnUpdate = new JButton("Update");
        JButton btnDelete = new JButton("Delete");
        JButton btnClose = new JButton("Close");

        public ButtonPanel4(){
            setLayout(new GridLayout(1, 4));
            add(btnAdd);
            add(btnUpdate);
            add(btnDelete);
            add(btnClose);
            btnAdd.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try{
                        Database db = new Database();
                        db.peminjaminsert(ip4.nimnidnfield.getText(),ip4.namafield.getText(),ip4.passwordfield.getText()
                        ,ip4.emailfield.getText(),ip4.phonefield.getText(),ip4.facultyfield.getText());
                        dp4.tampilkanData();

                    }
                    catch(SQLException ex){
                        ex.printStackTrace();
                    }
                    catch(ClassNotFoundException ex){
                        ex.printStackTrace();
                    }



                }
            });
            btnUpdate.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                    try{
                        Database db = new Database();
                        db.peminjamupdate(ip4.nimnidnfield.getText(),ip4.namafield.getText(),ip4.passwordfield.getText()
                                ,ip4.emailfield.getText(),ip4.phonefield.getText(),ip4.facultyfield.getText());
                        dp4.tampilkanData();
                    }
                    catch(SQLException ex ){
                        ex.printStackTrace();
                    }
                    catch(ClassNotFoundException ex){
                        ex.printStackTrace();
                    }
                }
            });
            btnDelete.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try{
                        Database db = new Database();
                        db.peminjamdelete(ip4.nimnidnfield.getText());
                        dp4.tampilkanData();
                    }
                    catch(SQLException ex){
                        ex.printStackTrace();
                    }
                    catch(ClassNotFoundException ex){
                        ex.printStackTrace();
                    }
                }
            });
            btnClose.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    new MenuAdmin();
                    dispose();
                }
            });
        }
    }


}